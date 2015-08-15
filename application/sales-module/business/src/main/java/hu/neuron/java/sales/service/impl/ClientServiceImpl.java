package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.entity.Client;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.converter.ClientConverter;
import hu.neuron.java.sales.service.vo.ClientVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Stateless(mappedName = "ClientService", name = "ClientService")
@Remote(ClientServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClientServiceImpl implements ClientServiceRemote, Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(ClientServiceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ClientDAO clientDAO;
	
	@Autowired
	ClientConverter clientConverter;
	
	public ClientServiceImpl(){
	}

	@Override
	public ClientVO findClientByName(String name) throws Exception {
		logger.debug(entityManager);
		ClientVO vo = clientConverter.toVO(clientDAO.findClientByUserName(name));
		return vo;
	}

	@Override
	public void registrationClient(ClientVO clientVO) throws Exception {
		//Client client = 
				clientDAO.save(clientConverter.toEntity(clientVO));
	}

	@Override
	public List<ClientVO> getClientList(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		
		List<ClientVO> ret = new ArrayList<ClientVO>(size);
		Page<Client> entities;
		
		entities = clientDAO.findAll(pageRequest);
		
		if (entities != null && entities.getSize() > 0) {
			List<Client> contents = entities.getContent();
			for (Client c : contents) {
				ret.add(clientConverter.toVO(c));
			}
		}
		
		return ret;
	}

	@Override
	public void saveClient(ClientVO selectedClient) {
		clientDAO.save(clientConverter.toEntity(selectedClient));
	}

}