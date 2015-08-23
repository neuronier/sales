package hu.neuron.java.sales.service.webservice;
 
import hu.neuron.java.sales.service.webservice.vo.OfferListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.OfferWebServiceVO;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface OfferWebService {

	@WebMethod(operationName = "getOfferList")
	@WebResult(name = "getOfferList")
	public OfferListWebServiceVO getOfferListWebMethod();

	@WebMethod(operationName = "getOfferByOfferId")
	@WebResult(name = "getOfferByOfferId")
	public OfferWebServiceVO getOfferByOfferIdWebMethod(String offerId);

	@WebMethod(operationName = "createOffer")
	@WebResult(name = "createOffer")
	public OfferWebServiceVO createOfferWebMethod(String name, Long price, HashMap<String, Integer> offerProductTypes);
	
	@WebMethod(operationName = "modifyOfferByOfferId")
	@WebResult(name = "modifyOfferByOfferId")
	public OfferWebServiceVO modifyOfferByOfferIdWebMethod(String offerId, String name, Long price, HashMap<String, Integer> offerProductTypes);
	
	@WebMethod(operationName = "removeOfferByOfferId")
	@WebResult(name = "removeOfferByOfferId")
	public void removeOfferByOfferIdWebMethod(String offerId);
	
}
