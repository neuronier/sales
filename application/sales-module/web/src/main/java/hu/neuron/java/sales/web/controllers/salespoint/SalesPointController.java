package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.AddressServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.web.onemenu.CitySelectOneMenuView;
import hu.neuron.java.web.onemenu.CityService;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.util.Constants;

@ViewScoped
@ManagedBean(name = "salesPointController")
public class SalesPointController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private SalesPointVO selectedSalesPoint;

	private String newSalesPointName;

	private String updateSalesPointName;
	
	private String streetName;
	
	private String houseNumber;
	
	private String zipCode;

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote salesPointService;
	
	@EJB(name = "AddressService", mappedName = "AddressService")
	private AddressServiceRemote addressService;
	
	@ManagedProperty("#{cityService}")
	private CityService cityService;

	private LazySalesPointModel lazySalesPointModel;

	@PostConstruct
	public void init() {
		setLazySalesPointModel(new LazySalesPointModel(salesPointService,addressService));
	}

	public void saveNewSalesPoint() {
		SalesPointVO salesPointVO = new SalesPointVO(true);
		AddressVO addressVO = new AddressVO();
		addressVO.setCity(CitySelectOneMenuView.getStaticCity());
		addressVO.setHouseNumber(houseNumber);
		addressVO.setStreet(streetName);
		addressVO.setZipCode(zipCode);
		AddressVO check = null;
		try {
			check = addressService.findAddressByEquals(addressVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check == null){
			addressVO.generateAddressId();
			addressService.saveAddress(addressVO);
			salesPointVO.setAddress(addressVO);
		} else{
			salesPointVO.setAddress(check);
		}
		salesPointVO.setName(newSalesPointName);
		salesPointService.saveSalePoint(salesPointVO);
		cityService.updateCityList();
		selectedSalesPoint = null;
		newSalesPointName = null;
		updateSalesPointName = null;
		CitySelectOneMenuView.setStaticCity(null);
		streetName = null;
		houseNumber = null;
		houseNumber = null;
		zipCode = null;
	}

	public void onRowSelect(SelectEvent event) {
		selectedSalesPoint = (SalesPointVO) event.getObject();
		updateSalesPointName = selectedSalesPoint.getName();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedSalesPoint.getName()));
	}

	public void removeSalesPoint() {
		try {
			salesPointService.removeSalePoint(selectedSalesPoint);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Deleted: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Deleted: "));
		}
	}

	public void updateSalesPoint() {
		try {
			selectedSalesPoint.setName(updateSalesPointName);
			salesPointService.saveSalePoint(selectedSalesPoint);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
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

	public String getNewSalesPointName() {
		return newSalesPointName;
	}

	public void setNewSalesPointName(String newSalesPointName) {
		this.newSalesPointName = newSalesPointName;
	}

	public String getUpdateSalesPointName() {
		return updateSalesPointName;
	}

	public void setUpdateSalesPointName(String updateSalesPointName) {
		this.updateSalesPointName = updateSalesPointName;
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

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	
	public int sortByAddress(Object addr1, Object addr2){
		if(addr1 instanceof AddressVO && addr2 instanceof AddressVO){
			AddressVO ad1 = (AddressVO)addr1;
			AddressVO ad2 = (AddressVO)addr2;
			return ad1.getCity().compareTo(ad2.getCity());
		}
		return 0;
	}
	
	public boolean filterByAddress(Object value, Object filter, Locale locale) {
		if(filter == null || filter.toString().trim().equals(Constants.EMPTY_STRING)) {
            return true;
        }
        
        if(value == null) {
            return false;
        }
        System.out.println("INFO:::VALUE: " + value.toString());
        System.out.println("INFO:::FILTER: " + filter.toString());
        AddressVO selected = (AddressVO) value;
        String search = selected.getCity() + " " + selected.getStreet() + " " + selected.getHouseNumber();
        return search.contains(filter.toString());
    }
}
