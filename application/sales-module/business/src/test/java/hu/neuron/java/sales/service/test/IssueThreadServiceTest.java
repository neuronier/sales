package hu.neuron.java.sales.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.util.Date;

import javax.ejb.EJB;

import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EJBContainerRunner.class)
public class IssueThreadServiceTest {

	@EJB(mappedName = "IssueThreadService", name = "IssueThreadService")
	IssueThreadServiceRemote issueThreadService;

	@Test
	public void test1CRUD() throws Exception {
		IssueThreadVO thread = new IssueThreadVO();
		thread.setClientId("clientId");
		thread.setClientUserName("clientUserName");
		thread.setId(1L);
		thread.setLastUpdate(new Date());
		thread.setStatus("Ongoing");
		thread.setSubject("subject");
		thread.setThreadId("threadId");

		thread = issueThreadService.saveIssueThread(thread);

		assertEquals(1, issueThreadService.countOngoingIssueThread());
		assertEquals(thread, issueThreadService.findByThreadId("threadId"));
//		List<IssueThreadVO> threads = issueThreadService.getIssueThreadList(0, 10, "threadId", 1, "clientUserName", "clientUserName");
//		assertEquals(1, threads.size());
		assertEquals(1, issueThreadService.getIssueThreadCount());
		assertEquals(thread, issueThreadService.findByClientId("clientId").get(0));

		thread.setStatus("Resolved");
		thread = issueThreadService.saveIssueThread(thread);

		assertEquals(0, issueThreadService.countOngoingIssueThread());
		assertEquals(thread, issueThreadService.findByThreadId("threadId"));
//		threads = issueThreadService.getIssueThreadList(0, 10, "threadId", 2, "clientUserName", "clientUserName");
//		assertEquals(1, threads.size());
		assertEquals(1, issueThreadService.getIssueThreadCount());
		assertEquals(thread,issueThreadService.findByClientId("clientId").get(0));
		
		
		issueThreadService.removeIssueThread(thread);
		assertEquals(0, issueThreadService.countOngoingIssueThread());
		assertNull(issueThreadService.findByThreadId("threadId"));
//		threads = issueThreadService.getIssueThreadList(0, 10, "threadId", 2, "clientUserName", "clientUserName");
//		assertEquals(0, threads.size());
		assertEquals(0, issueThreadService.getIssueThreadCount());
		assertEquals(0, issueThreadService.findByClientId("clientId").size());

	}

}
