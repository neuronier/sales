package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Collection;

public class UserVO implements Serializable {

	private static final long serialVersionUID = -1465616249735667174L;

	private Long id;

	private String name;

	private String userId;

	private String userName;

	private String password;

	private String email;

	private String phoneNumber;

	private Collection<RoleVO> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Collection<RoleVO> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleVO> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", userName=" + userName + ", email=" + email + "]";
	}

}
