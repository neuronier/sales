package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.InventoryVO;

import java.util.List;

public interface InventoryWebClient {
	public List<InventoryVO> refreshInventory(String warehouseId);
}
