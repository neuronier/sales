package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.AddressServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;
import hu.neuron.java.sales.web.LocalizationsUtils;
import hu.neuron.java.web.onemenu.CitySelectOneMenuView;
import hu.neuron.java.web.onemenu.UserListService;
import hu.neuron.java.web.onemenu.WarehouseSelectOneMenuView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

@ViewScoped
@ManagedBean(name = "salesPointController")
public class SalesPointController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private static SalesPointVO selectedSalesPoint;

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
	
	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;
	
	@ManagedProperty("#{userListService}")
    private UserListService userListBean;
	
	@ManagedProperty("#{warehouseSelectOneMenuView}")
	private WarehouseSelectOneMenuView warehouseBean;

	private LazySalesPointModel lazySalesPointModel;
	
	private DualListModel<UserVO> users;
	
    private List<UserVO> allUserList;
    
    private List<UserVO> userSourceList;
    
    private List<UserVO> userTargetList;

	@PostConstruct
	public void init() {
		setLazySalesPointModel(new LazySalesPointModel(salesPointService, addressService));
		setAllUserList(userService.getUserList());
        userSourceList = new LinkedList<>();
        userTargetList = new LinkedList<>();
        users = new DualListModel<UserVO>(userSourceList,userTargetList);
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
			warehouseBean.getWarehouses().remove(warehouseBean.getWarehouses().indexOf(wrh));
			warehouseBean.getWarehouses().add(0, wrh);
			warehouseBean.setWarehouse(wrh);
		}
		updateSalesPointName = selectedSalesPoint.getName();
		updatePhoneNumber = selectedSalesPoint.getSalePointPhoneNumber();
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
					.findWarehouseByWarehouseName(warehouseBean.getWarehouse().getWarehouseName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (warehouseCheck == null) {
			warehouseCheck = new WarehouseVO();
			warehouseCheck.setWarehouseName(warehouseBean.getWarehouse().getWarehouseName());
			warehouseCheck.generateWarehouseId();
			warehouseService.saveWarehouse(warehouseCheck);
			salesPointVO.setWarehouse(warehouseCheck);
		} else {
			salesPointVO.setWarehouse(warehouseCheck);
		}

		salesPointService.saveSalePoint(salesPointVO);
		CitySelectOneMenuView.updateCityList();
		warehouseBean.updateWarehouseList();
		selectedSalesPoint = null;
		salesPointName = null;
		CitySelectOneMenuView.setStaticCity(null);
		warehouseBean.setWarehouse(null);
		streetName = null;
		houseNumber = null;
		houseNumber = null;
		zipCode = null;
		phoneNumber = null;
	}
	
	public void removeSalesPoint() {
		try {
			AddressVO addr = selectedSalesPoint.getAddress();
			salesPointService.removeSalePoint(selectedSalesPoint);
			addressService.removeAddressById(addr.getAddressId());
			List<UserVO> allUsers = userService.findAll();
			for(UserVO user : allUsers){
				if(user.getSalesPoint() != null &&
						user.getSalesPoint().getSalePointId().equals
							(selectedSalesPoint.getSalePointId())){
					user.setSalesPoint(null);
					userService.updateUser(user);
				}
			}
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							LocalizationsUtils.getText("info", context),
							LocalizationsUtils.getText("salespoint_deleted", context) + " "
									+ selectedSalesPoint.getName());
			context.addMessage(null, msg);
			selectedSalesPoint = null;
			CitySelectOneMenuView.setStaticCity(null);
			warehouseBean.setWarehouse(null);
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							LocalizationsUtils.getText("error", context),
							LocalizationsUtils.getText("salespoint_delete_error", context));
			context.addMessage(null, msg);
			e.printStackTrace();
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
						.findWarehouseByWarehouseName(warehouseBean
								.getWarehouse().getWarehouseName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (warehouseCheck == null) {
				warehouseCheck = new WarehouseVO();
				warehouseCheck.setWarehouseName(warehouseBean
						.getWarehouse().getWarehouseName());
				warehouseCheck.generateWarehouseId();
				warehouseService.saveWarehouse(warehouseCheck);
				selectedSalesPoint.setWarehouse(warehouseCheck);
			} else {
				selectedSalesPoint.setWarehouse(warehouseCheck);
				if(!warehouseCheck.getWarehouseName().equals(warehouseBean
						.getWarehouse())){
					warehouseService.updateWarehouse(warehouseCheck);
				}
			}
			
			CitySelectOneMenuView.setStaticCity(null);
			warehouseBean.setWarehouse(null);
			selectedSalesPoint.setName(updateSalesPointName);
			selectedSalesPoint.setSalePointPhoneNumber(updatePhoneNumber);
			salesPointService.saveSalePoint(selectedSalesPoint);
			warehouseBean.updateWarehouseList();

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
	
	public void employees(){
		for(UserVO u : users.getTarget()){
			u.setSalesPoint(selectedSalesPoint);
			userService.updateUser(u);
		}
		
		for(UserVO u : users.getSource()){
			if(u.getSalesPoint() != null && u.getSalesPoint().getSalePointId().
					equals(selectedSalesPoint.getSalePointId())){
						u.setSalesPoint(null);
						userService.updateUser(u);
			}
		}
	}
	
	public void clearSelection(CloseEvent event){ 
		selectedSalesPoint = null;
		CitySelectOneMenuView.setStaticCity(null);
		warehouseBean.setWarehouse(null);
		updateSalesPointName = null;
		updateStreetName = null;
		updateHouseNumber = null;
		updatePhoneNumber = null;
		updateZipCode = null;
	}
	
	public void updateUserList(){
    	allUserList = userService.getUserList();
    	if(selectedSalesPoint != null){
    		distributeUsers();
    		users.setSource(userSourceList);
        	users.setTarget(userTargetList);
    	} else {
    		users.setSource(allUserList);
    		userTargetList.clear();
    		users.setTarget(userTargetList);
    	}
    }
	
	private void distributeUsers(){
		userTargetList.clear();
		userSourceList.clear();
		for(UserVO user : allUserList){
        	if(user.getSalesPoint() != null && selectedSalesPoint != null &&
        			user.getSalesPoint().getSalePointId().equals(selectedSalesPoint.getSalePointId())){
        		userTargetList.add(user);
        	}
        	else{
        		userSourceList.add(user);	
        	}
        }
	}
	
	public String workplace(UserVO user){
		if(user.getSalesPoint() != null){
			return user.getSalesPoint().getName();
		}
		
		FacesContext context = FacesContext.getCurrentInstance();		
		return LocalizationsUtils.getText("nowhere", context);
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
		SalesPointController.selectedSalesPoint = selectedSalesPoint;
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
	
	public static SalesPointVO getStaticSelectedSalesPoint(){
		return selectedSalesPoint;
	}

	public DualListModel<UserVO> getUsers() {
		updateUserList();
		return users;
	}

	public void setUsers(DualListModel<UserVO> users) {
		this.users = users;
	}

	public List<UserVO> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(List<UserVO> allUserList) {
		this.allUserList = allUserList;
	}

	public List<UserVO> getUserSourceList() {
		return userSourceList;
	}

	public void setUserSourceList(List<UserVO> userSourceList) {
		this.userSourceList = userSourceList;
	}

	public List<UserVO> getUserTargetList() {
		return userTargetList;
	}

	public void setUserTargetList(List<UserVO> userTargetList) {
		this.userTargetList = userTargetList;
	}

	public UserListService getUserListBean() {
		return userListBean;
	}

	public void setUserListBean(UserListService uls) {
		this.userListBean = uls;
	}

	public WarehouseSelectOneMenuView getWarehouseBean() {
		return warehouseBean;
	}

	public void setWarehouseBean(WarehouseSelectOneMenuView warehouseBean) {
		this.warehouseBean = warehouseBean;
	}
	
	

}
