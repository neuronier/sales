package hu.neuron.java.sales.web.controllers.refresh;

import hu.neuron.java.sales.service.ClientWebClient;
import hu.neuron.java.sales.service.ProductTypeWebClient;
import hu.neuron.java.sales.service.WarehouseWebClient;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "refreshController")
public class refreshController implements Serializable {

	private static final long serialVersionUID = -8103310274938316809L;

	@EJB(name = "ProductTypeWebClient", mappedName = "ProductTypeWebClient")
	ProductTypeWebClient productTypeWebClient;

	@EJB(name="WarehouseWebClient",mappedName = "WarehouseWebClient")
	WarehouseWebClient warehouseWebClient;
	
	@EJB(name="ClientWebClient", mappedName = "ClientWebClient")
	ClientWebClient clientWebClient;
	
	List<ProductTypeVO> productTypeList = new ArrayList<ProductTypeVO>();
	
	List<WarehouseVO> warehouseList = new ArrayList<WarehouseVO>();
	
	List<ClientVO> clientList = new ArrayList<ClientVO>();
	
	public void refreshProductsList() {
		productTypeList = productTypeWebClient.refreshProductTypes();
	}
	
	public void refreshWarehouseList() throws Exception{
		warehouseList = warehouseWebClient.refreshWarehouses();
	}
	
	public void refreshClientList(){
//		clientList = clientWebClient.refreshClients();
	}
	
	public ClientWebClient getClientWebClient() {
		return clientWebClient;
	}

	public void setClientWebClient(ClientWebClient clientWebClient) {
		this.clientWebClient = clientWebClient;
	}

	public ProductTypeWebClient getProductTypeWebClient() {
		return productTypeWebClient;
	}

	public void setProductTypeWebClient(ProductTypeWebClient productTypeWebClient) {
		this.productTypeWebClient = productTypeWebClient;
	}

	public WarehouseWebClient getWarehouseWebClient() {
		return warehouseWebClient;
	}

	public void setWarehouseWebClient(WarehouseWebClient warehouseWebClient) {
		this.warehouseWebClient = warehouseWebClient;
	}

	public List<ProductTypeVO> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<ProductTypeVO> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public List<WarehouseVO> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<WarehouseVO> warehouseList) {
		this.warehouseList = warehouseList;
	}

	public List<ClientVO> getClientList() {
		return clientList;
	}

	public void setClientList(List<ClientVO> clientList) {
		this.clientList = clientList;
	}
}
