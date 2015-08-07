package hu.neuron.java.sales.service.test;

import java.util.List;

import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import javax.interceptor.Interceptors;

import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(SalesPointServiceTest.class);
	private static SalesPointVO sp;

	@Autowired
	SalesPointServiceRemote salesPointService;

	@Test
	public void test1Create() {
		try {
			sp = new SalesPointVO();
			sp.setName("TestPont");
			sp.setIdentifier(202L);
			salesPointService.saveSalePoint(sp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2Update() {
		try {
			sp.setIdentifier(203L);
			sp.setName("Test2Pont");
			salesPointService.saveSalePoint(sp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test3Find() {
		try {
			SalesPointVO rvVO = salesPointService.findSalePointByName(sp.getName());
			logger.info("rv: " + rvVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test4FindAll() {
		try {
			List<SalesPointVO> salesPoints = salesPointService.findAll();
			for (SalesPointVO salesPointVO : salesPoints) {
				logger.info("rv: " + salesPointVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test5Remove() {
		try {
			salesPointService.removeSalePoint(sp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
