package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class IssueThreadVO implements Serializable {

	private static final long serialVersionUID = -3539824510322540496L;

	private String threadId;

	private String clientId;
	
	private String clientUserName;

	private String status;

	private String subject;
	
	private Date lastUpdate;

	public IssueThreadVO() {
		super();
		this.threadId = UUID.randomUUID().toString();
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public String getClientUserName() {
		return clientUserName;
	}

	public void setClientUserName(String clientUserName) {
		this.clientUserName = clientUserName;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}	
}
