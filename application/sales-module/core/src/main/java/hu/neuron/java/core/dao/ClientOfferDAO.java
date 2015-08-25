package hu.neuron.java.core.dao;

import java.util.Date;
import java.util.List;

import hu.neuron.java.core.entity.ClientOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientOfferDAO extends JpaRepository<ClientOffer, Long>{
	
	List<ClientOffer> findClientOfferByClientId(String clientId)throws Exception;

	List<ClientOffer> findClientOfferByOfferId(String offerId) throws Exception;
							
	@Query("select co from ClientOffer co where co.clientId=?1 and co.offerId=?2")
	List<ClientOffer> findClientOfferByClientIdAndOfferId(String clientId, String offerId)throws Exception;
	
	ClientOffer findClientOfferByClientIdAndDate(String clientId, Date date)throws Exception;
	
}
