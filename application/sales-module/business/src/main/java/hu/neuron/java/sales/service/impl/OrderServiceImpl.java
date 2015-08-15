package hu.neuron.java.sales.service.impl;

import java.io.Serializable;
import java.util.List;

import hu.neuron.java.core.dao.ClientOrderDAO;
import hu.neuron.java.core.dao.OfferOrderDAO;
import hu.neuron.java.core.dao.OrderDAO;
import hu.neuron.java.core.dao.OrderProductTypeDAO;
import hu.neuron.java.core.entity.ClientOrder;
import hu.neuron.java.core.entity.OfferOrderEntity;
import hu.neuron.java.core.entity.Order;
import hu.neuron.java.core.entity.OrderProductType;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.converter.OrderConverter;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

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
	ClientOrderDAO clientOrderDAO;
	
	@Autowired
	OfferOrderDAO offerOrderDAO;
	
	@Autowired
	OrderProductTypeDAO orderProductTypeDAO;

	@Autowired
	OrderConverter oConverter;

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
	public void addClientToOrder(ClientVO client, OrderVO order) {
		ClientOrder clientOrder = new ClientOrder();
		
		clientOrder.setClientId(client.getClientId());
		clientOrder.setOrderId(order.getOrderId());
		
		clientOrderDAO.save(clientOrder);

	}

	@Override
	public void addOfferToOrder(OfferVO offer, OrderVO order) {
		OfferOrderEntity offerOrder = new OfferOrderEntity();
		
		offerOrder.setOfferId(offer.getOfferId());
		offerOrder.setOrderId(order.getOrderId());
		
		offerOrderDAO.save(offerOrder);

	}

	@Override
	public void addProductTypeToOrder(ProductTypeVO productType, OrderVO order) {
		OrderProductType orderProductType = new OrderProductType();
		
		orderProductType.setProductTypeId(productType.getProductTypeId());
		orderProductType.setOrderId(order.getOrderId());
		
		orderProductTypeDAO.save(orderProductType);

	}

	@Override
	public List<OrderVO> findOrdersToClient(ClientVO client) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
