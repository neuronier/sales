package hu.neuron.java.sales.service.webservice.impl;

import hu.neuron.java.sales.service.IssueMessageServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote.Status;
import hu.neuron.java.sales.service.vo.IssueMessageVO;
import hu.neuron.java.sales.service.vo.IssueThreadVO;
import hu.neuron.java.sales.service.webservice.IssueMessageWebService;
import hu.neuron.java.sales.service.webservice.vo.IssueMessageListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.IssueMessageWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.IssueThreadListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.IssueThreadWebServiceVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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


@Stateless(mappedName = "IssueMessageWebService", name = "IssueMessageWebService")
@WebService(name = "IssueMessageWebServicePort", serviceName = "IssueMessageWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.sales.service.webservice.IssueMessageWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class IssueMessageWebServiceImpl implements IssueMessageWebService{

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	IssueThreadServiceRemote issueThreadService;
	
	IssueMessageServiceRemote issueMessageService;

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
			issueThreadService = (IssueThreadServiceRemote) ctx.lookup("IssueThreadService#hu.neuron.java.sales.service.IssueThreadServiceRemote");
			issueMessageService = (IssueMessageServiceRemote) ctx.lookup("IssueMessageService#hu.neuron.java.sales.service.IssueMessageServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IssueThreadListWebServiceVO getIssueThreadListByClientWebMethod(String clientId) {
		initEJB();
		
		List<IssueThreadVO> issueThreadVOs = issueThreadService.findByClientId(clientId);
		IssueThreadListWebServiceVO rv = new IssueThreadListWebServiceVO();
		rv.setList(new ArrayList<IssueThreadWebServiceVO>());
		
		for (IssueThreadVO issueThreadVO : issueThreadVOs) {
			rv.getList().add(mapper.map(issueThreadVO, IssueThreadWebServiceVO.class));
		}
		
		return rv;
	}

	@Override
	public IssueThreadWebServiceVO createNewIssueThreadWebMethod(String clientId, String subject) {
		initEJB();
		
		IssueThreadVO issueThread = new IssueThreadVO();
		issueThread.setClientId(clientId);
		issueThread.setSubject(subject);
		issueThread.setStatus(Status.NEW.toString());
		issueThread = issueThreadService.saveIssueThread(issueThread);
		
		return mapper.map(issueThread, IssueThreadWebServiceVO.class);
	}

	@Override
	public IssueMessageListWebServiceVO getIssueMessageListByThreadIdWebMethod(String threadId) {
		initEJB();
		
		List<IssueMessageVO> issueMessageVOs = issueMessageService.findByThreadId(threadId);
		IssueMessageListWebServiceVO rv = new IssueMessageListWebServiceVO();
		rv.setList(new ArrayList<IssueMessageWebServiceVO>());
		
		for (IssueMessageVO issueMessageVO : issueMessageVOs) {
			rv.getList().add(mapper.map(issueMessageVO, IssueMessageWebServiceVO.class));
		}
		
		return rv;
	}

	@Override
	public void createIssueMessageInThreadWebMethod(String threadId, String messageText, String owner) {
		initEJB();
		
		IssueMessageVO issueMessage = new IssueMessageVO();
		issueMessage.setThreadId(threadId);
		issueMessage.setDate(new Date());
		issueMessage.setText(messageText);
		issueMessage.setOwner(owner);
		issueMessageService.saveIssueMessage(issueMessage);
	}

	@Override
	public void modifyIssueThreadStatusWebMethod(String threadId, String status) {
		initEJB();
		
		IssueThreadVO issueThread = issueThreadService.findByThreadId(threadId);		
		issueThread.setStatus(status);
		
		issueThreadService.saveIssueThread(issueThread);
	}

	
}
