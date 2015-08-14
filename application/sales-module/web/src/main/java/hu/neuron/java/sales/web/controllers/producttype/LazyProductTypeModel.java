package hu.neuron.java.sales.web.controllers.producttype;

import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyProductTypeModel extends LazyDataModel<ProductTypeVO> {

	private static final long serialVersionUID = 6507205944843133123L;

	private List<ProductTypeVO> visibleProductTypeList;
	
	private ProductTypeServiceRemote productTypeService;

	public LazyProductTypeModel(ProductTypeServiceRemote productTypeService) {
		super();
		this.productTypeService = productTypeService;
	}

	@Override
	public ProductTypeVO getRowData(String rowkey) {
		if (visibleProductTypeList != null || rowkey != null) {
			for (ProductTypeVO productTypeVO : visibleProductTypeList) {
				if (productTypeVO.getID().toString().equals(rowkey)) {
					return productTypeVO;
				}
			} 
		}
		return null;
	}

	@Override
	public Object getRowKey(ProductTypeVO productTypeVO) {
		if (productTypeVO == null) {
			return null;
		}
		return productTypeVO.getID();
	}

	@Override
	public List<ProductTypeVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleProductTypeList = productTypeService.getProductTypes(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName);

		int dataSize = productTypeService.getRowNumber();

		this.setRowCount(dataSize);
 
		return visibleProductTypeList;
	}
	
}
