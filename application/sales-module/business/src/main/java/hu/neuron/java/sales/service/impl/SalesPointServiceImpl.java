package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.core.entity.SalesPoint;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.converter.SalesPointConverter;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;

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

@Stateless(mappedName = "SalesPointService", name = "SalesPointService")
@Remote(SalesPointServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class SalesPointServiceImpl implements SalesPointServiceRemote,
		Serializable {

	private static final long serialVersionUID = -541159372291863297L;

	@Autowired
	SalesPointDAO salesDao;

	@Autowired
	SalesPointConverter spConverter;

	@Override
	public void saveSalePoint(SalesPointVO salePoint) {
		salesDao.save(spConverter.toEntity(salePoint));
	}

	@Override
	public void updateSalePoint(SalesPointVO salePoint) {
		salesDao.save(spConverter.toEntity(salePoint));

	}

	@Override
	public void removeSalePoint(SalesPointVO salePoint) {
		salesDao.delete(spConverter.toEntity(salePoint));

	}

	@Override
	public List<SalesPointVO> getSalePoints(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new Sort.Order(dir, sortField)));
		Page<SalesPoint> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = salesDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = salesDao.findAll(pageRequest);
		}

		List<SalesPointVO> ret = spConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public SalesPointVO findSalePointByName(String name) throws Exception {
		SalesPointVO rVO = spConverter.toVO(salesDao.findSalesPointByName(name));
		return rVO;
	}

	@Override
	public List<SalesPointVO> findAll() {
		List<SalesPointVO> rvList = spConverter.toVO(salesDao.findAll());
		return rvList;
	}
	
	@Override
	public int getRowNumber() {
		return (int) salesDao.count();
	}
	
	@Override
	public SalesPointVO findSalePointByAddress(AddressVO address) throws Exception {
		SalesPointVO rVO = spConverter.toVO(salesDao.findSalesPointByAddressId(address.getAddressId()));
		return rVO;
	}

}
