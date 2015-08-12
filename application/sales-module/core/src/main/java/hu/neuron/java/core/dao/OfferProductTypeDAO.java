package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.OfferProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferProductTypeDAO extends JpaRepository<OfferProductTypeEntity, Long>{
	
	List<OfferProductTypeEntity> findOfferProductTypeEntityByOfferId(Long offerId)throws Exception;

	List<OfferProductTypeEntity> findOfferProductTypeEntityByProductTypeId(Long productTypeId) throws Exception;
	
}
