package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.IssueMessageVO;

import java.util.List;

public interface IssueMessageServiceRemote {
	
	public String saveIssueMessage(IssueMessageVO issueMessage);
	
	public void removeIssueMessage(IssueMessageVO issueMessage);
	
	public List<IssueMessageVO> findByThreadId(String threadId);
	
	public IssueMessageVO findByIssueMessageId(String issueMessageId);

}
