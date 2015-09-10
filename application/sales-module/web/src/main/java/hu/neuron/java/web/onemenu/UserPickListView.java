package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.web.controllers.salespoint.SalesPointController;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
 
@ViewScoped
@ManagedBean
public class UserPickListView {
       
    private static DualListModel<UserVO> users;
    private static List<UserVO> allUserList;
    private static List<UserVO> userSourceList;
    private static List<UserVO> userTargetList;
    private static String salesPointId;
     
    @ManagedProperty("#{userListService}")
    private UserListService service;
    
    private static UserListService staticService;
     
    @PostConstruct
    public void init() {
        setAllUserList(service.getUserList());
        staticService = service;
        userSourceList = new LinkedList<>();
        userTargetList = new LinkedList<>();
        distributeUsers();
        setUsers(new DualListModel<UserVO>(userSourceList,userTargetList));
    }
   
    public static void updateUserList(){
    	staticService.updateUserList();
    	allUserList = staticService.getUserList();
    	if(SalesPointController.getStaticSelectedSalesPoint() != null){
    		salesPointId = SalesPointController.getStaticSelectedSalesPoint().getSalePointId();
    		distributeUsers();
    		System.out.println(SalesPointController.getStaticSelectedSalesPoint().getName());
    		users.setSource(userSourceList);
        	users.setTarget(userTargetList);
    	} else {
    		users.setSource(allUserList);
    		userTargetList.clear();
    		users.setTarget(userTargetList);
    	}
    }


	public DualListModel<UserVO> getUsers() {
		updateUserList();
		return users;
	}
	
	public static DualListModel<UserVO> getStaticUsers() {
		return users;
	}


	public void setUsers(DualListModel<UserVO> users) {
		UserPickListView.users = users;
	}


	public List<UserVO> getUserSourceList() {
		return userSourceList;
	}


	public void setUserSourceList(List<UserVO> userSourceList) {
		UserPickListView.userSourceList = userSourceList;
	}


	public List<UserVO> getUserTargetList() {
		return userTargetList;
	}


	public void setUserTargetList(List<UserVO> userTargetList) {
		UserPickListView.userTargetList = userTargetList;
	}


	public static List<UserVO> getAllUserList() {
		return allUserList;
	}


	public static void setAllUserList(List<UserVO> allUserList) {
		UserPickListView.allUserList = allUserList;
	}

	public String getSalesPointId() {
		return salesPointId;
	}


	public void setSalesPointId(String salesPointId) {
		UserPickListView.salesPointId = salesPointId;
	}
	
	private static void distributeUsers(){
		userTargetList.clear();
		userSourceList.clear();
		for(UserVO user : getAllUserList()){
        	if(user.getSalesPoint() != null && 
        			user.getSalesPoint().getSalePointId().equals(salesPointId)){
        		userTargetList.add(user);		
        	}
        	else{
        		userSourceList.add(user);	
        	}
        }
	}


	public UserListService getService() {
		return service;
	}


	public void setService(UserListService service) {
		this.service = service;
	}


	public static UserListService getStaticService() {
		return staticService;
	}


	public static void setStaticService(UserListService staticService) {
		UserPickListView.staticService = staticService;
	}
	
	
}