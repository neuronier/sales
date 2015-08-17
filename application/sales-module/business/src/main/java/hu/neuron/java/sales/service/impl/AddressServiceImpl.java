package hu.neuron.java.sales.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import hu.neuron.java.core.dao.AddressDAO;
import hu.neuron.java.core.entity.Address;
import hu.neuron.java.sales.service.AddressServiceRemote;
import hu.neuron.java.sales.service.converter.AddressConverter;
import hu.neuron.java.sales.service.vo.AddressVO;

@Stateless(mappedName = "AddressService", name = "AddressService")
@Remote(AddressServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class) //For Unit tests to work
public class AddressServiceImpl implements AddressServiceRemote, Serializable {

	private static final long serialVersionUID = 1838519677303448003L;
	
	@Autowired
	AddressDAO addressDao;
	
	@Autowired
	AddressConverter adConv;
	
	@Override
	public AddressVO findAddressByEquals(AddressVO addressVO) throws Exception {
		return adConv.toVO(addressDao.findAddressByEquals(
				addressVO.getCity(), addressVO.getStreet(), addressVO.getHouseNumber(), 
				addressVO.getZipCode()));
	}

	@Override
	public List<AddressVO> findAddressByCity(String city) throws Exception {
		return adConv.toVO(addressDao.findAddressByCity(city));
	}

	@Override
	public AddressVO findAddressByAddressId(String addressId) throws Exception {
		return adConv.toVO(addressDao.findAddressByAddressId(addressId));
	}

	@Override
	public List<AddressVO> getAddressList(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new Sort.Order(dir, sortField)));
		Page<Address> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = addressDao.findByCityStartsWith(filter, pageRequest);
		} else {
			entities = addressDao.findAll(pageRequest);
		}

		List<AddressVO> ret = adConv.toVO(entities.getContent());

		return ret;
	}

	@Override
	public void saveAddress(AddressVO address) {
		addressDao.save(adConv.toEntity(address));

	}
	
	@Override
	public int getRowNumber() {
		return (int) addressDao.count();
	}

	@Override
	public List<AddressVO> findAll() {
		return adConv.toVO(addressDao.findAll());
	}

	@Override
	public void updateAddress(AddressVO address) {
		saveAddress(address);
		
	}

	@Override
	public void removeAddress(AddressVO address) {
		addressDao.delete(adConv.toEntity(address));
		
	}

	@Override
	public List<String> findAllCities() throws Exception {
		List<AddressVO> res = findAll();
		Set<String> set = new HashSet<>();
		for(AddressVO ad : res){
			set.add(ad.getCity());
		}
		List<String> rv = new ArrayList<>(set.size());
		rv.addAll(set);
		return rv;
	}

}
