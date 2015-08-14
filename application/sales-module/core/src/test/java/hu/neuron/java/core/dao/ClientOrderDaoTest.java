package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.core.entity.SalesPoint;

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
	
	private static SalesPoint entity;
	
	@Autowired
	SalesPointDAO salesDAO;

	@Test
	public void test1Create() {
		entity = new SalesPoint();
		entity.setSalePointAdress("NOWHERE");
		entity.setSalePointId(101L);
		entity.setName("SALEPOINT");
		entity.setSalePointPhoneNumber("06303030300");
		entity.setWareHouseId(303L);
		salesDAO.save(entity);
	}
	
	@Test
	public void test2Update() {
		try {
			entity.setName("UPDATED SALEPOINT");
			salesDAO.save(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3Find() {
		try {
			SalesPoint resEntity = salesDAO.findOne(entity.getId());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4Find() {
		try {
			SalesPoint resEntity = salesDAO.findSalesPointByName(entity.getName());
			logger.info("res: " + resEntity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5FindAll() {
		try {
			List<SalesPoint> salesPoints = salesDAO.findAll();
			for (SalesPoint salesPoint : salesPoints) {
				logger.info("rv: " + salesPoint);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test6Delete() {
		try {
			salesDAO.delete(entity.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
