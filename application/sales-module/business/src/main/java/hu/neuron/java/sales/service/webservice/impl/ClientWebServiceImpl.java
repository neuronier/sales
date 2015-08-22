package hu.neuron.java.sales.service.webservice.impl;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.webservice.ClientWebService;
import hu.neuron.java.sales.service.webservice.vo.ClientListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.ClientWebServiceVO;

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

@Stateless(mappedName = "ClientWebService", name = "ClientWebService")
@WebService(name = "ClientWebServicePort", serviceName = "ClientWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.sales.service.webservice.ClientWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ClientWebServiceImpl implements ClientWebService {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	ClientServiceRemote clientService;
	//OrderServiceRemote orderService;

	public void initEJB() {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("hu/neuron/java/sales/services/Settings.properties");

			Properties properties = new Properties();

			try {
				properties.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			env.put(Context.SECURITY_PRINCIPAL, properties.getProperty("SECURITY_PRINCIPAL"));
			env.put(Context.SECURITY_CREDENTIALS, properties.getProperty("SECURITY_CREDENTIALS"));
			env.put(Context.PROVIDER_URL, properties.getProperty("PROVIDER_URL"));
			Context ctx;

			ctx = new InitialContext(env);
			System.out.println("ctx  = " + ctx);
			clientService = (ClientServiceRemote) ctx.lookup("ClientService#hu.neuron.java.sales.service.ClientServiceRemote");
			//orderService = (OrderServiceRemote) ctx.lookup("OrderServiceRemote#hu.neuron.java.sales.service.OrderServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ClientListWebServiceVO getClientListWebMethod() {
		initEJB();
		List<ClientVO> clientVOs = clientService.findAll();
		ClientListWebServiceVO rv = new ClientListWebServiceVO();
		rv.setList(new ArrayList<ClientWebServiceVO>());
		for (ClientVO clientVO : clientVOs) {
			rv.getList().add(mapper.map(clientVO, ClientWebServiceVO.class));
		}

		return rv;
	}

	@Override
	public ClientWebServiceVO getClientByClientIdWebMethod(String clientId) {
		initEJB();
		return mapper.map(clientService.findByClientId(clientId), ClientWebServiceVO.class);
	}

//	@Override
//	public OrderListWebServiceVO getOrderListByClientIdWebMethod(String clientId) {
//		initEJB();
//		List<OrderVO> orderVOs;
//		OrderListWebServiceVO rv = new OrderListWebServiceVO();
//		try {
//			orderVOs = orderService.findOrdersByClientId(clientId);
//			rv.setList(new ArrayList<OrderWebServiceVO>());
//			for (OrderVO orderVO : orderVOs) {
//				rv.getList().add(mapper.map(orderVO, OrderWebServiceVO.class));
//			}
//
//			return rv;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return rv;
//		}
//		
//	}

	@Override
	public ClientWebServiceVO createClientWebMethod(String name, String userName, String password, String email, String phoneNumber) {
		initEJB();
		
		ClientVO newClient = new ClientVO();
		newClient.setName(name);
		newClient.setUserName(userName);
		newClient.setPassword(password);
		newClient.setEmailAddress(email);
		newClient.setPhoneNumber(phoneNumber);
		
		
		newClient = clientService.saveClient(newClient);
		return mapper.map(newClient, ClientWebServiceVO.class);
	}

	@Override
	public ClientWebServiceVO modifyClientByClientIdWebMethod(String clientId, String name, String userName, String password, String email,	String phoneNumber) {
		initEJB();
		
		ClientVO client = clientService.findByClientId(clientId);
		client.setName(name);
		client.setUserName(userName);
		client.setPassword(password);
		client.setEmailAddress(email);
		client.setPhoneNumber(phoneNumber);
		
		client = clientService.saveClient(client);
		return mapper.map(client, ClientWebServiceVO.class);
	}

	@Override
	public void removeClientByClientIdWebMethod(String clientId) {
		initEJB();
		clientService.removeClient(clientId);
	}

}
