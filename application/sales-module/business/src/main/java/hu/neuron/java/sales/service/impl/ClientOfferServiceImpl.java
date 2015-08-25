package hu.neuron.java.sales.service.impl;

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

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ClientOfferService", name = "ClientOfferService")
@Remote(ClientOfferServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientOfferServiceImpl implements ClientOfferServiceRemote,
		Serializable {


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
	public ClientOfferVO findClientOfferByClientIdAndDate(String clientID,
			Date date) {
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

}
