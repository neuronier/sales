package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.List;

public interface OfferServiceRemote {

	public OfferVO saveOffer(OfferVO offerVO);
	
	public void updateOffer(OfferVO offer);
	  
	public void removeOffer(OfferVO offer);
	
	public void removeOfferById(String offerId);
	
	public OfferVO findOfferEntityByName(String name);
	
	public OfferVO findOfferEntityByOfferId(String offerId);
	
	public List<OfferVO> getOffers(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
	
	public List<OfferVO> findAll();

	public int getRowNumber();
	
	public List<ProductTypeVO> findProductTypesToOffer(OfferVO offerVo) throws Exception;
	
	public void addProductTypeToOffer(OfferVO offer, ProductTypeVO productType, int quantity);
	
	public void removeProductTypeFromOffer(OfferVO offer, ProductTypeVO productType);
	
	public void removeProductTypesFromOffer(OfferVO offer, List<ProductTypeVO> productType);
	
	public void removeAllProductTypeFromOffer(OfferVO offer);
	
	public int findQuantityToOfferProductType(OfferVO offer, ProductTypeVO productType);
	
	public List<OfferVO> findTopOffers(int limit) throws Exception;
}

