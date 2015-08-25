package hu.neuron.java.core.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "IssueThread")
@NamedQueries({
		@NamedQuery(name = "IssueThread.findByThreadId", query = "SELECT it FROM IssueThread it  WHERE it.threadId = :threadId"),
		@NamedQuery(name = "IssueThread.findByClientId", query = "SELECT it FROM IssueThread it  WHERE it.clientId = :clientId")
})
public class IssueThread extends Base{

	private static final long serialVersionUID = 9159421569896952603L;

	private String threadId;
	
	private String clientId;
	
	private String status;
	
	private String subject;
	
	private Date lastUpdate;

	public IssueThread() {
		super();
		this.threadId = UUID.randomUUID().toString();
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadID) {
		this.threadId = threadID;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientID) {
		this.clientId = clientID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
