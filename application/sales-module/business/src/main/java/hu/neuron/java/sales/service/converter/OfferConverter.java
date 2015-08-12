package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.vo.OfferVO;

@Component("OfferConverter")
public class OfferConverter {

	
	public OfferVO toVO(OfferEntity dto){
		if (dto == null) {
			return null;
		}
		OfferVO vo = new OfferVO();
		vo.setName(dto.getName());
		vo.setOfferId(dto.getOfferId());
		vo.setOfferPrice(dto.getOfferPrice());
		vo.setID(dto.getId());
		
		return vo;
	}
	
	public List<OfferVO> toVO(List<OfferEntity> dtos) {
		if (dtos == null) {
			return null;
		}
		List<OfferVO> vos = new ArrayList<OfferVO>();
		for (OfferEntity dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}
	
	public OfferEntity toEntity(OfferVO vo){
		if (vo == null) {
			return null; 
		}
		
		OfferEntity en = new OfferEntity();
		en.setId(vo.getID());
		en.setName(vo.getName());
		en.setOfferId(vo.getOfferId());
		en.setOfferPrice(vo.getOfferPrice());
		
		return en;
	}
	
	public List<OfferEntity> toEntity(List<OfferVO> vos) {
		if (vos == null) {
			return null;
		}
		List<OfferEntity> dtos = new ArrayList<OfferEntity>();
		for (OfferVO vo : vos) {
			dtos.add(toEntity(vo)); 
		}
		return dtos;
	} 
}
