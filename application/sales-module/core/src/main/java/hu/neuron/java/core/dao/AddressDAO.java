package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.Address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AddressDAO extends JpaRepository<Address, Long>{
	
	List<Address> findAddressByCity(String city) throws Exception;
	
	Address findAddressByAddressId(String addressId) throws Exception;
	
	Page<Address> findByCityStartsWith(String filter, Pageable pageRequest);
	
	@Query("select ad from Address ad where ad.city=?1 and ad.street=?2 and ad.houseNumber=?3 and ad.zipCode=?4")
	Address findAddressByEquals(String city, String street, String houseNumber, String zipCode);

}
