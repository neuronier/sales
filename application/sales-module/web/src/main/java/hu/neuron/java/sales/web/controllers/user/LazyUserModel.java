package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserModel extends LazyDataModel<UserVO> {

	private static final long serialVersionUID = -5307370998080747212L;

	private List<UserVO> visibleUserList;

	private UserServiceRemote userService;

	private RoleServiceRemote roleService;

	public LazyUserModel(UserServiceRemote userServiceRemote, RoleServiceRemote roleServiceRemote) {
		super();
		this.userService = userServiceRemote;
		this.roleService = roleServiceRemote;
	}

	@Override
	public UserVO getRowData(String rowkey) {
		if (visibleUserList != null || rowkey != null) {
			for (UserVO userVO : visibleUserList) {
				if (userVO.getId().toString().equals(rowkey)) {
					return userVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(UserVO userVO) {
		if (userVO == null) {
			return null;
		}
		return userVO.getId();
	}

	@Override
	public List<UserVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
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
		int page = first / pageSize;
		visibleUserList = userService.getUserList(page, pageSize, sortField, dir, filter, filterColumnName);
		
		if(visibleUserList.size() == 0 && page > 0) {
			page -= 1;
			visibleUserList = userService.getUserList(page, pageSize, sortField, dir, filter, filterColumnName);
		}
		
		int dataSize = userService.getUserCount();

		this.setRowCount(dataSize);

		return visibleUserList;
	}

	public UserServiceRemote getUserServiceRemote() {
		return userService;
	}

	public void setUserServiceRemote(UserServiceRemote userServiceRemote) {
		this.userService = userServiceRemote;
	}

	public RoleServiceRemote getRoleServiceRemote() {
		return roleService;
	}

	public void setRoleServiceRemote(RoleServiceRemote roleServiceRemote) {
		this.roleService = roleServiceRemote;
	}

}
