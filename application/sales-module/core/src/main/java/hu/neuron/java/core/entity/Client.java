package hu.neuron.java.core.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client extends Base {

	private static final long serialVersionUID = 1L;

	private String name;

	private String clientId;

	private String userName;

	private String password;

	private String emailAddress;

	private String phoneNumber;

	public Client() {
		super();
		setClientId(UUID.randomUUID().toString());
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	// public List<Address> getBillingAddress() {
	// return billingAddress;
	// }
	//
	// public void setBillingAddress(List<Address> billingAddress) {
	// this.billingAddress = billingAddress;
	// }
	//
	// public List<Address> getDeliveryAddress() {
	// return deliveryAddress;
	// }
	//
	// public void setDeliveryAddress(List<Address> deliveryAddress) {
	// this.deliveryAddress = deliveryAddress;
	// }

}
