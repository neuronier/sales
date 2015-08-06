package hu.neuron.java.sales.service;

import java.util.List;

import hu.neuron.java.sales.service.vo.OfferVO;

public interface OfferServiceRemote {

	public void saveOffer(OfferVO offer);
	
	public void updateOffer(OfferVO offer);
	  
	public void removeOffer(OfferVO offer);
	
	public List<OfferVO> getOffers(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
}
