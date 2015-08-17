package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address extends Base {

	private static final long serialVersionUID = 1L;

	/**
	 * Irányítószám.
	 */
	private String zipCode;

	/**
	 * Település.
	 */
	private String city;

	/**
	 * Utca.
	 */
	private String street;

	/**
	 * Házszám.
	 */
	private String houseNumber;

	/**
	 * Az entitás azonosítója.
	 */
	private String addressId;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

}
