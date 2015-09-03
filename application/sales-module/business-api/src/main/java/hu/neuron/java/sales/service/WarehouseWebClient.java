package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.List;

public interface WarehouseWebClient {
	public List<WarehouseVO> refreshWarehouses() throws Exception;
}
