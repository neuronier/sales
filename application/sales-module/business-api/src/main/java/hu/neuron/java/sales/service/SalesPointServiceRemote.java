package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import java.util.List;

public interface SalesPointServiceRemote {
	
	public void saveSalePoint(SalesPointVO salePoint);
	
	public void updateSalePoint(SalesPointVO salePoint);
	
	public void removeSalePoint(SalesPointVO salePoint);
	
	public SalesPointVO findSalePointByName(String name) throws Exception;
	
	public List<SalesPointVO> getSalePoints(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);

	public List<SalesPointVO> findAll();

	public int getRowNumber();

	SalesPointVO findSalePointByAddress(AddressVO address) throws Exception;
	
	SalesPointVO findSalesPointBySalePointId(String salePointId) throws Exception;

}
