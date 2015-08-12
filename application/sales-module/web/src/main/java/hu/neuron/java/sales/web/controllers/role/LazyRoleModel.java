package hu.neuron.java.sales.web.controllers.role;

import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyRoleModel extends LazyDataModel<RoleVO>{

	private static final long serialVersionUID = -3627506723987836217L;
	
	private List<RoleVO> visibleRolesList;

	private RoleServiceRemote roleService;
	
	public LazyRoleModel( RoleServiceRemote roleServicee) {
		super();
		this.roleService = roleServicee;
	}

	@Override
	public RoleVO getRowData(String rowkey) {
		if (visibleRolesList != null || rowkey != null) {
			for (RoleVO roleVO : visibleRolesList) {
				if (roleVO.getId().toString().equals(rowkey)) {
					return roleVO;
				}
			}
		}
		return null;
	}

	@Override
	public List<RoleVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
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
		visibleRolesList = roleService.getRoles(first / pageSize, pageSize, sortField, dir, filter, filterColumnName);

		int dataSize = roleService.getRoleCount();

		this.setRowCount(dataSize);

		return visibleRolesList;
	}

	@Override
	public Object getRowKey(RoleVO roleVO) {
		if (roleVO == null) {
			return null;
		}
		return roleVO.getId();
	}
	
	
}
