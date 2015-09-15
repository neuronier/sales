package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.OfferProductTypeVO;
import java.util.List;

public interface OfferProductTypeServiceRemote {

	List<OfferProductTypeVO> findOfferProductTypeByOfferId(String offerId)throws Exception;

	List<OfferProductTypeVO> findOfferProductTypeByProductTypeId(String productTypeId) throws Exception;
							
	OfferProductTypeVO findOfferProductTypeByOfferIdAndProductTypeId(String offerId, String productTypeId);
}

