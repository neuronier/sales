package hu.neuron.java.sales.service.webservice.impl;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.webservice.OfferWebService;
import hu.neuron.java.sales.service.webservice.vo.OfferListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.OfferWebServiceVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
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

@Stateless(mappedName = "OfferWebService", name = "OfferWebService")
@WebService(name = "OfferWebServicePort", serviceName = "OfferWebService", targetNamespace = "http://hu.neuron", endpointInterface = "hu.neuron.java.sales.service.webservice.OfferWebService")
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OfferWebServiceImpl implements OfferWebService {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	OfferServiceRemote offerService;
	ProductTypeServiceRemote productTypeService;
	
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
			offerService = (OfferServiceRemote) ctx.lookup("OfferService#hu.neuron.java.sales.service.OfferServiceRemote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public OfferListWebServiceVO getOfferListWebMethod() {
		initEJB();
		List<OfferVO> offerVOs = offerService.findAll();
		OfferListWebServiceVO rv = new OfferListWebServiceVO();
		rv.setList(new ArrayList<OfferWebServiceVO>());
		for (OfferVO offerVO : offerVOs) {
			rv.getList().add(mapper.map(offerVO, OfferWebServiceVO.class));
		}
		return rv;
	}

	@Override
	public OfferWebServiceVO getOfferByOfferIdWebMethod(String offerId){
		initEJB();
		return mapper.map(offerService.findOfferEntityByOfferId(offerId), OfferWebServiceVO.class);
	}

	@Override
	public OfferWebServiceVO createOfferWebMethod(String name, Long price,
			HashMap<String, Integer> offerProductTypes) {
		initEJB();
		
		OfferVO newOffer = new OfferVO();
		newOffer.setName(name);
		newOffer.setOfferPrice(price);	
		newOffer = offerService.saveOffer(newOffer); 
		
		for (Entry<String, Integer> entry : offerProductTypes.entrySet()) {
			offerService.addProductTypeToOffer(newOffer, productTypeService.findProductTypeByName(entry.getKey()), entry.getValue());
		}

		return mapper.map(newOffer, OfferWebServiceVO.class);
	}

	@Override
	public OfferWebServiceVO modifyOfferByOfferIdWebMethod(String offerId,
			String name, Long price,
			HashMap<String, Integer> offerProductTypes) {
		initEJB();
		
		OfferVO newOffer = offerService.findOfferEntityByOfferId(offerId);
		newOffer.setName(name);
		newOffer.setOfferPrice(price);	
		newOffer = offerService.saveOffer(newOffer); 
		
		for (Entry<String, Integer> entry : offerProductTypes.entrySet()) {
			offerService.addProductTypeToOffer(newOffer, productTypeService.findProductTypeByName(entry.getKey()), entry.getValue());
		}

		return mapper.map(newOffer, OfferWebServiceVO.class);
		
	}

	@Override
	public void removeOfferByOfferIdWebMethod(String offerId) {
		initEJB();
		OfferVO offer = offerService.findOfferEntityByOfferId(offerId);
		offerService.removeAllProductTypeFromOffer(offer);
		offerService.removeOffer(offer);	
	}
}