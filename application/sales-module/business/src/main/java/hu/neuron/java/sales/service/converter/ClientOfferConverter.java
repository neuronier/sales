package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.core.entity.ClientOffer;
import hu.neuron.java.sales.service.vo.ClientOfferVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClientOfferConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public ClientOfferVO toVO(ClientOffer dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, ClientOfferVO.class);
	}

	public ClientOffer toEntity(ClientOfferVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, ClientOffer.class);
	}

	public List<ClientOfferVO> toVO(List<ClientOffer> dtos) {
		if (dtos == null) {
			return null;
		}
		List<ClientOfferVO> vos = new ArrayList<ClientOfferVO>();
		for (ClientOffer dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<ClientOffer> toEntity(List<ClientOfferVO> vos) {
		if (vos == null) {
			return null;
		}
		List<ClientOffer> dtos = new ArrayList<ClientOffer>();
		for (ClientOfferVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}	
