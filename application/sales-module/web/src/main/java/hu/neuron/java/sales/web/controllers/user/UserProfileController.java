package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.UserVO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "userProfileController")
public class UserProfileController {

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;
	
	
	
	private UserVO currentUser;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String oldPassword;
	
	
	@PostConstruct
	public void init(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    
		try {
			currentUser = userService.findUserByUserName(user.getUsername());
			
			name = currentUser.getName();
			userName = currentUser.getUserName();
			email = currentUser.getEmail();
			password = "";
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR!", "We can't load your details."));
		} 
	}
	
	public void saveChanges(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if(bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			currentUser.setEmail(email);
			if(!password.isEmpty()){
				
				String encPassword = bCryptPasswordEncoder.encode(password);
				currentUser.setPassword(encPassword);
				
			}
			userService.saveUser(currentUser);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Your details are saved succesfully."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Wrong old password!"));
		}

	}


	public UserServiceRemote getUserService() {
		return userService;
	}


	public void setUserService(UserServiceRemote userService) {
		this.userService = userService;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
	
}
