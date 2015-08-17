package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ClientVO;

import java.util.List;

public interface ClientServiceRemote {
	public ClientVO findClientByName(String name) throws Exception;

	public void registrationClient(ClientVO clientVO) throws Exception;

	public List<ClientVO> getClientList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public void saveClient(ClientVO selectedClient);
	
	public List<ClientVO> findAll() throws Exception;

}