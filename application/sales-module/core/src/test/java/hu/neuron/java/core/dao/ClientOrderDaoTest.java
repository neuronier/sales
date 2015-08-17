package hu.neuron.java.core.dao;

import static org.junit.Assert.*;

import hu.neuron.java.core.entity.Client;
import hu.neuron.java.core.entity.ClientOrder;
import hu.neuron.java.core.entity.Order;
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
public class ClientOrderDaoTest {
	
	private static final Logger logger = Logger.getLogger(ClientOrderDaoTest.class);
	
	private static ClientOrder entity;
	
	private static Client client;
	
	private static Order order;
	
	@Autowired
	ClientOrderDAO clientOrderDAO;
	
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	OrderDAO orderDAO;

	@Test
	public void test1Create() {
		entity = new ClientOrder();
		client = new Client();
		client.setClientId("101L");
		client.setName("Test Client");
		order = new Order();
		order.setOrderId("202L");
		order.setOrderName("Test Order");
		entity.setClientId(client.getClientId());
		entity.setOrderId(order.getOrderId());
		clientOrderDAO.save(entity);
	}
	
	@Test
	public void test2Update() {
		try {
			client.setClientId("202L");
			entity.setClientId(client.getClientId());
			clientOrderDAO.save(entity);
			ClientOrder res = clientOrderDAO.findClientOrderByClientId(client.getClientId()).get(0);
			assertEquals(res.getClientId(),client.getClientId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3FindByClientId() {
		try {
			ClientOrder resEntity = clientOrderDAO.findClientOrderByClientId(client.getClientId()).get(0);
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4FindByOrderId() {
		try {
			ClientOrder resEntity = clientOrderDAO.findClientOrderByOrderId(order.getOrderId()).get(0);
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindByClientIdAndOrderId() {
		try {
			ClientOrder clientOrder = 
					clientOrderDAO.findClientOrderByClientIdAndOrderId(
							client.getClientId(), order.getOrderId());
			logger.info("res: " + clientOrder);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6Delete() {
		try {
			clientOrderDAO.delete(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
