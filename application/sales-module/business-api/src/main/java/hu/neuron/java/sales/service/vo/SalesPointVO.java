package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class SalesPointVO implements Serializable{

	private static final long serialVersionUID = -7273059346505981511L;
	
	public SalesPointVO(){}
	
	public SalesPointVO(boolean generateUUID){
		if(generateUUID){
			generateSalePointId();
		}
	}

	private String name;
	
	private Long id;
	
	private String salePointId;
	
	private WarehouseVO warehouse;
	
	private AddressVO address;
	
	private List<UserVO> users;
	
	private String salePointPhoneNumber;
	
	public String getSalePointId() {
		return salePointId;
	}

	public void setSalePointId(String salePointId) {
		this.salePointId = salePointId;
	}

	public AddressVO getAddress() {
		return address;
	}

	public void setAddress(AddressVO address) {
		this.address = address;
	}

	public List<UserVO> getUsers() {
		return users;
	}

	public void setUsers(List<UserVO> users) {
		this.users = users;
	}

	public WarehouseVO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseVO warehouse) {
		this.warehouse = warehouse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long iD) {
		id = iD;
	}

	public String getSalePointPhoneNumber() {
		return salePointPhoneNumber;
	}

	public void setSalePointPhoneNumber(String salePointPhoneNumber) {
		this.salePointPhoneNumber = salePointPhoneNumber;
	}
	
	private void generateSalePointId() {
		salePointId = UUID.randomUUID().toString();
		
	}

}
