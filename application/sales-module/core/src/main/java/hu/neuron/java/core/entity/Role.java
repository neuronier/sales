package hu.neuron.java.core.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
@NamedQueries({
	@NamedQuery(name = "User.findRoleByRoleId", query = "SELECT r FROM Role r  WHERE r.roleId = :roleId")
})
public class Role extends Base {

	private static final long serialVersionUID = 1L;

	private String name;

	private String roleId;

	public Role() {
		super();
		this.roleId = UUID.randomUUID().toString();
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
		return "Role [name=" + name + ", getId()=" + getId() + "]";
	}
}
