package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.List;

public interface OrderServiceRemote {
	
	public void saveOrder(OrderVO orderVO);
	
	public void updateOrder(OrderVO orderVO);
	
	public void removeOrder(OrderVO orderVO);
	
	public OrderVO findOrderByName(String name) throws Exception;
	
	public OrderVO findOrderByOrderId(String orderId) throws Exception;
	
	//még nincs implementálva
	public List<OrderVO> findOrdersToClient(ClientVO client) throws Exception;
	
	public List<OrderVO> getOrderVOs(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
	
	public List<OrderVO> findAll() throws Exception;
	
	public int getRowNumber();
	
	public void addClientToOrder(ClientVO client, OrderVO order);
	
	public void addOfferToOrder(OfferVO offer, OrderVO order);
	
	public void addProductTypeToOrder(ProductTypeVO productType, OrderVO order);

}
