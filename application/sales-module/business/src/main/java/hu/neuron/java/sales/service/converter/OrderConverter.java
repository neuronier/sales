package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import hu.neuron.java.core.entity.Order;
import hu.neuron.java.sales.service.vo.OrderVO;

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

	@PostConstruct
	void init() {
		logger.debug("OrderConverter init");
	}

	public OrderVO toVO(Order entity) {
		if (entity == null) {
			return null;
		}
		return mapper.map(entity, OrderVO.class);
	}

	public Order toEntity(OrderVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Order.class);
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
