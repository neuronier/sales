package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.dao.IssueMessageDAO;
import hu.neuron.java.core.dao.IssueThreadDAO;
import hu.neuron.java.core.entity.Client;
import hu.neuron.java.core.entity.IssueMessage;
import hu.neuron.java.core.entity.IssueThread;
import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.converter.IssueThreadConverter;
import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "IssueThreadService", name = "IssueThreadService")
@Remote(IssueThreadServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class IssueThreadServiceImpl implements IssueThreadServiceRemote, Serializable {

	private static final long serialVersionUID = -4932008222285229954L;

	@Autowired
	IssueThreadDAO issueThreadDAO;

	@Autowired
	IssueMessageDAO issueMessageDAO;

	@Autowired
	ClientDAO clientDAO;

	@Autowired
	IssueThreadConverter issueThreadConverter;

	@Override
	public IssueThreadVO saveIssueThread(IssueThreadVO issueThread) {
		IssueThread it = issueThreadDAO.save(issueThreadConverter.toEntity(issueThread));
		return issueThreadConverter.toVO(it);
	}


	@Override
	public void removeIssueThread(IssueThreadVO issueThread) {
		issueThreadDAO.delete(issueThreadConverter.toEntity(issueThread));
	}

	@Override
	public IssueThreadVO findByThreadId(String threadId) {
		return issueThreadConverter.toVO(issueThreadDAO.findByThreadId(threadId));
	}

	@Override
	public List<IssueThreadVO> findByClientId(String clientId) {
		return issueThreadConverter.toVO(issueThreadDAO.findByClientId(clientId));
	}

	@Override
	public List<IssueThreadVO> getIssueThreadList(int page, int size, String sortField, int sortOrder, String filter, String filterColumnName) {

		boolean sortedByClientUserName = false;
		boolean sortedByLastUpdate = false;

		if (sortField.equals("clientUserName")) {
			sortedByClientUserName = true;
			sortField = "clientId";
		} else if (sortField.equals("lastUpdate")) {
			sortedByLastUpdate = true;
			sortField = "clientId";
		}

		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		List<IssueThreadVO> ret = new ArrayList<IssueThreadVO>(size);
		Page<IssueThread> entities;

		if (filter.length() != 0 && filterColumnName.equals("clientUserName")) {
			entities = issueThreadDAO.findByClientIdIn(getClientIdsByUserName(filter), pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("status")) {
			entities = issueThreadDAO.findByStatusStartsWith(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("subject")) {
			entities = issueThreadDAO.findBySubjectStartsWith(filter, pageRequest);
		} else {
			entities = issueThreadDAO.findAll(pageRequest);
		}

		if (entities != null && entities.getSize() > 0) {
			List<IssueThread> contents = entities.getContent();
			for (IssueThread m : contents) {

				IssueThreadVO itVO = issueThreadConverter.toVO(m);
				itVO.setClientUserName(clientDAO.findByClientId(itVO.getClientId()).getUserName());
				itVO.setLastUpdate(getLastUpdateToIssueThread(itVO.getThreadId()));
				ret.add(itVO);
			}
		}

		if (sortedByClientUserName) {
			sortByClientUserName(ret, sortOrder);
		} else if (sortedByLastUpdate) {
			sortByLastUpdate(ret, sortOrder);
		}

		return ret;
	}

	@Override
	public int getIssueThreadCount() {
		return (int) issueThreadDAO.count();
	}

	private List<String> getClientIdsByUserName(String userName) {
		List<Client> clients = clientDAO.findByuserNameStartsWith(userName);
		List<String> clientIds = new ArrayList<>();
		for (Client client : clients) {
			clientIds.add(client.getClientId());
		}
		
		if(clientIds.isEmpty()) {
			clientIds.add("");
		}
		
		return clientIds;
	}

	private List<IssueThreadVO> sortByClientUserName(List<IssueThreadVO> issueThreads, int sortOrder) {
		if (sortOrder == 1) {
			Collections.sort(issueThreads, new Comparator<IssueThreadVO>() {
				@Override
				public int compare(IssueThreadVO o1, IssueThreadVO o2) {
					return o1.getClientUserName().compareTo(o2.getClientUserName());
				}
			});

		} else {
			Collections.sort(issueThreads, new Comparator<IssueThreadVO>() {
				@Override
				public int compare(IssueThreadVO o1, IssueThreadVO o2) {
					return -(o1.getClientUserName().compareTo(o2.getClientUserName()));
				}
			});
		}
		return issueThreads;
	}

	private List<IssueThreadVO> sortByLastUpdate(List<IssueThreadVO> issueThreads, int sortOrder) {
		if (sortOrder == 1) {
			Collections.sort(issueThreads, new Comparator<IssueThreadVO>() {
				@Override
				public int compare(IssueThreadVO o1, IssueThreadVO o2) {
					if (o1.getLastUpdate() == null && o2.getLastUpdate() == null) {
						return 0;
					}
					if (o1.getLastUpdate() == null) {
						return -1;
					}

					if (o2.getLastUpdate() == null) {
						return 1;
					}
					return o1.getLastUpdate().compareTo(o2.getLastUpdate());
				}
			});

		} else {
			Collections.sort(issueThreads, new Comparator<IssueThreadVO>() {
				@Override
				public int compare(IssueThreadVO o1, IssueThreadVO o2) {

					if (o1.getLastUpdate() == null && o2.getLastUpdate() == null) {
						return 0;
					}
					if (o1.getLastUpdate() == null) {
						return 1;
					}

					if (o2.getLastUpdate() == null) {
						return -1;
					}
					return -(o1.getLastUpdate().compareTo(o2.getLastUpdate()));
				}
			});
		}
		return issueThreads;
	}

	private Date getLastUpdateToIssueThread(String threadId) {
		Direction dir = Sort.Direction.DESC;
		IssueMessage lastIssueMessage = issueMessageDAO.findFirst1ByThreadId(threadId, new Sort(new org.springframework.data.domain.Sort.Order(dir,
				"date")));

		if (lastIssueMessage != null) {
			return lastIssueMessage.getDate();
		}

		return null;
	}

	@Override
	public long countOngoingIssueThread() {
		Long count = issueThreadDAO.countByStatus("Ongoing");
		return count == null ? 0 : count;
	}


}
