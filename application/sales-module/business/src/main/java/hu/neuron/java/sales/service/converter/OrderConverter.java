package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.dao.ClientOrderDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.dao.OfferOrderDAO;
import hu.neuron.java.core.dao.OrderProductTypeDAO;
import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.core.entity.Client;
import hu.neuron.java.core.entity.ClientOrder;
import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.core.entity.OfferOrderEntity;
import hu.neuron.java.core.entity.Order;
import hu.neuron.java.core.entity.OrderProductType;
import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.sales.service.vo.OrderVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

	private static final Logger logger = Logger.getLogger(OrderConverter.class);

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	@Autowired
	OfferConverter offerConverter;

	@Autowired
	OfferOrderDAO offerOrderDAO;

	@Autowired
	OfferDAO offerDAO;

	@Autowired
	ClientConverter clientConverter;

	@Autowired
	ClientOrderDAO clientOrderDAO;

	@Autowired
	ClientDAO clientDAO;

	@Autowired
	ProductTypeConverter productConverter;

	@Autowired
	OrderProductTypeDAO orderProductDAO;

	@Autowired
	ProductTypeDAO productDAO;

	@PostConstruct
	void init() {
		logger.debug("OrderConverter init");
	}

	public OrderVO toVO(Order entity) {
		if (entity == null) {
			return null;
		}
		OrderVO rv = new OrderVO();
		try {
			ClientOrder client = clientOrderDAO.findClientOrderByOrderId(
					entity.getOrderId()).get(0);
			Client c = clientDAO.findByClientId(client.getClientId());
			rv.setClient(clientConverter.toVO(c));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			OfferOrderEntity offer = offerOrderDAO
					.findOfferOrderEntityByOrderId(entity.getOrderId()).get(0);
			rv.setoQuantity(String.valueOf(offer.getQuantity()));
			OfferEntity offerEntity = offerDAO.findOfferEntityByOfferId(offer
					.getOfferId());

			// List<OfferVO> offers = new ArrayList<OfferVO>();
			// offers.add(offerConverter.toVO(offerEntity));
			rv.setOffers(offerConverter.toVO(offerEntity));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			OrderProductType product = orderProductDAO
					.findOrderProductTypeByOrderId(entity.getOrderId()).get(0);
			rv.setpQuantity(String.valueOf(product.getQuantity()));
			ProductTypeEntity typeEntity = productDAO
					.findProductTypeEntityByProductTypeId(product
							.getProductTypeId());

			rv.setProductType(productConverter.toVO(typeEntity));
		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setId(entity.getId());
		rv.setOrderId(entity.getOrderId());
		rv.setName(entity.getName());
		rv.setStatus(entity.getStatus());

		return rv;
		// return mapper.map(entity, OrderVO.class);
	}

	public Order toEntity(OrderVO vo) {
		if (vo == null) {
			return null;
		}
		Order rv = new Order();

		rv.setId(vo.getId());
		rv.setOrderId(vo.getOrderId());
		rv.setOrderName(vo.getName());
		rv.setStatus(vo.getStatus());

		return rv;
		// return mapper.map(vo, Order.class);
	}

	public List<OrderVO> toVO(List<Order> dtos) {
		if (dtos == null) {
			return null;
		}

		List<OrderVO> vos = new ArrayList<OrderVO>();
		for (Order dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Order> toEntity(List<OrderVO> vos) {
		if (vos == null) {
			return null;
		}

		List<Order> dtos = new ArrayList<Order>();

		for (OrderVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
