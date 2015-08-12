package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role extends Base {

	private static final long serialVersionUID = 1L;

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
		return "Role [name=" + name + ", getId()=" + getId() + "]";
	}
}
