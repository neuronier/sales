package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Order;

import java.util.List;

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
public class OrderDaoTest {
	
	private static final Logger logger = Logger.getLogger(OrderDaoTest.class);
	
	private static Order entity;
	
	@Autowired
	OrderDAO orderDAO;
	
	@Test
	public void test1Create(){
		entity = new Order();
		entity.setOrderId("orderId");
		entity.setOrderName("orderName");
		entity.setStatus("FELDOLGOZVA");
		orderDAO.save(entity);
	}
	
	@Test
	public void test2Update(){
		try {
			entity.setOrderName("UPDATED");
			orderDAO.save(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test3Find() {
		try {
			Order resEntity = orderDAO.findOne(entity.getId());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test4Find() {
		try {
			Order resEntity = orderDAO.findOrderByName(entity.getName());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindAll() {
		try {
			List<Order> orders = orderDAO.findAll();
			for (Order order : orders) {
				logger.info("rv: " + order);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test6Delete() {
		try {
			orderDAO.delete(entity.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
