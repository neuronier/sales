package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class AddressVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037424070007872953L;
	
	private String zipCode;
	
	private String city;
	
	private String street;
	
	private String houseNumber;
	
	private String addressId;
	
	public AddressVO() {}
	
	public AddressVO(boolean generateUUID) {
		if(generateUUID){
			generateAddressId();
		}
	}

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

	public void setAddressId(String addresId) {
		this.addressId = addresId;
	}
	
	public void generateAddressId(){
		this.addressId = UUID.randomUUID().toString();
	}
}
