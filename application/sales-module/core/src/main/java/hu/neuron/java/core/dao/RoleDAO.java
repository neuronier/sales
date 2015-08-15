package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Role;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleDAO extends JpaRepository<Role, Long> {

	@Query("select r from Role r where r.name=?1")
	Role findRoleByName(String name) throws Exception;
	
	Role findRoleByRoleId(@Param("roleId") String roleId);
	
	Page<Role> findByNameStartsWith(String filter, Pageable pageRequest);
	
	

}
