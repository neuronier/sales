package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.ProductTypeVO;
import java.util.List;

public interface ProductTypeServiceRemote {
	
	public void saveProductType(ProductTypeVO productType);
	
	public void updateProductType(ProductTypeVO productType);
	
	public void removeProductType(ProductTypeVO productType);
	
	public ProductTypeVO findProductTypeByName(String name) throws Exception;
	
	public List<ProductTypeVO> getProductTypes(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);

	public List<ProductTypeVO> findAll();

	public int getRowNumber();

}
