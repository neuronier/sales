package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.IssueThread;
import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class IssueThreadConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public IssueThreadVO toVO(IssueThread dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, IssueThreadVO.class);
	}

	public IssueThread toEntity(IssueThreadVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, IssueThread.class);
	}

	public List<IssueThreadVO> toVO(List<IssueThread> dtos) {
		if (dtos == null) {
			return null;
		}
		List<IssueThreadVO> vos = new ArrayList<IssueThreadVO>();
		for (IssueThread dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<IssueThread> toEntity(List<IssueThreadVO> vos) {
		if (vos == null) {
			return null;
		}
		List<IssueThread> dtos = new ArrayList<IssueThread>();
		for (IssueThreadVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
	
}
