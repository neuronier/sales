package hu.neuron.java.sales.service.impl;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.ClientWebClient;
import hu.neuron.java.sales.service.vo.ClientVO;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.xml.namespace.QName;

import neuron.hu.ClientListWebServiceVO;
import neuron.hu.ClientWebService;
import neuron.hu.ClientWebServiceVO;
import neuron.hu.ClientWebService_Service;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "ClientWebClient", mappedName = "ClientWebClient")
@Remote(ClientWebClient.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)

public class ClientWebClientImpl implements ClientWebClient {

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;
	
	@Override
	public List<ClientVO> refreshClients() {
		URL wsdl = null;
		List<ClientVO> clientList = new ArrayList<ClientVO>();
		try {
			wsdl = new URL(
					"http://javatraining.neuron.hu/webshopApp/WebshopClientWebService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName("http://java.neuron.hu/",
				"ClientWebService_Service");
		ClientWebService_Service exampleImplService = new ClientWebService_Service(
				wsdl, qName);

		ClientWebService exampleImpl = exampleImplService
				.getClientWebServicePortPort();
		 ClientListWebServiceVO list = exampleImpl.getClientList();
		 List<ClientWebServiceVO> volist = list.getClients();
		 for (ClientWebServiceVO clientWebVO : volist) {
			 
			ClientVO clientVO = new ClientVO();
			clientVO.setClientId(clientWebVO.getClientId());
			clientVO.setEmailAddress(clientWebVO.getEmail());
			clientVO.setPassword(clientWebVO.getPassword());
			clientVO.setName(clientWebVO.getFullName());
			clientVO.setPhoneNumber(clientWebVO.getPhone());
			clientVO.setUserName(clientWebVO.getUserName());
			if(clientService.findByClientId(clientVO.getClientId()) == null){
				clientService.saveClient(clientVO);
				clientList.add(clientVO);
			}
		}
		 return clientList;
	}

}
