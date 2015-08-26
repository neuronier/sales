package hu.neuron.java.sales.service;

import java.util.Date;
import java.util.List;

import hu.neuron.java.sales.service.vo.ClientOfferVO;

public interface ClientOfferServiceRemote {
	
	public void saveClientOffer(ClientOfferVO purchase);
	
	public void updateClientOffer(ClientOfferVO purchase);
	
	public void removeClientOffer(ClientOfferVO purchase);
	
	public List<ClientOfferVO> findAll();
	
	public ClientOfferVO findClientOfferByClientIdAndDate(String clientID, Date date);
	
	public List<ClientOfferVO> findClientOfferByClientId(String clientID);
	
	public List<ClientOfferVO> findClientOfferByOfferId(String offerID);
	
	public int findCountByMonth(int year, int month);
	
	public int findIncomeByMonth(int year, int month) throws Exception;
	
	public int findCountByOfferId(String offerId);
	
	public int count();
	
	public int findTotalIncome();

}
