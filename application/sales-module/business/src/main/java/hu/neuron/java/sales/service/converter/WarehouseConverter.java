package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.core.entity.Warehouse;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WarehouseConverter {
	
	private static final Logger logger = Logger.getLogger(WarehouseConverter.class);
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	@PostConstruct
	void init() {
		logger.debug("WarehouseConverter init");
	}

	public WarehouseVO toVO(Warehouse dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, WarehouseVO.class);
	}

	public Warehouse toEntity(WarehouseVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Warehouse.class);
	}

	public List<WarehouseVO> toVO(List<Warehouse> dtos) {
		if (dtos == null) {
			return null;
		}
		List<WarehouseVO> vos = new ArrayList<WarehouseVO>();
		for (Warehouse dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Warehouse> toEntity(List<WarehouseVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Warehouse> dtos = new ArrayList<Warehouse>();
		for (WarehouseVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}


}
