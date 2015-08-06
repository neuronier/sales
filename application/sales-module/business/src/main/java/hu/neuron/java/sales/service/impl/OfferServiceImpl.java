package hu.neuron.java.sales.service.impl;

import java.io.Serializable;
import java.util.List;

import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.converter.OfferConverter;
import hu.neuron.java.sales.service.vo.OfferVO;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Stateless(mappedName = "OfferService",name = "OfferService")
@Remote(OfferServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OfferServiceImpl implements OfferServiceRemote, Serializable {
 
	private static final long serialVersionUID = 8870541031617767500L;

	@EJB
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
}
