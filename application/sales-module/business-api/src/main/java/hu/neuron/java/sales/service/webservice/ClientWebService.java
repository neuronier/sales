package hu.neuron.java.sales.service.webservice;

import hu.neuron.java.sales.service.webservice.vo.ClientListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.ClientWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.OrderListWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface ClientWebService {
	
	@WebMethod(operationName = "getClientList")
	@WebResult(name = "getClientList")
	public ClientListWebServiceVO getClientListWebMethod();

	@WebMethod(operationName = "getClientByClientId")
	@WebResult(name = "getClientByClientId")
	public ClientWebServiceVO getClientByClientIdWebMethod(String clientId);
	
	@WebMethod(operationName = "getOrderListByClientId")
	@WebResult(name = "getOrderListByClientId")
	public OrderListWebServiceVO getOrderListByClientIdWebMethod(String clientId);

	@WebMethod(operationName = "createClient")
	@WebResult(name = "createClient")
	public ClientWebServiceVO createClientWebMethod(String name, String userName, String password, String email, String phoneNumber);
	
	@WebMethod(operationName = "modifyClientByClientId")
	@WebResult(name = "modifyClientByClientId")
	public ClientWebServiceVO modifyClientByClientIdWebMethod(String clientId, String name, String userName, String password, String email, String phoneNumber);
	
	@WebMethod(operationName = "removeClientByClientId")
	@WebResult(name = "removeClientByClientId")
	public void removeClientByClientIdWebMethod(String clientId);

}
