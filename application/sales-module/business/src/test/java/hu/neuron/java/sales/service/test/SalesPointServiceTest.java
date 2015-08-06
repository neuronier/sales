package hu.neuron.java.sales.service.test;

import javax.interceptor.Interceptors;

import hu.neuron.java.sales.service.SalesPointServiceLocale;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-business.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SalesPointServiceTest {
	
	@Autowired
	SalesPointServiceLocale salesPointService;
	
	@Test
	public void test1Create() {
		SalesPointVO sp = new SalesPointVO();
		sp.setIdentifier(202L);
		salesPointService.saveSalePoint(sp);
	}

}
