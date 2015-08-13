package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.OfferVO;

import java.util.List;

public interface OfferServiceRemote {

	public void saveOffer(OfferVO offer);
	
	public void updateOffer(OfferVO offer);
	  
	public void removeOffer(OfferVO offer);
	
	public OfferVO findOfferEntityByName(String name) throws Exception;
	
	public List<OfferVO> getOffers(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
	
	public List<OfferVO> findAll();

	public int getRowNumber();
}
