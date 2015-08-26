package hu.neuron.java.sales.web.controllers.customer;

import java.util.List;
import java.util.Map;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyCustomerModel extends LazyDataModel<ClientVO> {

	private static final long serialVersionUID = 1L;

	private List<ClientVO> visibleClientList;

	private ClientServiceRemote clientService;

	public LazyCustomerModel(ClientServiceRemote clientService) {
		super();
		this.clientService = clientService;
	}

	@Override
	public ClientVO getRowData(String rowKey) {
		if (visibleClientList != null || rowKey != null) {
			for (ClientVO clientVO : visibleClientList) {
				if (clientVO.getId().toString().equals(rowKey)) {
					return clientVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(ClientVO object) {
		if (object == null) {
			return null;
		}
		return object.getId();
	}

	@Override
	public List<ClientVO> load(int first, int pageSize, String sortField,
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
		visibleClientList = clientService.getClientList(first / pageSize,
				pageSize, sortField, dir, filter, filterColumnName);

		int dataSize = clientService.getRowNumber();
		this.setRowCount(dataSize);

		return visibleClientList;
	}

}
