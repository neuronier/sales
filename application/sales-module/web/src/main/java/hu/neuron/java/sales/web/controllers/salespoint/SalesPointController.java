package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.AddressServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;
import hu.neuron.java.web.onemenu.CitySelectOneMenuView;
import hu.neuron.java.web.onemenu.WarehouseSelectOneMenuView;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "salesPointController")
public class SalesPointController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private SalesPointVO selectedSalesPoint;

	private String salesPointName;

	private String streetName;

	private String houseNumber;

	private String zipCode;

	private String phoneNumber;
	
	private String updateSalesPointName;

	private String updateStreetName;

	private String updateHouseNumber;

	private String updateZipCode;

	private String updatePhoneNumber;

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote salesPointService;

	@EJB(name = "AddressService", mappedName = "AddressService")
	private AddressServiceRemote addressService;

	@EJB(name = "WarehouseService", mappedName = "WarehouseService")
	private WarehouseServiceRemote warehouseService;

	private LazySalesPointModel lazySalesPointModel;

	@PostConstruct
	public void init() {
		setLazySalesPointModel(new LazySalesPointModel(salesPointService,
				addressService));
	}

	public void saveNewSalesPoint() {
		SalesPointVO salesPointVO = new SalesPointVO(true);
		AddressVO addressVO = new AddressVO();
		addressVO.setCity(CitySelectOneMenuView.getStaticCity());
		addressVO.setHouseNumber(houseNumber);
		addressVO.setStreet(streetName);
		addressVO.setZipCode(zipCode);

		AddressVO addressCheck = null;
		try {
			addressCheck = addressService.findAddressByEquals(addressVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (addressCheck == null) {
			addressVO.generateAddressId();
			addressService.saveAddress(addressVO);
			salesPointVO.setAddress(addressVO);
		} else {
			salesPointVO.setAddress(addressCheck);
		}
		salesPointVO.setName(salesPointName);
		salesPointVO.setSalePointPhoneNumber(phoneNumber);

		WarehouseVO warehouseCheck = null;
		try {
			warehouseCheck = warehouseService
					.findWarehouseByWarehouseName(WarehouseSelectOneMenuView
							.getStaticWarehouseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (warehouseCheck == null) {
			warehouseCheck = new WarehouseVO();
			warehouseCheck.setWarehouseName(WarehouseSelectOneMenuView
					.getStaticWarehouseName());
			warehouseCheck.generateWarehouseId();
			warehouseService.saveWarehouse(warehouseCheck);
			salesPointVO.setWarehouse(warehouseCheck);
		} else {
			salesPointVO.setWarehouse(warehouseCheck);
		}

		salesPointService.saveSalePoint(salesPointVO);
		CitySelectOneMenuView.updateCityList();
		WarehouseSelectOneMenuView.updateWarehouseList();
		selectedSalesPoint = null;
		salesPointName = null;
		CitySelectOneMenuView.setStaticCity(null);
		WarehouseSelectOneMenuView.setStaticWarehouseName(null);
		streetName = null;
		houseNumber = null;
		houseNumber = null;
		zipCode = null;
		phoneNumber = null;
	}

	public void onRowSelect(SelectEvent event) {
		selectedSalesPoint = (SalesPointVO) event.getObject();
		AddressVO adr = selectedSalesPoint.getAddress();
		WarehouseVO wrh = selectedSalesPoint.getWarehouse();
		if (adr != null) {
			CitySelectOneMenuView.setStaticCity(adr.getCity());
			updateStreetName = adr.getStreet();
			updateHouseNumber = adr.getHouseNumber();
			updateZipCode = adr.getZipCode();
		}
		if (wrh != null) {
			WarehouseSelectOneMenuView.setStaticWarehouseName(wrh.getWarehouseName());
		}
		updateSalesPointName = selectedSalesPoint.getName();
		updatePhoneNumber = selectedSalesPoint.getSalePointPhoneNumber();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedSalesPoint.getName()));
	}

	public void removeSalesPoint() {
		try {
			AddressVO addr = selectedSalesPoint.getAddress();
			salesPointService.removeSalePoint(selectedSalesPoint);
			addressService.removeAddressById(addr.getAddressId());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Deleted: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
			CitySelectOneMenuView.setStaticCity(null);
			WarehouseSelectOneMenuView.setStaticWarehouseName(null);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Deleted: "));
		}
	}

	public void updateSalesPoint() {
		try {
			AddressVO currentAddr = selectedSalesPoint.getAddress();
			if (currentAddr != null) {
				currentAddr.setCity(CitySelectOneMenuView.getStaticCity());
				currentAddr.setHouseNumber(updateHouseNumber);
				currentAddr.setStreet(updateStreetName);
				currentAddr.setZipCode(updateZipCode);
			} else {
				throw new NullPointerException();
			}
			addressService.updateAddress(currentAddr);
			WarehouseVO warehouseCheck = null;
			try {
				warehouseCheck = warehouseService
						.findWarehouseByWarehouseName(WarehouseSelectOneMenuView
								.getStaticWarehouseName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (warehouseCheck == null) {
				warehouseCheck = new WarehouseVO();
				warehouseCheck.setWarehouseName(WarehouseSelectOneMenuView
						.getStaticWarehouseName());
				warehouseCheck.generateWarehouseId();
				warehouseService.saveWarehouse(warehouseCheck);
				selectedSalesPoint.setWarehouse(warehouseCheck);
			} else {
				selectedSalesPoint.setWarehouse(warehouseCheck);
				if(!warehouseCheck.getWarehouseName().equals(WarehouseSelectOneMenuView
						.getStaticWarehouseName())){
					warehouseService.updateWarehouse(warehouseCheck);
				}
			}

			CitySelectOneMenuView.setStaticCity(null);
			WarehouseSelectOneMenuView.setStaticWarehouseName(null);
			selectedSalesPoint.setName(updateSalesPointName);
			selectedSalesPoint.setSalePointPhoneNumber(updatePhoneNumber);
			salesPointService.saveSalePoint(selectedSalesPoint);
			WarehouseSelectOneMenuView.updateWarehouseList();

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
			updateSalesPointName = null;
			updateStreetName = null;
			updateHouseNumber = null;
			updatePhoneNumber = null;
			updateZipCode = null;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
			selectedSalesPoint = null;
		}
	}
	
	public void clearSelection(CloseEvent event){ 
		selectedSalesPoint = null;
		CitySelectOneMenuView.setStaticCity(null);
		WarehouseSelectOneMenuView.setStaticWarehouseName(null);
		updateSalesPointName = null;
		updateStreetName = null;
		updateHouseNumber = null;
		updatePhoneNumber = null;
		updateZipCode = null;
	}

	public LazySalesPointModel getLazySalesPointModel() {
		return lazySalesPointModel;
	}

	public void setLazySalesPointModel(LazySalesPointModel lazySalesPointModel) {
		this.lazySalesPointModel = lazySalesPointModel;
	}

	public SalesPointVO getSelectedSalesPoint() {
		return selectedSalesPoint;
	}

	public void setSelectedSalesPoint(SalesPointVO selectedSalesPoint) {
		this.selectedSalesPoint = selectedSalesPoint;
	}

	public String getSalesPointName() {
		return salesPointName;
	}

	public void setSalesPointName(String newSalesPointName) {
		this.salesPointName = newSalesPointName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUpdateSalesPointName() {
		return updateSalesPointName;
	}

	public void setUpdateSalesPointName(String updateSalesPointName) {
		this.updateSalesPointName = updateSalesPointName;
	}

	public String getUpdateStreetName() {
		return updateStreetName;
	}

	public void setUpdateStreetName(String updateStreetName) {
		this.updateStreetName = updateStreetName;
	}

	public String getUpdateHouseNumber() {
		return updateHouseNumber;
	}

	public void setUpdateHouseNumber(String updateHouseNumber) {
		this.updateHouseNumber = updateHouseNumber;
	}

	public String getUpdateZipCode() {
		return updateZipCode;
	}

	public void setUpdateZipCode(String updateZipCode) {
		this.updateZipCode = updateZipCode;
	}

	public String getUpdatePhoneNumber() {
		return updatePhoneNumber;
	}

	public void setUpdatePhoneNumber(String updatePhoneNumber) {
		this.updatePhoneNumber = updatePhoneNumber;
	}
	
	
}
