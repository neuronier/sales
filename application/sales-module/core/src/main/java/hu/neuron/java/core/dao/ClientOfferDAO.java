package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.ClientOffer;

import java.util.Date;
import java.util.List;

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
	
	List<ClientOffer> findClientOfferByClientIdAndDate(String clientId, Date date)throws Exception;
	
	@Query("select COUNT(co) from ClientOffer co where YEAR(co.date) = ?1 AND MONTH(co.date) = ?2")
	int findCountByMonth(int year, int month);
	
	@Query("select co from ClientOffer co where YEAR(co.date) = ?1 AND MONTH(co.date) = ?2")
	List<ClientOffer> findByMonth(int year, int month);

	@Query(value = "select * from clientoffer group by offerId order by count(*) desc limit ?1", nativeQuery = true)
	List<ClientOffer> findTopOffers(int limit);

	@Query("select COUNT(co) from ClientOffer co where co.offerId = ?1")
	int findCountByOfferId(String offerId);
	
	@Query(value = "select IFNULL(SUM(o.offerPrice),0) from clientoffer co INNER JOIN offer o ON co.offerId = o.offerId;", nativeQuery = true)
	int findTotalIncome();
}
