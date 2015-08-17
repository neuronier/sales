package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class IssueMessageVO implements Serializable {

	private static final long serialVersionUID = 1228668701098572097L;

	private String messageId;

	private String Text;

	private Date date;

	private String threadId;
	
	private String owner;

	public IssueMessageVO() {
		super();
		this.messageId = UUID.randomUUID().toString();
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
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
}
