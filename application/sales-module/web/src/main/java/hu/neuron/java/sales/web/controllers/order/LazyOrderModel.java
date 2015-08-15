package hu.neuron.java.sales.web.controllers.order;

import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.vo.OrderVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyOrderModel extends LazyDataModel<OrderVO> {

	private static final long serialVersionUID = 1L;

	private List<OrderVO> visibleOrderList;

	private OrderServiceRemote orderService;

	public LazyOrderModel(OrderServiceRemote orderService) {
		super();
		this.orderService = orderService;
	}

	@Override
	public OrderVO getRowData(String rowKey) {
		if (visibleOrderList != null || rowKey != null) {
			for (OrderVO orderVO : visibleOrderList) {
				if (orderVO.getId().toString().equals(rowKey)) {
					return orderVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(OrderVO object) {
		if (object == null) {
			return null;
		}
		return object.getId();
	}

	@Override
	public List<OrderVO> load(int first, int pageSize, String sortField,
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
		visibleOrderList = orderService.getOrderVOs(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName);

		int dataSize = orderService.getRowNumber();
		this.setRowCount(dataSize);

		return visibleOrderList;
	}

}