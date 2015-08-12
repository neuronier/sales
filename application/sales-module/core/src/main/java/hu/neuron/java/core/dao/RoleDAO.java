package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleDAO extends JpaRepository<Role, Long> {

	@Modifying
	@Query(value = "insert into user_role_sw (roleId, userId) VALUES (?1, ?2)", nativeQuery = true)
	void addRoleToUser(Long roleId, Long userId) throws Exception;

	@Modifying
	@Query(value = "delete from user_role_sw where roleId=?1 and userId=?2", nativeQuery = true)
	void removeRoleFromUser(Long roleId, Long userId) throws Exception;

	@Query("select r from Role r where r.name=?1")
	Role findRoleByName(String name) throws Exception;
	
	Page<Role> findByNameStartsWith(String filter, Pageable pageRequest);

}
