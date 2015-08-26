package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientOfferDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.entity.ClientOffer;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.converter.ClientOfferConverter;
import hu.neuron.java.sales.service.vo.ClientOfferVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ClientOfferService", name = "ClientOfferService")
@Remote(ClientOfferServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientOfferServiceImpl implements ClientOfferServiceRemote, Serializable {

	private static final long serialVersionUID = -181586732036315166L;
	
	@Autowired
	ClientOfferDAO clientOfferDAO;
	
	@Autowired
	OfferDAO offerDAO;

	@Autowired
	ClientOfferConverter coConv;
	
	@Autowired
	ClientOfferDAO coDao;
	
	
	@Override
	public void saveClientOffer(ClientOfferVO purchase) {
		purchase.createId();
		coDao.save(coConv.toEntity(purchase));
	}

	@Override
	public void updateClientOffer(ClientOfferVO purchase) {
		saveClientOffer(purchase);
	}

	@Override
	public void removeClientOffer(ClientOfferVO purchase) {
		coDao.delete(coConv.toEntity(purchase));
	}

	@Override
	public List<ClientOfferVO> findAll() {
		return coConv.toVO(coDao.findAll());
	}

	@Override
	public List<ClientOfferVO> findClientOfferByClientIdAndDate(String clientId,
			Date date) throws Exception {
		
		return coConv.toVO(coDao.findClientOfferByClientIdAndDate(clientId, date));
	}

	@Override
	public List<ClientOfferVO> findClientOfferByClientId(String clientId) throws Exception {
		return coConv.toVO(coDao.findClientOfferByClientId(clientId));
	}

	@Override
	public List<ClientOfferVO> findClientOfferByOfferId(String offerId) throws Exception {
		return coConv.toVO(coDao.findClientOfferByOfferId(offerId));
	}

	@Override
	public List<ClientOfferVO> findClientOfferByClientIdAndOfferId(String clientId,
			String offerId) throws Exception {
		
		return coConv.toVO(coDao.findClientOfferByClientIdAndOfferId(clientId, offerId));
	}

	@Override
	public int findCountByMonth(int year, int month) {
		return clientOfferDAO.findCountByMonth(year, month);
	}

	@Override
	public int findIncomeByMonth(int year, int month) throws Exception {
		List<ClientOffer> clientOffers = clientOfferDAO.findByMonth(year, month);
		
		int sum = 0;
		for (ClientOffer clientOffer : clientOffers) {
			OfferEntity offer = offerDAO.findOfferEntityByOfferId(clientOffer.getOfferId());
			sum += (int)offer.getOfferPrice();
		}
		
		return sum;
	}

	@Override
	public int findCountByOfferId(String offerId) {
		return clientOfferDAO.findCountByOfferId(offerId);
	}

	@Override
	public int count() {
		return (int)clientOfferDAO.count();
	}

	@Override
	public int findTotalIncome() {
		return clientOfferDAO.findTotalIncome();
	}

}
