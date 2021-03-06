package hu.neuron.java.sales.service.test;

import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest {

	private static final Logger logger = Logger.getLogger(OrderServiceTest.class);
	
	private EJBContainer ejbContainer;
	
	@EJB(name="OrderService", mappedName="OrderService")
	OrderServiceRemote orderService;
	
	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.java.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.java.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.TestDataSource", "new://Resource?type=DataSource");
		p.put("hu.neuron.TestDataSource.JtaManaged", "false");
		p.put("hu.neuron.TestDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.TestDataSource.JdbcUrl", "jdbc:hsqldb:mem:aname");

		ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}
	
	@Test
	public void test1(){
		logger.info("Create new order");
		OrderVO vo = new OrderVO();
		
		vo.setName("name");
		vo.setOrderId("orderId");
		vo.setStatus("status");
		vo.setQuantity("10");
		vo.setWarehouse(new WarehouseVO());
		
		orderService.saveOrder(vo);
		
		ProductTypeVO product = new ProductTypeVO();
		
		product.setProductTypeId("productId");
		
		vo.setProductType(product);
		
		orderService.updateOrder(vo);
		
		List<OrderVO> listHelper = new ArrayList<OrderVO>();
		
		try {
			listHelper = orderService.findAll();
					logger.info(listHelper + "vos");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
		int num = orderService.getRowNumber();
		logger.info(num);
	}

	@After
	public void destroy() {
		ejbContainer.close();
	}
	
}
