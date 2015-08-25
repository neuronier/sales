package hu.neuron.java.core.dao;

import static org.junit.Assert.*;
import hu.neuron.java.core.entity.Client;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.core.entity.ClientOffer;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ClientOfferDaoTest {
	
	private static final Logger logger = Logger.getLogger(ClientOfferDaoTest.class);
	
	private static ClientOffer clientOffer;
	
	private static Client client;
	
	private static OfferEntity offer;
	
	@Autowired
	ClientOfferDAO clientOfferDAO;
	
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	OrderDAO orderDAO;

	@Test
	public void test1Create() {
		clientOffer = new ClientOffer();
		client = new Client();
		client.setClientId("101L");
		client.setName("Test Client");
		offer = new OfferEntity();
		offer.setOfferId("202L");
		offer.setName("Test Offer");
		clientOffer.setClientId(client.getClientId());
		clientOffer.setOfferId(offer.getOfferId());
		clientOfferDAO.save(clientOffer);
	}
	
	@Test
	public void test2Update() {
		try {
			client.setClientId("202L");
			clientOffer.setClientId(client.getClientId());
			clientOfferDAO.save(clientOffer);
			ClientOffer res = clientOfferDAO.findClientOfferByClientId(client.getClientId()).get(0);
			assertEquals(res.getClientId(),client.getClientId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindByClientId() {
		try {
			ClientOffer resEntity = clientOfferDAO.findClientOfferByClientId(client.getClientId()).get(0);
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4FindByOrderId() {
		try {
			ClientOffer resEntity = clientOfferDAO.findClientOfferByOfferId(offer.getOfferId()).get(0);
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindByClientIdAndOrderId() {
		try {
			ClientOffer clientOrder = 
					clientOfferDAO.findClientOfferByClientIdAndOfferId(
							client.getClientId(), offer.getOfferId()).get(0);
			logger.info("res: " + clientOrder);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6Delete() {
		try {
			clientOfferDAO.delete(clientOffer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
