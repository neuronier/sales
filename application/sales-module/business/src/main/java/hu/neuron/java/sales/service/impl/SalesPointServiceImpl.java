package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.core.entity.SalesPoint;
import hu.neuron.java.sales.service.SalesPointServiceLocale;
import hu.neuron.java.sales.service.converter.SalesPointConverter;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Stateless(mappedName = "SalesPointService", name = "SalesPointService")
@Remote(SalesPointServiceLocale.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SalesPointServiceImpl implements SalesPointServiceLocale,
		Serializable {

	private static final long serialVersionUID = -541159372291863297L;

	@EJB
	SalesPointDAO salesDao;

	@EJB
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

}
