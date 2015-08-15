package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dao.UserDAO;
import hu.neuron.java.core.dao.UserRoleDAO;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.core.entity.UserRole;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.converter.RoleConverter;
import hu.neuron.java.sales.service.converter.UserConverter;
import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "UserService", name = "UserService")
@Remote(UserServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserServiceImpl implements UserServiceRemote, Serializable {

	private static final long serialVersionUID = -2855280432819032935L;

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserDAO userDao;

	@Autowired
	RoleDAO roleDao;
	
	@Autowired
	UserRoleDAO userRoleDao;

	@Autowired
	RoleConverter roleConverter;

	@Autowired
	UserConverter userConverter;

	public UserServiceImpl() {
	}

	@Override
	public UserVO findUserByName(String name) throws Exception{
		logger.debug(entityManager);
		UserVO vo = userConverter.toVO(userDao.findUserByName(name));
		return vo;

	}
	
	@Override
	public List<UserVO> getUserList(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		List<UserVO> ret = new ArrayList<UserVO>(size);
		Page<User> entities;

		if (filter.length() != 0 && filterColumnName.equals("userName")) {
			entities = userDao.findByUserNameStartsWith(filter, pageRequest);
		} else {
			entities = userDao.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<User> contents = entities.getContent();
			for (User m : contents) {
				ret.add(userConverter.toVO(m));

			}
		}
		return ret;
	}


	@Override
	public void saveUser(UserVO selectedUser) {
		userDao.save(userConverter.toEntity(selectedUser));
		userDao.flush();
	}

	@Override
	public List<UserVO> getUserList() {
		return userConverter.toVO(userDao.findAll());
	}

	@Override
	public int getUserCount() {
		return (int) userDao.count();
	}

	@Override
	public List<UserVO> findAll() {
		return userConverter.toVO(userDao.findAll());
	}

	@Override
	public void removeUser(UserVO user) {
		userDao.delete(user.getId());
		
	}
	
	@Override
	public List<RoleVO> findRolesToUser(UserVO userVo) {
		List<UserRole> roleIdList = userRoleDao.findUserRolesByUserId(userVo.getUserId());
		List<Role> roleList = new ArrayList<>();
		
		for (UserRole ur : roleIdList) {
				roleList.add(roleDao.findRoleByRoleId(ur.getRoleId()));
		}
		
		return roleConverter.toVO(roleList);
	}

	@Override
	public void addRoleToUser(UserVO user, RoleVO role) {
		UserRole userRole = new UserRole();
		userRole.setRoleId(role.getRoleId());
		userRole.setUserId(user.getUserId());
		
		userRoleDao.save(userRole);
		
	}

	@Override
	public void removeRoleFromUser(UserVO user, RoleVO role) {
		UserRole userRole = userRoleDao.findUserRoleByUserIdAndRoleId(user.getUserId(), role.getRoleId());
		userRoleDao.delete(userRole);
		
	}

	@Override
	public UserVO findUserById(Long id) throws Exception {
		return userConverter.toVO(userDao.findUserById(id));
	}

	@Override
	public UserVO findUserByUserName(String userName) throws Exception {
		return userConverter.toVO(userDao.findUserByName(userName));
	}

	@Override
	public UserVO findUserByUserId(String userId) throws Exception {
		return userConverter.toVO(userDao.findUserByUserId(userId));
	}

}
