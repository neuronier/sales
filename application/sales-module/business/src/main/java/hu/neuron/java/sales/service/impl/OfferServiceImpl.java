package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientOfferDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.dao.OfferProductTypeDAO;
import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.core.entity.ClientOffer;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.core.entity.OfferProductTypeEntity;
import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.converter.OfferConverter;
import hu.neuron.java.sales.service.converter.ProductTypeConverter;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OfferService",name = "OfferService")
@Remote(OfferServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferServiceImpl implements OfferServiceRemote, Serializable {
 
	private static final long serialVersionUID = 8870541031617767500L;

	@Autowired
	OfferConverter offerConverter;
	
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
	public OfferVO saveOffer(OfferVO offerVO) {
		OfferEntity offer = offerDao.save(offerConverter.toEntity(offerVO));
		return offerConverter.toVO(offer);
	}

	@Override
	public void updateOffer(OfferVO offer) {
		offerDao.save(offerConverter.toEntity(offer));
	}

	@Override
	public void removeOffer(OfferVO offer) {
		offerDao.delete(offerConverter.toEntity(offer));	
	}

	@Override
	public List<OfferVO> getOffers(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new Sort.Order(dir, sortField)));
		Page<OfferEntity> entities;
		
		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = offerDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = offerDao.findAll(pageRequest);
		} 

		List<OfferVO> ret = offerConverter.toVO(entities.getContent());

		return ret;	
	}

	@Override
	public OfferVO findOfferEntityByName(String name){
		OfferVO rVO = null;
		try {
			rVO = offerConverter.toVO(offerDao.findOfferEntityByName(name));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rVO;
	}

	@Override
	public List<OfferVO> findAll() {
		List<OfferVO> rvList = offerConverter.toVO(offerDao.findAll());
		return rvList;
	}

	@Override
	public int getRowNumber() {
		return (int) offerDao.count();
	}



	@Override
	public void addProductTypeToOffer(OfferVO offer, ProductTypeVO productType, int quantity) {
		OfferProductTypeEntity offerProductType = new OfferProductTypeEntity();
		offerProductType.setProductTypeId(productType.getProductTypeId());
		offerProductType.setOfferId(offer.getOfferId());
		offerProductType.setQuantity(quantity);	
		offerProductTypeDao.save(offerProductType);
	}

	@Override
	public void removeProductTypeFromOffer(OfferVO offer, 
			ProductTypeVO productType) {
		OfferProductTypeEntity offerProductType = offerProductTypeDao.findOfferProductTypeEntityByOfferIdAndProductTypeId(offer.getOfferId(), productType.getProductTypeId());
		offerProductTypeDao.delete(offerProductType);
	}

	@Override
	public List<ProductTypeVO> findProductTypesToOffer(OfferVO offerVo) throws Exception {
		List<OfferProductTypeEntity> offerProductTypeList = offerProductTypeDao.findOfferProductTypeEntityByOfferId(offerVo.getOfferId());
		
		List<String> productTypeIdList = new ArrayList<>();
		List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
		
		for (OfferProductTypeEntity opt : offerProductTypeList) {
			productTypeIdList.add(opt.getProductTypeId());
		}
		
		for (String productTypeId : productTypeIdList) {
			productTypeEntities.add(productTypeDao.findProductTypeEntityByProductTypeId(productTypeId));
		}
		
		return productTypeConverter.toVO(productTypeEntities);
	}

	@Override
	public int findQuantityToOfferProductType(OfferVO offer,
			ProductTypeVO productType) {
		return offerProductTypeDao.findOfferProductTypeEntityByOfferIdAndProductTypeId(offer.getOfferId(), productType.getProductTypeId()).getQuantity();	
	}

	@Override
	public OfferVO findOfferEntityByOfferId(String offerId) {
		OfferVO rVO = null;
		try {
			rVO = offerConverter.toVO(offerDao.findOfferEntityByOfferId(offerId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rVO;
	}

	@Override
	public void removeOfferById(String offerId) {
		OfferEntity offer = null;
		try {
			offer = offerDao.findOfferEntityByOfferId(offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		offerDao.delete(offer);	
	}

	@Override
	public void removeProductTypesFromOffer(OfferVO offer,
			List<ProductTypeVO> productTypes) {
		for (ProductTypeVO productTypeVO : productTypes) {
			OfferProductTypeEntity offerProductType = offerProductTypeDao.findOfferProductTypeEntityByOfferIdAndProductTypeId(offer.getOfferId(), productTypeVO.getProductTypeId());
			offerProductTypeDao.delete(offerProductType); 
		}
	}

	@Override
	public void removeAllProductTypeFromOffer(OfferVO offer) {
		List<ProductTypeVO> productTypes = null;
		try {
			productTypes = findProductTypesToOffer(offer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ProductTypeVO productTypeVO : productTypes) {
			OfferProductTypeEntity offerProductType = offerProductTypeDao.findOfferProductTypeEntityByOfferIdAndProductTypeId(offer.getOfferId(), productTypeVO.getProductTypeId());
			offerProductTypeDao.delete(offerProductType); 
		}
	}

	@Override
	public List<OfferVO> findTopOffers(int limit) throws Exception{
		List<OfferVO> topOffers = new ArrayList<>();
		List<ClientOffer> offers = clientOfferDao.findTopOffers(limit);
		
		for (ClientOffer clientOffer : offers) {
			OfferEntity findOfferEntityByOfferId = offerDao.findOfferEntityByOfferId(clientOffer.getOfferId());
			OfferVO offer = offerConverter.toVO(findOfferEntityByOfferId);
			topOffers.add(offer);
		}
		
		return topOffers;
	}
}
