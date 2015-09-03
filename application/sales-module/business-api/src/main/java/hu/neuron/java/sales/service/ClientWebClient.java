package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ClientVO;

import java.util.List;

public interface ClientWebClient {
	public List<ClientVO> refreshClients();
}
