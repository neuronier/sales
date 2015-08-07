package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.List;

public class ClientVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ClientVO(){
	}
	
	private Long identifier;
	
	private String name;
	
	private String clientId;
	
	private String userName;
	
	private String password;
	
	private String emailAddress;

	private String phoneNumber;
	
	private List<AddressVO> billingAddress;
	
	private List<AddressVO> deliveryAddress;
	
	public List<AddressVO> getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(List<AddressVO> billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<AddressVO> getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(List<AddressVO> deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
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

	@Override
	public String toString() {
		return "ClientVO [identifier=" + identifier + ", name=" + name
				+ ", clientId=" + clientId + ", userName=" + userName
				+ ", password=" + password + ", emailAddress=" + emailAddress
				+ ", phoneNumber=" + phoneNumber + ", billingAddress="
				+ billingAddress + ", deliveryAddress=" + deliveryAddress + "]";
	}	
}