package hu.neuron.java.sales.service.test;

import static org.junit.Assert.assertEquals;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;

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
public class ClientServiceTest {

//	private static final Logger logger = Logger.getLogger(RoleServiceTest.class);
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

	@EJB(mappedName = "ClientService", name = "ClientService")
	ClientServiceRemote clientService;
	
	@Test
	public void test1CRUD() throws Exception {
		
		ClientVO client = new ClientVO();
		client.setName("Test Client");
		client.setUserName("ctest");
		client.setPassword("bcyptedpw");
		client.setPhoneNumber("+36123456789");
		client.setEmailAddress("ctest@ier-sales.hu");
		client.setClientId("testclientId");
		client.setId(1L);
		
		client = clientService.saveClient(client);
		
		List<ClientVO> clients = clientService.getClientList(0, 10, "clientId", 1, "", "");
		assertEquals(1, clients.size());
		assertEquals(client, clients.get(0));
		assertEquals(client, clientService.findByClientId("testclientId"));
		
		client.setUserName("ctest1");
		client.setName("Client Test");
		client = clientService.saveClient(client);
		
		assertEquals(client, clientService.findClientByName("Client Test"));
		assertEquals(client, clientService.findByUserName("ctest1"));
		clients = clientService.getClientList(0, 10, "clientId", 2, "ctest1", "userName");
		assertEquals(1, clients.size());
		clients = clientService.findAll();
		assertEquals(1, clients.size());
		
		clientService.removeClient(client.getClientId());
		clients = clientService.getClientList(0, 10, "clientId", 2, "testclientId", "clientId");
		assertEquals(0, clients.size());
		
		clients = clientService.getClientList(0, 10, "clientId", 1, "Non existing name", "name");
		assertEquals(0, clients.size());
	}
	
	
	@After
	public void destroy() {
		ejbContainer.close();
	}
	
}
