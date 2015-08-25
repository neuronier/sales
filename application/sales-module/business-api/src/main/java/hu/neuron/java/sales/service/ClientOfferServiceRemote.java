package hu.neuron.java.sales.service;

import java.util.Date;
import java.util.List;

import hu.neuron.java.sales.service.vo.ClientOfferVO;

public interface ClientOfferServiceRemote {
	
	public void saveClientOffer(ClientOfferVO purchase) throws Exception;
	
	public void updateClientOffer(ClientOfferVO purchase) throws Exception;
	
	public void removeClientOffer(ClientOfferVO purchase) throws Exception;
	
	public List<ClientOfferVO> findAll() throws Exception;
	
	public ClientOfferVO findClientOfferByClientIdAndDate(String clientId, Date date) throws Exception;
	
	public List<ClientOfferVO> findClientOfferByClientIdAndOfferId(String clientId, String offerId) throws Exception;
	
	public List<ClientOfferVO> findClientOfferByClientId(String clientId) throws Exception;
	
	public List<ClientOfferVO> findClientOfferByOfferId(String offerId) throws Exception;
}
