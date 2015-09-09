package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.dao.OfferDAO;
import hu.neuron.java.core.entity.ClientOffer;
import hu.neuron.java.sales.service.vo.ClientOfferVO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientOfferConverter {
	
	@Autowired
	ClientDAO clientDao;
	
	@Autowired
	ClientConverter clientConv;
	
	@Autowired
	OfferDAO offerDao;
	
	@Autowired
	OfferConverter offerConv;
	
	public ClientOfferVO toVO(ClientOffer dto) {
		if (dto == null) {
			return null;
		}
		ClientOfferVO co = new ClientOfferVO();
		try {
			co.setClient(clientConv.toVO(clientDao.findClientByClientId(dto.getClientId())));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		co.setClientOfferId(dto.getClientOfferId());
		co.setDate(dto.getDate());
		try {
			co.setOffer(offerConv.toVO(offerDao.findOfferEntityByOfferId(dto.getOfferId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		co.setQuantity(dto.getQuantity());
		co.setId(dto.getId());
		co.setSalesPointId(dto.getSalesPointId());
		
		return co;
	}

	public ClientOffer toEntity(ClientOfferVO vo) {
		if (vo == null) {
			return null;
		}
		ClientOffer co = new ClientOffer();
		co.setClientId(vo.getClient().getClientId());
		co.setClientOfferId(vo.getClientOfferId());
		co.setDate(vo.getDate());
		co.setId(vo.getId());
		co.setOfferId(vo.getOffer().getOfferId());
		co.setQuantity(vo.getQuantity());
		co.setSalesPointId(vo.getSalesPointId());
		return co;
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
