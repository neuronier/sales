package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dao.UserDAO;
import hu.neuron.java.core.entity.Role;
import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.converter.RoleConverter;
import hu.neuron.java.sales.service.converter.UserConverter;
import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


@Stateless(mappedName = "RoleService", name = "RoleService")
@Remote(RoleServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RoleServiceImpl implements RoleServiceRemote, Serializable {

	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
	private static final long serialVersionUID = 806957209356013823L;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	UserDAO userDao;

	@Autowired
	RoleDAO roleDao;

	@EJB
	RoleConverter roleConverter;

	@EJB
	UserConverter userConverter;

	public RoleServiceImpl() {
	}

	@Override
	public UserVO setUpRoles(UserVO vo) throws Exception {
		logger.debug(entityManager);
		List<Role> roles;
		try {
			roles = roleDao.findRolesByUserId(vo.getId());
			vo.setRoles(roleConverter.toVO(roles));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;

	}

	@Override
	public int getRowNumber() {
		return (int) userDao.count();
	}

	@Override
	public List<RoleVO> getRoles() {
		return roleConverter.toVO(roleDao.findAll());
	}

	@Override
	public RoleVO getRoleByName(String role) {
		RoleVO vo = null;
		try {
			vo = roleConverter.toVO(roleDao.findRoleByName(role));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void saveRole(RoleVO roleVO) {
		roleDao.save(roleConverter.toEntity(roleVO));
	}

	@Override
	public void updateRole(RoleVO roleVO) {
		roleDao.save(roleConverter.toEntity(roleVO));

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
