package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.entity.Client;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.converter.ClientConverter;
import hu.neuron.java.sales.service.vo.ClientVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "ClientService", name = "ClientService")
@Remote(ClientServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
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
		ClientVO vo = clientConverter.toVO(clientDAO.findByName(name));
		return vo;
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
	public ClientVO saveClient(ClientVO clientVO) {
		Client client = clientDAO.save(clientConverter.toEntity(clientVO));
		return clientConverter.toVO(client);
	}

	@Override
	public List<ClientVO> findAll() {
		List<ClientVO> rvo = clientConverter.toVO(clientDAO.findAll());
		return rvo;
	}

	@Override
	public ClientVO findByUserName(String userName) {
		return clientConverter.toVO(clientDAO.findClientByUserName(userName));
	}

	@Override
	public ClientVO findByClientId(String clientId) {
		return clientConverter.toVO(clientDAO.findByClientId(clientId));
	}

	@Override
	public void removeClient(String clientId) {
		Client clientToRemove = clientDAO.findByClientId(clientId);
		clientDAO.delete(clientToRemove);
	}

	@Override
	public int findNumberOfClientsBeforeDate(Date date) {
		return clientDAO.findNumberOfCliensBeforeDate(date);
	}

}