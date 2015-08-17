package hu.neuron.java.sales.service.webservice.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.service.webservice.UserWebService;
import hu.neuron.java.sales.service.webservice.vo.UserListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.UserWebServiceVO;

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

@Stateless(mappedName = "UserWebService", name = "UserWebServiceImpl")
@WebService(name = "UserWebServicePort", serviceName = "UserWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.sales.service.webservice.UserWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserWebServiceImpl implements UserWebService {

	UserServiceRemote serviceRemote;

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
			serviceRemote = (UserServiceRemote) ctx.lookup("UserService#hu.neuron.java.sales.service.UserServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public UserListWebServiceVO getUserListWebMethod() {
		initEJB();

		List<UserVO> userVOs = serviceRemote.getUserList();
		UserListWebServiceVO rv = new UserListWebServiceVO();
		rv.setList(new ArrayList<UserWebServiceVO>());
		for (UserVO userVO : userVOs) {
			rv.getList().add(mapper.map(userVO, UserWebServiceVO.class));
		}

		return rv;
	}
}
