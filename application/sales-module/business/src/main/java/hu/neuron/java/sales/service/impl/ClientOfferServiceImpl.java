package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientOfferDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.entity.ClientOffer;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.ClientOfferServiceRemote;
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

	@Autowired
	ClientOfferDAO clientOfferDAO;
	
	@Autowired
	OfferDAO offerDAO;

	private static final long serialVersionUID = -181586732036315166L;

	@Override
	public void saveClientOffer(ClientOfferVO purchase) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClientOffer(ClientOfferVO purchase) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeClientOffer(ClientOfferVO purchase) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ClientOfferVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientOfferVO findClientOfferByClientIdAndDate(String clientID, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientOfferVO> findClientOfferByClientId(String clientID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientOfferVO> findClientOfferByOfferId(String offerID) {
		// TODO Auto-generated method stub
		return null;
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
