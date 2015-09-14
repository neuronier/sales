package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientOfferDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.dao.OfferProductTypeDAO;
import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.sales.service.OfferProductTypeServiceRemote;
import hu.neuron.java.sales.service.converter.OfferProductTypeConverter;
import hu.neuron.java.sales.service.converter.ProductTypeConverter;
import hu.neuron.java.sales.service.vo.OfferProductTypeVO;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OfferProductTypeService",name = "OfferProductTypeService")
@Remote(OfferProductTypeServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferProductTypeServiceImpl implements OfferProductTypeServiceRemote, Serializable {
 
	private static final long serialVersionUID = 8870541031617767500L;

	@Autowired
	OfferProductTypeConverter optConv;
	
	@Autowired
	ProductTypeConverter productTypeConverter;
	
	@Autowired
	OfferDAO offerDao;
	
	@Autowired
	ClientOfferDAO clientOfferDao;
	
	@Autowired
	OfferProductTypeDAO offerProductTypeDao;
	
	@Autowired
	ProductTypeDAO productTypeDao;

	@Override
	public List<OfferProductTypeVO> findOfferProductTypeByOfferId(
			String offerId) throws Exception {
		return optConv.toVO(offerProductTypeDao.
					findOfferProductTypeEntityByOfferId(offerId));
	}

	@Override
	public List<OfferProductTypeVO> findOfferProductTypeByProductTypeId(
			String productTypeId) throws Exception {
		
		return optConv.toVO(offerProductTypeDao.
					findOfferProductTypeEntityByProductTypeId(productTypeId));
	}

	@Override
	public OfferProductTypeVO findOfferProductTypeByOfferIdAndProductTypeId(
			String offerId, String productTypeId) {
		return optConv.toVO(offerProductTypeDao.
					findOfferProductTypeEntityByOfferIdAndProductTypeId(offerId, productTypeId));
	}
	
	
}
