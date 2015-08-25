package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.User;

import java.util.Date;
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
public interface UserDAO extends JpaRepository<User, Long> {

	List<User> findUserByName(@Param("name") String name) throws Exception;
	
	User findUserByUserName(@Param("userName") String userName) throws Exception;
	
	User findUserById(@Param("id") Long id) throws Exception;
	
	User findUserByUserId(@Param("userId") String userId) throws Exception;
	
	Page<User> findByUserNameStartsWith(String filter,Pageable pageable);
	
	List<User> findByRegistrationDateBetween(Date from, Date to);
	
	@Query("select COUNT(u.registrationDate) from User u where u.registrationDate < ?1")
	int findNumberOfUsersBeforeDate(Date date);
}
