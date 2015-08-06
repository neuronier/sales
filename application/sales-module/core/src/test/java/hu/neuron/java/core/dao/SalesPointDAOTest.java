package hu.neuron.java.core.dao;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.core.entity.SalesPoint;

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
public class SalesPointDAOTest {
	
	@Autowired
	SalesPointDAO salesDAO;

	@Test
	public void test1Create() {
		SalesPoint entity = new SalesPoint();
		entity.setId(101L);
		entity.setSalePointAdress("NOWHERE");
		entity.setSalePointId(202L);
		entity.setName("SALEPOINT");
		entity.setSalePointPhoneNumber("06303030300");
		entity.setWareHouseId(303L);
		salesDAO.save(entity);
	}

}
