package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.util.List;

public interface IssueThreadServiceRemote {

	public enum Status {
		NEW {
			@Override
			public String toString() {
				return "New";
			}
		},
		ONGOING {
			@Override
			public String toString() {
				return "Ongoing";
			}
		},
		RESOLVED {
			@Override
			public String toString() {
				return "Resolved";
			}
		}
	}

	public IssueThreadVO saveIssueThread(IssueThreadVO issueThread);

	public void removeIssueThread(IssueThreadVO issueThread);

	public IssueThreadVO findByThreadId(String threadId);

	public List<IssueThreadVO> findByClientId(String clientId);

	public int getIssueThreadCount();

	public List<IssueThreadVO> getIssueThreadList(int i, int pageSize, String sortField, int dir, String filter, String filterColumnName);

	public long countOngoingIssueThread();

}
