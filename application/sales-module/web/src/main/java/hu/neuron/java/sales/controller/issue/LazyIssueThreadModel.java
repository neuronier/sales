package hu.neuron.java.sales.controller.issue;

import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyIssueThreadModel extends LazyDataModel<IssueThreadVO> {

	private static final long serialVersionUID = -4091722103838409971L;

	private List<IssueThreadVO> visibleIssueThreadList;
	
	private IssueThreadServiceRemote issueThreadService;

	public LazyIssueThreadModel(IssueThreadServiceRemote issueThreadService) {
		super();
		this.issueThreadService = issueThreadService;
	}

	@Override
	public IssueThreadVO getRowData(String rowkey) {
		if (visibleIssueThreadList != null || rowkey != null) {
			for (IssueThreadVO issueThreadVO : visibleIssueThreadList) {
				if (issueThreadVO.getThreadId().equals(rowkey)) {
					return issueThreadVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(IssueThreadVO issueThreadVO) {
		if (issueThreadVO == null) {
			return null;
		}
		return issueThreadVO.getThreadId();
	}

	@Override
	public List<IssueThreadVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "clientId";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleIssueThreadList = issueThreadService.getIssueThreadList(first / pageSize, pageSize, sortField, dir, filter, filterColumnName);

		int dataSize = issueThreadService.getIssueThreadCount();

		this.setRowCount(dataSize);

		return visibleIssueThreadList;
	}


	public List<IssueThreadVO> getVisibleIssueThreadList() {
		return visibleIssueThreadList;
	}

	public void setVisibleIssueThreadList(List<IssueThreadVO> visibleIssueThreadList) {
		this.visibleIssueThreadList = visibleIssueThreadList;
	}

	public IssueThreadServiceRemote getIssueThreadService() {
		return issueThreadService;
	}

	public void setIssueThreadService(IssueThreadServiceRemote issueThreadService) {
		this.issueThreadService = issueThreadService;
	}
	
}
