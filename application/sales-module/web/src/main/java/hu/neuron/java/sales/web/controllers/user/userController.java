package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "userController")
public class userController {

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

		initRoleList();
	}

	public void initRoleList() {
		List<RoleVO> sourceRole = roleServiceRemote.getRoles();
		List<String> source = new ArrayList<>();

		for (RoleVO role : sourceRole) {
			source.add(role.getName());
		}

		List<String> target = new ArrayList<>();
		roleList = new DualListModel<String>(source, target);
	}

	public void addNewUserBtnAction() {
		initRoleList();
		newUser = new UserVO();
	}

	public void editUserBtnAction() {
		initRoleList();
		List<RoleVO> sourceRole = roleServiceRemote.getRoles();
		List<String> source = new ArrayList<>();
		List<RoleVO> targetRole = userServiceRemote.findRolesToUser(selectedUser);
		List<String> target = new ArrayList<>();

		for (RoleVO role : targetRole) {
			target.add(role.getName());
		}

		for (RoleVO role : sourceRole) {
			if (!target.contains(role.getName())) {
				source.add(role.getName());
			}
		}

		roleList = new DualListModel<String>(source, target);
	}

	public void addNewUser() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encPassword = bCryptPasswordEncoder.encode(userServiceRemote.getDefaultPassword());

		newUser.setPassword(encPassword);
		newUser.setRegistrationDate(new Date());

		userServiceRemote.saveUser(newUser);

		List<String> target = roleList.getTarget();

		for (String roleName : target) {
			RoleVO role = null;
			try {
				role = roleServiceRemote.getRoleByName(roleName);
			} catch (Exception e) {
				// Nincs ilyen role
				e.printStackTrace();
			}
			userServiceRemote.addRoleToUser(newUser, role);
		}

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, LocalizationsUtils.getText("user_info", context), LocalizationsUtils.getText(
				"user_new_user_created", context) + ": " + newUser.getUserName());
		context.addMessage(null, msg);

	}

	public void editUser() {
		userServiceRemote.saveUser(selectedUser);
		
		List<String> target = roleList.getTarget();

		for (String roleName : target) {
			RoleVO role = null;
			try {
				role = roleServiceRemote.getRoleByName(roleName);
			} catch (Exception e) {
				// Nincs ilyen role
				e.printStackTrace();
			}
			userServiceRemote.addRoleToUser(selectedUser, role);
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, LocalizationsUtils.getText("user_info", context), LocalizationsUtils.getText(
				"user_user_edited_successfuly", context) + ": " + selectedUser.getUserName());
		context.addMessage(null, msg);
		
		selectedUser = null;
	}

	public void removeUser() {
		try {
			userServiceRemote.removeUser(selectedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, LocalizationsUtils.getText("user_info", context), LocalizationsUtils.getText(
				"user_user_removed", context) + ": " + selectedUser.getUserName());
		context.addMessage(null, msg);
		
		selectedUser = null;
	}

	public boolean isDefaultPasswordUsed() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		try {
			UserVO currentUser = userServiceRemote.findUserByUserName(user.getUsername());
			if (bCryptPasswordEncoder.matches(userServiceRemote.getDefaultPassword(), currentUser.getPassword())) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getLocalizedRole(String role){
		FacesContext context = FacesContext.getCurrentInstance();
		String rv;
		try {
			rv = LocalizationsUtils.getText("user_"+role.toLowerCase(), context);
			return rv;
		}catch(MissingResourceException ex){
			return role;
		}
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
