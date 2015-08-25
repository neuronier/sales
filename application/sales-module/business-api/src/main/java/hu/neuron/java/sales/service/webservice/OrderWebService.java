package hu.neuron.java.sales.service.webservice;

import hu.neuron.java.sales.service.webservice.vo.OrderListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.OrderWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface OrderWebService {
	@WebMethod(operationName = "getOrderList")
	@WebResult(name = "getOrderList")
	public OrderListWebServiceVO getOrderListWebMethod();

	@WebMethod(operationName = "getOrderByOrderId")
	@WebResult(name = "getOrderByOrderId")
	public OrderWebServiceVO getOrderByOrderIdWebMethod(String orderId);

	@WebMethod(operationName = "modifyOrderByOrderId")
	@WebResult(name = "modifyOrderByOrderId")
	public OrderWebServiceVO modifyOrderStatusByOrderIdWebMethod(String orderId, String updateStatus);
}
