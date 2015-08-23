package hu.neuron.java.sales.service.impl;

import hu.neuron.java.core.dao.IssueMessageDAO;
import hu.neuron.java.core.entity.IssueMessage;
import hu.neuron.java.sales.service.IssueMessageServiceRemote;
import hu.neuron.java.sales.service.converter.IssueMessageConverter;
import hu.neuron.java.sales.service.vo.IssueMessageVO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Stateless(mappedName = "IssueMessageService", name = "IssueMessageService")
@Remote(IssueMessageServiceRemote.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class IssueMessageServiceImpl implements IssueMessageServiceRemote, Serializable{

	private static final long serialVersionUID = 1592904839407646632L;
	
	@Autowired
	IssueMessageDAO issueMessageDAO;

	@Autowired
	IssueMessageConverter issueMessageConverter;

	@Override
	public IssueMessageVO saveIssueMessage(IssueMessageVO issueMessage) {
		IssueMessage im = issueMessageDAO.save(issueMessageConverter.toEntity(issueMessage));
		return issueMessageConverter.toVO(im);
	}

	@Override
	public void removeIssueMessage(IssueMessageVO issueMessage) {
		issueMessageDAO.delete(issueMessageConverter.toEntity(issueMessage));
	}

	@Override
	public List<IssueMessageVO> findByThreadId(String threadId) {
		return issueMessageConverter.toVO(issueMessageDAO.findByThreadId(threadId));
	}

	@Override
	public IssueMessageVO findByIssueMessageId(String issueMessageId) {
		return issueMessageConverter.toVO(issueMessageDAO.findByMessageId(issueMessageId));
	}

}
