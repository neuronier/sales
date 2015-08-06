package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.SalesPoint;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Singleton
public class SalesPointConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public SalesPointVO toVO(SalesPoint dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, SalesPointVO.class);
	}

	public SalesPoint toEntity(SalesPointVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, SalesPoint.class);
	}

	public List<SalesPointVO> toVO(List<SalesPoint> dtos) {
		if (dtos == null) {
			return null;
		}
		List<SalesPointVO> vos = new ArrayList<SalesPointVO>();
		for (SalesPoint dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<SalesPoint> toEntity(List<SalesPointVO> vos) {
		if (vos == null) {
			return null;
		}
		List<SalesPoint> dtos = new ArrayList<SalesPoint>();
		for (SalesPointVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
