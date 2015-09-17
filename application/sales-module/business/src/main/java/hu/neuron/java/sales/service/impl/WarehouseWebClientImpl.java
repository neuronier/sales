package hu.neuron.java.sales.service.impl;

import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.WarehouseWebClient;
import hu.neuron.java.sales.service.vo.WarehouseVO;
import hu.neuron.java.warehouse.whweb.web.service.WarehouseWebService;
import hu.neuron.java.warehouse.whweb.web.service.WarehouseWebServiceImplService;
import hu.neuron.java.warehouse.whweb.web.service.WarehouseWebVO;

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

@Stateless(name = "WarehouseWebClient", mappedName = "WarehouseWebClient")
@Remote(WarehouseWebClient.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class WarehouseWebClientImpl implements WarehouseWebClient {

	@EJB(name = "WarehouseService", mappedName = "WarehouseService")
	WarehouseServiceRemote warehouseService;

	@Override
	public List<WarehouseVO> refreshWarehouses() throws Exception {
		URL wsdl = null;
		List<WarehouseVO> warehouseList = new ArrayList<WarehouseVO>();
		try {
			wsdl = new URL( 
					"http://javatraining.neuron.hu/warehouseApp/WarehouseWebServiceImplService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName(
				"http://service.web.whWeb.warehouse.java.neuron.hu/",
				"WarehouseWebServiceImplService");
		WarehouseWebServiceImplService exampleImplService = new WarehouseWebServiceImplService(
				wsdl, qName);

		WarehouseWebService exampleImpl = exampleImplService
				.getWarehouseWebServiceImplPort();
		List<WarehouseWebVO> list = exampleImpl.getAllWarehouse();
		for (WarehouseWebVO warehouseWebVO : list) {

			WarehouseVO warehouse = new WarehouseVO();
			warehouse.setWarehouseName(warehouseWebVO.getName());
			warehouse.setWarehouseId(warehouseWebVO.getWarehouseId());
			if(warehouseService.findWarehouseByWarehouseName(warehouse.getWarehouseName()) == null){
				warehouseService.saveWarehouse(warehouse);
				warehouseList.add(warehouse);
			}
		}
		return warehouseList;
	}
}