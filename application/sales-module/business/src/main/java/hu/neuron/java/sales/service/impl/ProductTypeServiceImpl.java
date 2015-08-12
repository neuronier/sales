package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ProductTypeDAO;
import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.converter.ProductTypeConverter;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
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

@Stateless(mappedName = "ProductTypeService", name = "ProductTypeService")
@Remote(ProductTypeServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ProductTypeServiceImpl implements ProductTypeServiceRemote,
		Serializable {

	private static final long serialVersionUID = -541159372291863297L;

	@Autowired
	ProductTypeDAO productTypeDao;

	@Autowired
	ProductTypeConverter ptConverter;

	@Override
	public void saveProductType(ProductTypeVO productType) {
		productTypeDao.save(ptConverter.toEntity(productType));
	}

	@Override
	public void updateProductType(ProductTypeVO productType) {
		productTypeDao.save(ptConverter.toEntity(productType));

	}

	@Override
	public void removeProductType(ProductTypeVO productType) {
		productTypeDao.delete(ptConverter.toEntity(productType));

	}

	@Override
	public List<ProductTypeVO> getProductTypes(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new Sort.Order(dir, sortField)));
		Page<ProductTypeEntity> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = productTypeDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = productTypeDao.findAll(pageRequest);
		}

		List<ProductTypeVO> ret = ptConverter.toVO(entities.getContent());

		return ret;
	}

	@Override
	public ProductTypeVO findProductTypeByName(String name) throws Exception {
		ProductTypeVO rVO = ptConverter.toVO(productTypeDao.findProductTypeEntityByName(name));
		return rVO;
	}

	@Override
	public List<ProductTypeVO> findAll() {
		List<ProductTypeVO> rvList = ptConverter.toVO(productTypeDao.findAll());
		return rvList;
	}
	
	@Override
	public int getRowNumber() {
		return (int) productTypeDao.count();
	}

}
