package hu.neuron.java.sales.service.salepoint;

import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.util.List;

public interface SalesPointServiceRemote {
	
	public void saveSalePoint(SalesPointVO salePoint);
	
	public void updateSalePoint(SalesPointVO salePoint);
	
	public void removeSalePoint(SalesPointVO salePoint);
	
	public List<SalesPointVO> getSalePoints(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);

}
