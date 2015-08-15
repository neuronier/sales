package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "user_role_sw")
@NamedQueries({
	@NamedQuery(name = "UserRole.findUserRolesByUserId", query = "SELECT ur FROM UserRole ur  WHERE ur.userId = :userId")
})
public class UserRole extends Base {
	
	private static final long serialVersionUID = -8340334376160672233L;
	
	private String userId;
	
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
