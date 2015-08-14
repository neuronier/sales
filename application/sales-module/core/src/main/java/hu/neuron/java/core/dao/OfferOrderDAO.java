package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.OfferOrderEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OfferOrderDAO extends JpaRepository<OfferOrderEntity, Long>{

	List<OfferOrderEntity> findOfferOrderEntityByOfferId(@Param("offerId") Long offerId) throws Exception;
	
	List<OfferOrderEntity> findOfferOrderEntityByOrderId(@Param("orderId") Long orderId) throws Exception;
	
	@Query("select oo from OfferOrderEntity oo where oo.offerId=?1 and oo.orderId=?2")
	OfferOrderEntity findOfferOrderEntityByOfferIdAndOrderId(Long offerId, Long orderId);
}
