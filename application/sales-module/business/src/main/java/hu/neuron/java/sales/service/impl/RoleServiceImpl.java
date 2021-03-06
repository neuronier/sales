package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dao.UserDAO;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.converter.RoleConverter;
import hu.neuron.java.sales.service.converter.UserConverter;
import hu.neuron.java.sales.service.vo.RoleVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


@Stateless(mappedName = "RoleService", name = "RoleService")
@Remote(RoleServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class RoleServiceImpl implements RoleServiceRemote, Serializable {

	private static final long serialVersionUID = 806957209356013823L;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserDAO userDao;

	@Autowired
	RoleDAO roleDao;

	@Autowired
	RoleConverter roleConverter;

	@Autowired
	UserConverter userConverter;

	public RoleServiceImpl() {
	}

	@Override
	public List<RoleVO> getRoles() {
		return roleConverter.toVO(roleDao.findAll());
	}

	@Override
	public RoleVO getRoleByName(String role) throws Exception{
		return roleConverter.toVO(roleDao.findRoleByName(role));
	}

	@Override
	public RoleVO saveRole(RoleVO roleVO) {
		Role role = roleDao.save(roleConverter.toEntity(roleVO));
		return roleConverter.toVO(role);
	}

	@Override
	public void removeRole(RoleVO roleVO) {
		roleDao.delete(roleVO.getId());

	}

	@Override
	public List<RoleVO> getRoles(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {

		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Role> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = roleDao.findByNameStartsWith(filter, pageRequest);
		} else {
			entities = roleDao.findAll(pageRequest);
		}

		List<RoleVO> ret = roleConverter.toVO(entities.getContent());

		return ret;

	}

	@Override
	public int getRoleCount() {
		return (int) roleDao.count();
	}

}
