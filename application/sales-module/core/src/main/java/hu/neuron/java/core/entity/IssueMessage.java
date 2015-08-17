package hu.neuron.java.core.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "IssueMessage")
@NamedQueries({
		@NamedQuery(name = "IssueMessage.findByMessageId", query = "SELECT im FROM IssueMessage im  WHERE im.messageId = :messageId"),
		@NamedQuery(name = "IssueMessage.findByThreadId", query = "SELECT im FROM IssueMessage im  WHERE im.threadId = :threadId")
})
public class IssueMessage extends Base{

	private static final long serialVersionUID = -5182968180489611694L;

	private String messageId;
	
	@Column(length = 5000)
	private String Text;
	
	private Date date;
	
	private String threadId;
	
	private String owner;

	public IssueMessage() {
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
