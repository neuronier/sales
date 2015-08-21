package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.List;

public interface OrderServiceRemote {

	public void saveOrder(OrderVO orderVO);

	public void updateOrder(OrderVO orderVO);

	public void removeOrder(OrderVO orderVO);

	public OrderVO findOrderByName(String name) throws Exception;

	public OrderVO findOrderByOrderId(String orderId) throws Exception;

	public List<OrderVO> getOrderVOs(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);

	public List<OrderVO> findAll() throws Exception;

	public int getRowNumber();

	public void addProductTypeToOrder(ProductTypeVO productType, OrderVO order,
			int q);

	public void removeProductTypeFromOrder(ProductTypeVO productType,
			OrderVO order);

	public int findQuantityToOrderProductType(ProductTypeVO productType,
			OrderVO order);

	public List<ProductTypeVO> findProductTypesToOrder(OrderVO order)
			throws Exception;

}
