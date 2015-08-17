package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.AddressVO;

import java.util.List;

public interface AddressServiceRemote {

	public List<AddressVO> findAddressByCity(String city) throws Exception;

	public AddressVO findAddressByAddressId(String addressId) throws Exception;

	public List<AddressVO> getAddressList(int i, int pageSize,
			String sortField, int dir, String filter, String filterColumnName);

	public void saveAddress(AddressVO address);

	public void updateAddress(AddressVO address);

	public void removeAddress(AddressVO address);

	public List<AddressVO> findAll();

	public int getRowNumber();

	public List<String> findAllCities() throws Exception;

	public AddressVO findAddressByEquals(AddressVO addressVO) throws Exception;

}