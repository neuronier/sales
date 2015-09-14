package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.OfferProductTypeEntity;
import hu.neuron.java.sales.service.vo.OfferProductTypeVO;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("OfferProductTypeConverter")
public class OfferProductTypeConverter {

	
	public OfferProductTypeVO toVO(OfferProductTypeEntity dto){
		if (dto == null) {
			return null;
		}
		OfferProductTypeVO vo = new OfferProductTypeVO();
		vo.setOfferId(dto.getOfferId());
		vo.setProductTypeId(dto.getProductTypeId());
		vo.setQuantity(dto.getQuantity());
		vo.setId(dto.getId());
		return vo;
	}
	
	public List<OfferProductTypeVO> toVO(List<OfferProductTypeEntity> dtos) {
		if (dtos == null) {
			return null;
		}
		List<OfferProductTypeVO> vos = new ArrayList<OfferProductTypeVO>();
		for (OfferProductTypeEntity dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}
	
	public OfferProductTypeEntity toEntity(OfferProductTypeVO vo){
		if (vo == null) {
			return null; 
		}
		
		OfferProductTypeEntity en = new OfferProductTypeEntity();
		en.setId(vo.getId());
		en.setOfferId(vo.getOfferId());
		en.setProductTypeId(vo.getProductTypeId());
		en.setQuantity(vo.getQuantity());
		return en;
	}
	
	public List<OfferProductTypeEntity> toEntity(List<OfferProductTypeVO> vos) {
		if (vos == null) {
			return null;
		}
		List<OfferProductTypeEntity> dtos = new ArrayList<OfferProductTypeEntity>();
		for (OfferProductTypeVO vo : vos) {
			dtos.add(toEntity(vo)); 
		}
		return dtos;
	} 
}
