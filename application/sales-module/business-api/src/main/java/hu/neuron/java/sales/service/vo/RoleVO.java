package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class RoleVO implements Serializable {

	private static final long serialVersionUID = -6985943237743277698L;
	
	private Long id;
	
	private String name;

	private String roleId;

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return name;
	}
	
}