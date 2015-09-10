package hu.neuron.java.sales.service.impl;

import hu.neuron.java.sales.service.InventoryWebClient;
import hu.neuron.java.sales.service.vo.InventoryVO;
import hu.neuron.java.warehouse.whweb.web.service.GetWaresNumbersResponse.Return;
import hu.neuron.java.warehouse.whweb.web.service.WareWebService;
import hu.neuron.java.warehouse.whweb.web.service.WareWebServiceImplService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.xml.namespace.QName;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(name = "InventoryWebClient", mappedName = "InventoryWebClient")
@Remote(InventoryWebClient.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class InventoryWebClientImpl implements InventoryWebClient {

	@Override
	public List<InventoryVO> refreshInventory(String warehouseId) {
		URL wsdl = null;

		List<InventoryVO> list = new ArrayList<InventoryVO>();

		try {
			wsdl = new URL(
					"http://flyertest.neuron.hu/warehouseApp/WareWebServiceImplService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName(
				"http://service.web.whWeb.warehouse.java.neuron.hu/",
				"WareWebServiceImplService");
		WareWebServiceImplService exampleImplService = new WareWebServiceImplService(
				wsdl, qName);

		WareWebService exampleImpl = exampleImplService
				.getWareWebServiceImplPort();

		Return rt = exampleImpl.getWaresNumbers(warehouseId);

		if (rt != null) {
			for (int i = 0; i < rt.getEntry().size(); i++) {
				list.add(new InventoryVO(rt.getEntry().get(i).getKey(), rt
						.getEntry().get(i).getValue()));
			}
		}

		return list;

	}
}
