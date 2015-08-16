package hu.neuron.java.sales.service.webservice.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoleVo")
public class RoleWebServiceVO  implements Serializable {

	private static final long serialVersionUID = -6985943237743277698L;
	
	private String name;

	private String roleId;

	
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
