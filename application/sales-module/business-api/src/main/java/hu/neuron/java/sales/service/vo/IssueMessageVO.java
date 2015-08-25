package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class IssueMessageVO implements Serializable {

	private static final long serialVersionUID = 1228668701098572097L;

	private Long Id;
	
	private String messageId;

	private String text;

	private Date date;

	private String threadId;
	
	private String owner;

	public IssueMessageVO() {
		super();
		this.messageId = UUID.randomUUID().toString();
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueMessageVO other = (IssueMessageVO) obj;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IssueMessageVO [messageId=" + messageId + ", text=" + text + ", date=" + date + ", threadId=" + threadId + ", owner=" + owner + "]";
	}
	
}
