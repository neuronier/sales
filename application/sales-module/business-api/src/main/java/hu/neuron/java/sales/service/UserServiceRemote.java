package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.Date;
import java.util.List;

public interface UserServiceRemote {

	public List<UserVO> findUserByName(String name) throws Exception;
	
	public UserVO findUserByUserId(String userId) throws Exception;

	public UserVO findUserByUserName(String userName) throws Exception;

	public List<UserVO> getUserList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public UserVO saveUser(UserVO selectedUser);
	
	public UserVO updateUser(UserVO selectedUser);
	
	public void removeUser(UserVO selectedUser) throws Exception;

	public List<UserVO> getUserList();

	public int getUserCount();
	
	public List<UserVO> findAll();
	
	public List<RoleVO> findRolesToUser(UserVO userVo);
	
	public void addRoleToUser(UserVO user, RoleVO role);
	
	public void removeRoleFromUser(UserVO user, RoleVO role);
	
	public String getDefaultPassword();
	
	public List<UserVO> findByRegistrationDateBetween(Date from, Date to);
	
	public int findNumberOfUsersBeforeDate(Date date);

	public List<UserVO> findUserBySalesPoint(SalesPointVO salesPoint) throws Exception;


}
