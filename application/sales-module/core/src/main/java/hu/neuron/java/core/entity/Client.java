package hu.neuron.java.core.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Client")
public class Client extends Base {

	private static final long serialVersionUID = 1L;

	/**
	 * Az ügyfél neve.
	 */
	private String name;

	/**
	 * Az ügyfél azonosítója.
	 */
	private String clientId;

	/**
	 * Az ügyfél felhasználóneve.
	 */
	private String userName;

	/**
	 * Az ügyfél jelszava.
	 */
	private String password;

	/**
	 * Az ügyfél e-mail címe.
	 */
	private String emailAddress;

	/**
	 * Az ügyfél telefonszáma.
	 */
	private String phoneNumber;

	/**
	 * Az ügyfélhez tartozó számlázási címek.
	 */

	public Client(){
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

}
