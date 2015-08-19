package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.OrderProductType;

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
public class OrderProductTypeDaoTest {

	private static final Logger logger = Logger
			.getLogger(OrderProductTypeDAO.class);

	private static OrderProductType entity = new OrderProductType();

	@Autowired
	OrderProductTypeDAO dao;

	public void test1Create() {
		entity.setOrderId("orderId");
		entity.setProductTypeId("prId");
		entity.setQuantity(1);
		dao.save(entity);
	}

	@Test
	public void test2Update() {
		try {
			entity.setQuantity(10);
			dao.save(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3Find() {
		try {
			OrderProductType resEntity = dao.findOne(entity.getId());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Find() {
		try {
			List<OrderProductType> resEntity = dao
					.findOrderProductTypeByOrderId(entity.getOrderId());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindAll() {
		try {
			List<OrderProductType> op = dao.findAll();
			for (OrderProductType asd : op) {
				logger.info("rv: " + asd);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6Delete() {
		try {
			dao.delete(entity.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
