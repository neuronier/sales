package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.List;

public class SalesPointVO implements Serializable{

	private static final long serialVersionUID = -7273059346505981511L;
	
	public SalesPointVO(){}
	
	private String name;
	
	private Long identifier;
	
	private WarehouseVO warehouse;
	
	private AddressVO address;
	
	private List<UserVO> users;
	
	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
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
	
}
