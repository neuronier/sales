package hu.neuron.java.sales.service.webservice;

import hu.neuron.java.sales.service.webservice.vo.AddressWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.ClientListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.ClientWebServiceVO;

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

	@WebMethod(operationName = "createClient")
	@WebResult(name = "createClient")
	public ClientWebServiceVO createClientWebMethod(String name, String userName, String password, String email, String phoneNumber, String addressId);
	
	@WebMethod(operationName = "modifyClientByClientId")
	@WebResult(name = "modifyClientByClientId")
	public ClientWebServiceVO modifyClientByClientIdWebMethod(String clientId, String name, String userName, String password, String email, String phoneNumber, String addressId);
	
	@WebMethod(operationName = "removeClientByClientId")
	@WebResult(name = "removeClientByClientId")
	public void removeClientByClientIdWebMethod(String clientId);

	@WebMethod(operationName = "createAddress")
	@WebResult(name = "createAddress")
	public AddressWebServiceVO createAddressWebMethod(String zipCode, String city, String street, String houseNumber);
	
	@WebMethod(operationName = "modifyAddressByAddressId")
	@WebResult(name = "modifyAddressByAddressId")
	public AddressWebServiceVO modifyAddressWebMethod(String addressId, String zipCode, String city, String street, String houseNumber);
	
}
