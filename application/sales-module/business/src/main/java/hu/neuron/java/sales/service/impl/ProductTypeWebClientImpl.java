package hu.neuron.java.sales.service.impl;

import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.ProductTypeWebClient;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.warehouse.whweb.web.service.WareWebService;
import hu.neuron.java.warehouse.whweb.web.service.WareWebServiceImplService;
import hu.neuron.java.warehouse.whweb.web.service.WareWebVO;

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

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


@Stateless(name = "ProductTypeWebClient", mappedName = "ProductTypeWebClient")
@Remote(ProductTypeWebClient.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ProductTypeWebClientImpl implements ProductTypeWebClient {
	
	@EJB(name="ProductTypeService", mappedName="ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	@Override
	public List<ProductTypeVO> refreshProductTypes() {
		URL wsdl = null;
		List<ProductTypeVO> productTypeList = new ArrayList<ProductTypeVO>();
		try {
			wsdl = new URL(
					"http://javatraining.neuron.hu/warehouseApp/WareWebServiceImplService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName("http://service.web.whWeb.warehouse.java.neuron.hu/",
				"WareWebServiceImplService");
		WareWebServiceImplService exampleImplService = new WareWebServiceImplService(
				wsdl, qName);

		WareWebService exampleImpl = exampleImplService
				.getWareWebServiceImplPort();
		 List<WareWebVO> list = exampleImpl.getAllWares();
		 for (WareWebVO wareWebVO : list) {
			 
			ProductTypeVO productType = new ProductTypeVO();
			productType.setName(wareWebVO.getWareName());
			if(productTypeService.findProductTypeByName(productType.getName()) == null){
				productTypeService.saveProductType(productType);
				productTypeList.add(productType);
			}
		}
		 return productTypeList;
	}
}

