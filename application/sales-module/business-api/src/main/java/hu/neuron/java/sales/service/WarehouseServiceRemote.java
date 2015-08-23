package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.List;

public interface WarehouseServiceRemote {

	public WarehouseVO findWarehouseByWarehouseName(String warehouseName) throws Exception;

	public WarehouseVO findWarehouseByWarehouseId(String warehouseId) throws Exception;

	public List<WarehouseVO> getWarehouses(int i, int pageSize,
			String sortField, int dir, String filter, String filterColumnName);

	public void saveWarehouse(WarehouseVO warehouse);

	public void updateWarehouse(WarehouseVO warehouse);

	public void removeWarehouse(WarehouseVO warehouse);

	public List<WarehouseVO> findAll();

	public int getRowNumber();

}