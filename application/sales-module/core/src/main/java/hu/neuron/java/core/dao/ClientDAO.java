package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientDAO extends JpaRepository<Client, Long> {

	Client findClientByUserName(String userName);
	
	Client findByClientId(String clientId);

	List<Client> findByuserNameStartsWith(String filter);
}
