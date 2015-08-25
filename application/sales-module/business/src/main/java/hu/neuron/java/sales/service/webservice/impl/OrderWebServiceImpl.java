package hu.neuron.java.sales.service.webservice.impl;

import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.webservice.OrderWebService;
import hu.neuron.java.sales.service.webservice.vo.OrderListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.OrderWebServiceVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OrderWebService", name = "OrderWebService")
@WebService(name = "OrderWebServicePort", serviceName = "OrderWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.sales.service.webservice.OrderWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrderWebServiceImpl implements OrderWebService {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	OrderServiceRemote orderService;

	public void initEJB() {
		try {
			InputStream inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"hu/neuron/java/sales/services/Settings.properties");

			Properties properties = new Properties();

			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			env.put(Context.SECURITY_PRINCIPAL,
					properties.getProperty("SECURITY_PRINCIPAL"));
			env.put(Context.SECURITY_CREDENTIALS,
					properties.getProperty("SECURITY_CREDENTIALS"));
			env.put(Context.PROVIDER_URL,
					properties.getProperty("PROVIDER_URL"));
			Context ctx;

			ctx = new InitialContext(env);
			System.out.println("ctx  = " + ctx);
			orderService = (OrderServiceRemote) ctx
					.lookup("OrderService#hu.neuron.java.sales.service.OrderServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderListWebServiceVO getOrderListWebMethod() {
		initEJB();
		List<OrderVO> orderVOs = null;
		try {
			orderVOs = orderService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OrderListWebServiceVO rv = new OrderListWebServiceVO();
		rv.setList(new ArrayList<OrderWebServiceVO>());
		for (OrderVO orderVO : orderVOs) {
			rv.getList().add(mapper.map(orderVO, OrderWebServiceVO.class));
		}
		return rv;
	}

	@Override
	public OrderWebServiceVO getOrderByOrderIdWebMethod(String orderId) {
		initEJB();
		OrderVO order = null;
		try {
			order = orderService.findOrderByOrderId(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapper.map(order, OrderWebServiceVO.class);
	}

	@Override
	public OrderWebServiceVO modifyOrderStatusByOrderIdWebMethod(
			String orderId, String updateStatus) {
		OrderVO orderVO = null;
		try {
			orderVO = orderService.findOrderByOrderId(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		orderVO.setStatus(updateStatus);
		orderService.saveOrder(orderVO);
		try {
			orderVO = orderService.findOrderByOrderId(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapper.map(orderVO, OrderWebServiceVO.class);
	}

}