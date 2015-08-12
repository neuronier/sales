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
	
	private Long userId;
	
	private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
