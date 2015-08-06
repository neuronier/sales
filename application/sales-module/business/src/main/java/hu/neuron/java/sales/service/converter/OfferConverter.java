package hu.neuron.java.sales.service.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import hu.neuron.java.core.entity.OfferEntity;
import hu.neuron.java.sales.service.vo.OfferVO;

@Singleton
public class OfferConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public OfferVO toVO(OfferEntity dto){
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, OfferVO.class);
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
		return mapper.map(vo, OfferEntity.class);
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
