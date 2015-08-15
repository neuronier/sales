package hu.neuron.java.core.dao;


import hu.neuron.java.core.entity.OfferEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferDAO extends JpaRepository<OfferEntity, Long> {
	
	OfferEntity findOfferEntityByName(String name) throws Exception;
	
	OfferEntity findOfferEntityByOfferId(String offerId) throws Exception;
	
	Page<OfferEntity> findByNameStartsWith(String filter, Pageable pageRequest);
}
