package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AddressDAO extends JpaRepository<Address, Long>{
	
	Address findAddressByCity(String city) throws Exception;

}
