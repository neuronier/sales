package hu.neuron.java.sales.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hu.neuron.java.sales.service.IssueMessageServiceRemote;
import hu.neuron.java.sales.service.vo.IssueMessageVO;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IssueMessageServiceTest {

	private EJBContainer ejbContainer;

	@Before
	public void startTheContainer() throws Exception {
		final Properties p = new Properties();

		p.put("hu.neuron.java.jpa.hibernate.hbm2ddl.auto", "create");
		p.put("hu.neuron.java.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		p.put("hu.neuron.TestDataSource", "new://Resource?type=DataSource");
		p.put("hu.neuron.TestDataSource.JtaManaged", "false");
		p.put("hu.neuron.TestDataSource.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("hu.neuron.TestDataSource.JdbcUrl", "jdbc:hsqldb:mem:aname");

		ejbContainer = EJBContainer.createEJBContainer(p);
		ejbContainer.getContext().bind("inject", this);
	}

	@EJB(mappedName = "IssueMessageService", name = "IssueMessageService")
	IssueMessageServiceRemote issueMessageService;
	
	@Test
	public void test1CRUD() throws Exception {
		
		IssueMessageVO issueMessage = new IssueMessageVO();
		issueMessage.setDate(new Date());
		issueMessage.setOwner("Teszt");
		issueMessage.setId(1L);
		issueMessage.setText("Test message.");
		issueMessage.setMessageId("messageId");
		issueMessage.setThreadId("threadId");
		
		issueMessage = issueMessageService.saveIssueMessage(issueMessage);
		
		List<IssueMessageVO> issues = issueMessageService.findByThreadId("threadId");
		assertEquals(1,issues.size());
		
		assertEquals(issueMessage, issueMessageService.findByIssueMessageId("messageId"));
		
		issueMessage.setText("updated");
		issueMessage = issueMessageService.saveIssueMessage(issueMessage);
		
		assertEquals(issueMessage, issueMessageService.findByIssueMessageId("messageId"));
		assertEquals("updated", issueMessage.getText());
		
		issueMessageService.removeIssueMessage(issueMessage);
		assertNull(issueMessageService.findByIssueMessageId("messageId2"));
		
	}
	
	
	@After
	public void destroy() {
		ejbContainer.close();
	}
	
}
