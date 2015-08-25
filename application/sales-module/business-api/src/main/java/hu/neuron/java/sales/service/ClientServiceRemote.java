package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ClientVO;

import java.util.Date;
import java.util.List;

public interface ClientServiceRemote {
	
	public ClientVO findClientByName(String name) throws Exception;
	
	public ClientVO findByUserName(String userName);
	
	public ClientVO findByClientId(String clientId);

	public List<ClientVO> getClientList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public ClientVO saveClient(ClientVO client);
	
	public void removeClient(String clientId);
	
	public List<ClientVO> findAll();

	public int findNumberOfClientsBeforeDate(Date date);

}