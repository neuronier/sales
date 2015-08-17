package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.IssueMessage;
import hu.neuron.java.sales.service.vo.IssueMessageVO;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IssueMessageConverter {
	
	@Autowired
	@Qualifier("mapper")
	Mapper mapper;
	
	public IssueMessageVO toVO(IssueMessage dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, IssueMessageVO.class);
	}

	public IssueMessage toEntity(IssueMessageVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, IssueMessage.class);
	}

	public List<IssueMessageVO> toVO(List<IssueMessage> dtos) {
		if (dtos == null) {
			return null;
		}
		List<IssueMessageVO> vos = new ArrayList<IssueMessageVO>();
		for (IssueMessage dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<IssueMessage> toEntity(List<IssueMessageVO> vos) {
		if (vos == null) {
			return null;
		}
		List<IssueMessage> dtos = new ArrayList<IssueMessage>();
		for (IssueMessageVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
