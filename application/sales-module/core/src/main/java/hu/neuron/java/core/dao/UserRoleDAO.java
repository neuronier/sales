package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface UserRoleDAO extends JpaRepository<UserRole, Long>{

	List<UserRole> findUserRolesByUserId(@Param("userId") String userId);
	
	@Query("select ur from UserRole ur where ur.userId=?1 and ur.roleId=?2")
	UserRole findUserRoleByUserIdAndRoleId(String userId, String roleId);
}
