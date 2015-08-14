package hu.neuron.java.sales.service.finder;

import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.dao.OfferProductTypeDAO;
import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.core.entity.OfferProductTypeEntity;
import hu.neuron.java.sales.service.converter.OfferConverter;
import hu.neuron.java.sales.service.converter.ProductTypeConverter;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.stereotype.Component;

@Component
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferProductTypeFinder implements Serializable{

	private static final long serialVersionUID = -4176449294112314466L;
	
	@Autowired
	private OfferDAO offerDao;
	
	@Autowired
	private OfferProductTypeDAO optDao;
	
	@Autowired
	private ProductTypeDAO productDao;
	
	@Autowired
	ProductTypeConverter ptConverter;
	
	@Autowired
	OfferConverter ofConverter;
	
	public OfferProductTypeFinder(){}
	
	/**
	 * A dobott kivétel SpringData-s
	 * */
	public List<ProductTypeVO> findProductTypes(OfferVO offer) throws Exception{
		
		List<OfferProductTypeEntity> opteList = optDao.findOfferProductTypeEntityByOfferId(offer.getOfferId());
		Set<ProductTypeVO> rv = new HashSet<>();
		for(OfferProductTypeEntity entity : opteList){
			rv.add(ptConverter.toVO(productDao.findProductTypeEntityByProductTypeId(entity.getProductTypeId())));
		}
		
		return Arrays.asList(rv.toArray(new ProductTypeVO[rv.size()]));
	}
	
	public List<OfferVO> findOffers(ProductTypeVO productType) throws Exception{
		
		List<OfferProductTypeEntity> opteList = optDao.findOfferProductTypeEntityByProductTypeId(productType.getProductTypeId());
		Set<OfferVO> rv = new HashSet<>();
		for(OfferProductTypeEntity entity : opteList){
			rv.add(ofConverter.toVO(offerDao.findOfferEntityByOfferId(entity.getOfferId())));
		}
		
		return Arrays.asList(rv.toArray(new OfferVO[rv.size()]));
	}

}
