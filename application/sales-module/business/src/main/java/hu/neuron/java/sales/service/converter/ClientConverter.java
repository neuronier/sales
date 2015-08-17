package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.Client;
import hu.neuron.java.sales.service.vo.ClientVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

	private static final Logger logger = Logger.getLogger(ClientConverter.class);

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	@PostConstruct
	void init() {

		logger.debug("ClientConverter init");
	}
	
	public ClientVO toVO(Client dto) {
		if (dto == null) {
			return null;
		}
		return mapper.map(dto, ClientVO.class);
	}

	public Client toEntity(ClientVO vo) {
		if (vo == null) {
			return null;
		}
		return mapper.map(vo, Client.class);
	}

	public List<ClientVO> toVO(List<Client> dtos) {
		if (dtos == null) {
			return null;
		}
		List<ClientVO> vos = new ArrayList<ClientVO>();
		for (Client dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<Client> toEntity(List<ClientVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Client> dtos = new ArrayList<Client>();
		for (ClientVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}