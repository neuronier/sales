package hu.neuron.java.sales.service.test;

import java.util.List;
import java.util.Properties;

import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesPointServiceTest {

	private static final Logger logger = Logger.getLogger(SalesPointServiceTest.class);
	private static SalesPointVO sp;
	
	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.java.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.java.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.TestDataSource", "new://Resource?type=DataSource");
		p.put("hu.neuron.TestDataSource.JtaManaged", "false");
		p.put("hu.neuron.TestDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.TestDataSource.JdbcUrl", "jdbc:hsqldb:mem:aname");

		EJBContainer ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@EJB(mappedName = "SalesPointService", name = "SalesPointService")
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
