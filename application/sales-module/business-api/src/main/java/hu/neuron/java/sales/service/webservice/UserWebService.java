package hu.neuron.java.sales.service.webservice;

import hu.neuron.java.sales.service.webservice.vo.UserListWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface UserWebService {

	@WebMethod(operationName = "userList")
	@WebResult(name = "userList")
	public UserListWebServiceVO getUserListWebMethod();
	
}
