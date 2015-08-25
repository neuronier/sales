package hu.neuron.java.core.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@NamedQueries({
		@NamedQuery(name = "User.findUserByName", query = "SELECT u FROM User u  WHERE u.name = :name"),
		@NamedQuery(name = "User.findUserByUserId", query = "SELECT u FROM User u  WHERE u.userId = :userId"),
		@NamedQuery(name = "User.findUserByUserName", query = "SELECT u FROM User u  WHERE u.userName = :userName")
})
public class User extends Base {

	private static final long serialVersionUID = 1L;

	private String name;

	private String userId;

	private String userName;

	private String password;

	private String email;

	private String phoneNumber;

	private Date registrationDate;

	public User() {
		super();
		this.userId = UUID.randomUUID().toString();
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", getId()=" + getId() + "]";
	}

}
