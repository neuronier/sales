package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.converter.OfferConverter;
import hu.neuron.java.sales.service.vo.OfferVO;

import java.io.Serializable;
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
	OfferDAO offerDao;
	
	@Override
	public void saveOffer(OfferVO offer) {
		offerDao.save(offerConverter.toEntity(offer));
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
	public OfferVO findOfferEntityByName(String name) throws Exception {
		OfferVO rVO = offerConverter.toVO(offerDao.findOfferEntityByName(name));
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
}
