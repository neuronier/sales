package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.OrderVO;

import java.util.List;

public interface OrderServiceRemote {
	
	public void saveOrder(OrderVO orderVO);
	
	public void updateOrder(OrderVO orderVO);
	
	public void removeOrder(OrderVO orderVO);
	
	public OrderVO findOrderByName(String name) throws Exception;
	
	public List<OrderVO> getOrderVOs(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);
	
	public List<OrderVO> findAll() throws Exception;
	
	public int getRowNumber();

}
