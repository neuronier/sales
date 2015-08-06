package hu.neuron.java.sales.service;

import java.util.List;

import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;

public interface UserServiceRemote {

	public UserVO findUserByName(String name) throws Exception;

	public void registrationUser(UserVO userVO) throws Exception;

	public List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public RoleVO getRoleByName(String role);

	public void saveUser(UserVO selectedUser);

	public List<UserVO> getUserList();

}
