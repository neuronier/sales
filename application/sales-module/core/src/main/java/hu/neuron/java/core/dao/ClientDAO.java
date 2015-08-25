package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Client;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientDAO extends JpaRepository<Client, Long> {

	Client findByName(String name);
	
	Client findClientByUserName(String userName);
	
	Client findByClientId(String clientId);

	List<Client> findByuserNameStartsWith(String filter);
	
	List<Client> findByRegistrationDateBetween(Date from, Date to);
	
	@Query("select COUNT(c) from Client c where c.registrationDate < ?1")
	int findNumberOfCliensBeforeDate(Date date);
}
