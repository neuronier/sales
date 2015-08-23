package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.WarehouseDAO;
import hu.neuron.java.core.entity.Warehouse;
import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.converter.WarehouseConverter;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "WarehouseService", name = "WarehouseService")
@Remote(WarehouseServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class) //For Unit tests to work
public class WarehouseServiceImpl implements WarehouseServiceRemote,
		Serializable {
 
	private static final long serialVersionUID = -5808139634153296284L;
	
	@Autowired
	WarehouseDAO warehouseDao;
	
	@Autowired
	WarehouseConverter whConv;

	@Override
	public WarehouseVO findWarehouseByWarehouseName(String warehouseName)
			throws Exception {
		
		return whConv.toVO(warehouseDao.findWarehouseByWarehouseName(warehouseName));
	}

	@Override
	public WarehouseVO findWarehouseByWarehouseId(String warehouseId)
			throws Exception {
		return whConv.toVO(warehouseDao.findWarehouseByWarehouseId(warehouseId));
	}

	@Override
	public List<WarehouseVO> getWarehouses(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new Sort.Order(dir, sortField)));
		Page<Warehouse> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = warehouseDao.findByWarehouseNameStartsWith(filter, pageRequest);
		} else {
			entities = warehouseDao.findAll(pageRequest);
		}

		List<WarehouseVO> ret = whConv.toVO(entities.getContent());

		return ret;
	}

	@Override
	public void saveWarehouse(WarehouseVO vo) {
		warehouseDao.save(whConv.toEntity(vo));
	}

	@Override
	public void updateWarehouse(WarehouseVO vo) {
		warehouseDao.save(whConv.toEntity(vo));
	}

	@Override
	public void removeWarehouse(WarehouseVO vo) {
		warehouseDao.delete(whConv.toEntity(vo));
	}

	@Override
	public List<WarehouseVO> findAll() {
		
		return whConv.toVO(warehouseDao.findAll());
	}

	@Override
	public int getRowNumber() {
		
		return (int) warehouseDao.count();
	}

}
