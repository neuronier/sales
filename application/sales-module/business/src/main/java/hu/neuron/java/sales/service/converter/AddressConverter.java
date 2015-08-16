package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.core.entity.Address;
import hu.neuron.java.sales.service.vo.AddressVO;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
	
	private static final Logger logger = Logger.getLogger(AddressConverter.class);
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	@PostConstruct
	void init() {
		logger.debug("AddressConverter init");
	}

	public AddressVO toVO(Address dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, AddressVO.class);
	}

	public Address toEntity(AddressVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Address.class);
	}

	public List<AddressVO> toVO(List<Address> dtos) {
		if (dtos == null) {
			return null;
		}
		List<AddressVO> vos = new ArrayList<AddressVO>();
		for (Address dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Address> toEntity(List<AddressVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Address> dtos = new ArrayList<Address>();
		for (AddressVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}


}
