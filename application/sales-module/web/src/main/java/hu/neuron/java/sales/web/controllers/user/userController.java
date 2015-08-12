package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "userController")
public class userController {

	private final String defaultPassword = "welcome1";

	private LazyUserModel lazyUserModel;

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userServiceRemote;

	@EJB(name = "RoleService", mappedName = "RoleService")
	private RoleServiceRemote roleServiceRemote;

	private UserVO selectedUser;
	private UserVO newUser;

	private DualListModel<String> roleList;
	
	public userController() {
		super();
	}

	@PostConstruct
	public void init() {
		setLazyUserModel(new LazyUserModel(userServiceRemote, roleServiceRemote));

		List<RoleVO> sourceRole = roleServiceRemote.getRoles();
		List<String> source = new ArrayList<>();

		for (RoleVO role : sourceRole) {
			source.add(role.getName());
		}

		List<String> target = new ArrayList<>();
		roleList = new DualListModel<String>(source, target);
	}

	
	public void addNewUserBtnAction() {
		newUser = new UserVO();
	}
	
	public void editUserBtnAction() {
		List<RoleVO> sourceRole = roleServiceRemote.getRoles();
		List<String> source = new ArrayList<>();
		List<RoleVO> targetRole = userServiceRemote.findRolesToUser(selectedUser);
		List<String> target = new ArrayList<>();
		
		for (RoleVO role : targetRole) {
			target.add(role.getName());
		}
		
		for (RoleVO role : sourceRole) {
			if(!target.contains(role.getName())){
				source.add(role.getName());
			}
		}

		roleList = new DualListModel<String>(source, target);
	}

	public void addNewUser() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encPassword = bCryptPasswordEncoder.encode(defaultPassword);

		newUser.setPassword(encPassword);

		userServiceRemote.saveUser(newUser);

		try {
			newUser = userServiceRemote.findUserByUserName(newUser.getUserName());
			List<String> target = roleList.getTarget();

			for (String roleName : target) {
				RoleVO role = roleServiceRemote.getRoleByName(roleName);
				userServiceRemote.addRoleToUser(newUser, role);
			}
			
		} catch (Exception e) {
			newUser = null;
		}

	}

	public void editUser() {
		userServiceRemote.saveUser(selectedUser);
	}

	public void removeUser() {
		userServiceRemote.removeUser(selectedUser);
	}

	public LazyUserModel getLazyUserModel() {
		return lazyUserModel;
	}

	public void setLazyUserModel(LazyUserModel lazyUserModel) {
		this.lazyUserModel = lazyUserModel;
	}

	public UserVO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserVO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public UserServiceRemote getUserServiceRemote() {
		return userServiceRemote;
	}

	public void setUserServiceRemote(UserServiceRemote userServiceRemote) {
		this.userServiceRemote = userServiceRemote;
	}

	public RoleServiceRemote getRoleServiceRemote() {
		return roleServiceRemote;
	}

	public void setRoleServiceRemote(RoleServiceRemote roleServiceRemote) {
		this.roleServiceRemote = roleServiceRemote;
	}

	public UserVO getNewUser() {
		return newUser;
	}

	public void setNewUser(UserVO newUser) {
		this.newUser = newUser;
	}

	public DualListModel<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(DualListModel<String> roleList) {
		this.roleList = roleList;
	}
	
	
}
