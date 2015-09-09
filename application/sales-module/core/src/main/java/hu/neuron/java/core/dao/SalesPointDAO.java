package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.SalesPoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface SalesPointDAO extends JpaRepository<SalesPoint, Long>{
	
	@Query("select sp from SalesPoint sp where sp.name=?1")
	SalesPoint findSalesPointByName(String name) throws Exception;
	
	Page<SalesPoint> findByNameStartsWith(String filter, Pageable pageRequest);

	SalesPoint findSalesPointByAddressId(String addressId);
	
	SalesPoint findBySalePointId(String salePointId) throws Exception;
}
