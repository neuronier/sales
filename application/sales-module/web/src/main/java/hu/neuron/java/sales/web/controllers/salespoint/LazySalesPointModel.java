package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazySalesPointModel extends LazyDataModel<SalesPointVO> {

	private static final long serialVersionUID = 58393515579651203L;

	private List<SalesPointVO> visibleSalesPointList;

	private SalesPointServiceRemote salesPointService;

	public LazySalesPointModel(SalesPointServiceRemote salesPointService) {
		super();
		this.salesPointService = salesPointService;
	}

	@Override
	public SalesPointVO getRowData(String rowkey) {
		if (visibleSalesPointList != null || rowkey != null) {
			for (SalesPointVO salesPointVO : visibleSalesPointList) {
				if (salesPointVO.getId().toString().equals(rowkey)) {
					return salesPointVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(SalesPointVO salesPointVO) {
		if (salesPointVO == null) {
			return null;
		}
		return salesPointVO.getId();
	}

	@Override
	public List<SalesPointVO> load(int first, int pageSize, String sortField,
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
		visibleSalesPointList = salesPointService.getSalePoints(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName);

		int dataSize = salesPointService.getRowNumber();

		this.setRowCount(dataSize);

		return visibleSalesPointList;

	}

}
