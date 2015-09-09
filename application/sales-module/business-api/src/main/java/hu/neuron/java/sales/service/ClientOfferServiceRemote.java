package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ClientOfferVO;

import java.util.Date;
import java.util.List;

public interface ClientOfferServiceRemote {
	
	public void saveClientOffer(ClientOfferVO purchase) throws Exception;
	
	public void updateClientOffer(ClientOfferVO purchase) throws Exception;
	
	public void removeClientOffer(ClientOfferVO purchase) throws Exception;
	
	public List<ClientOfferVO> findAll() throws Exception;
	
	public List<ClientOfferVO> findClientOfferByClientIdAndDate(String clientId, Date date) throws Exception;
	
	public List<ClientOfferVO> findClientOfferByClientIdAndOfferId(String clientId, String offerId) throws Exception;
	
	public int findCountByMonth(int year, int month);
	
	public int findCountByInterval(Date from, Date to);
	
	public int findIncomeByMonth(int year, int month) throws Exception;
	
	public int findIncomeByInterval(Date from, Date to) throws Exception;
	
	public int findCountByOfferId(String offerId);
	
	public int count();
	
	public int findTotalIncome();

	public List<ClientOfferVO> findClientOfferByClientId(String clientId) throws Exception;
	
	public List<ClientOfferVO> findClientOfferByOfferId(String offerId) throws Exception;
	
	public int findIncomeBySalesPointIdInDateInterval(String salesPointId, Date from, Date to) throws Exception;
}
