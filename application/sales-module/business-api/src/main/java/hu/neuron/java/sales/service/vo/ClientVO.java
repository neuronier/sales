package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class ClientVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long Id;
	
	private String name;
	
	private String clientId;
	
	private String userName;
	
	private String password;
	
	private String emailAddress;

	private String phoneNumber;
	
	
	public ClientVO(){
		super();
		setClientId(UUID.randomUUID().toString());
	}


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}

}