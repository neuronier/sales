package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.OrderDAO;
import hu.neuron.java.core.dao.OrderProductTypeDAO;
import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.core.entity.Order;
import hu.neuron.java.core.entity.OrderProductType;
import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.converter.OrderConverter;
import hu.neuron.java.sales.service.converter.ProductTypeConverter;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "OrderService", name = "OrderService")
@Remote(OrderServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class OrderServiceImpl implements OrderServiceRemote, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderProductTypeDAO orderProductTypeDAO;
	
	@Autowired
	ProductTypeDAO productTypeDAO;

	@Autowired
	OrderConverter oConverter;

	@Autowired
	ProductTypeConverter productTypeConverter;
	
	@Override
	public void saveOrder(OrderVO orderVO) {
		orderDAO.save(oConverter.toEntity(orderVO));
	}

	@Override
	public void updateOrder(OrderVO orderVO) {
		orderDAO.save(oConverter.toEntity(orderVO));
	}

	@Override
	public void removeOrder(OrderVO orderVO) {
		orderDAO.delete(oConverter.toEntity(orderVO));
	}

	@Override
	public OrderVO findOrderByName(String name) throws Exception {
		OrderVO rvo = oConverter.toVO(orderDAO.findOrderByName(name));
		return rvo;
	}

	@Override
	public OrderVO findOrderByOrderId(String orderId) throws Exception {
		OrderVO rvo = oConverter.toVO(orderDAO.findOrderByOrderId(orderId));
		return rvo;
	}

	@Override
	public List<OrderVO> getOrderVOs(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new Sort.Order(dir, sortField)));
		Page<Order> entities;

		if (filter.length() != 0 && filterColumnName.equals("orderName")) {
			entities = orderDAO.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = orderDAO.findAll(pageRequest);
		}

		List<OrderVO> ret = oConverter.toVO(entities.getContent());
		return ret;
	}

	@Override
	public List<OrderVO> findAll() throws Exception {
		List<OrderVO> rvList = oConverter.toVO(orderDAO.findAll());
		return rvList;
	}

	@Override
	public int getRowNumber() {
		int num = (int) orderDAO.count();
		return num;
	}

	@Override
	public void addProductTypeToOrder(ProductTypeVO productType, OrderVO order,
			int q) {
		OrderProductType orderProductType = new OrderProductType();

		orderProductType.setProductTypeId(productType.getProductTypeId());
		orderProductType.setOrderId(order.getOrderId());
		orderProductType.setQuantity(q);

		orderProductTypeDAO.save(orderProductType);

	}

	@Override
	public void removeProductTypeFromOrder(ProductTypeVO productType,
			OrderVO order) {
		OrderProductType orderProductType = orderProductTypeDAO.findOrderProductTypeByOrderIdAndProductTypeId(order.getOrderId(), productType.getProductTypeId());
		orderProductTypeDAO.delete(orderProductType);

	}

	@Override
	public int findQuantityToOrderProductType(ProductTypeVO productType,
			OrderVO order) {

		return orderProductTypeDAO.findOrderProductTypeByOrderIdAndProductTypeId(order.getOrderId(), productType.getProductTypeId()).getQuantity();
	}

	@Override
	public List<ProductTypeVO> findProductTypesToOrder(OrderVO order)
			throws Exception {
		List<OrderProductType> entities = orderProductTypeDAO.findOrderProductTypeByOrderId(order.getOrderId());
		
		List<String> productTypeIdList = new ArrayList<>();
		List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
		
		for (OrderProductType e : entities) {
			productTypeIdList.add(e.getProductTypeId());
		}
		
		for (String productTypeId : productTypeIdList) {
			productTypeEntities.add(productTypeDAO.findProductTypeEntityByProductTypeId(productTypeId));
		}
		return productTypeConverter.toVO(productTypeEntities);
	}

}
