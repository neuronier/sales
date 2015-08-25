package hu.neuron.java.sales.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;

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
public class RoleServiceTest {

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

	@EJB(mappedName = "RoleService", name = "RoleService")
	RoleServiceRemote roleService;

	@Test
	public void test1CRUD() throws Exception {
		RoleVO role = new RoleVO();
		role.setName("ROLE_TEST");
		role.setId(1L);
		role.setRoleId("test_id");
		role = roleService.saveRole(role);

		List<RoleVO> roles = roleService.getRoles();
		assertEquals(1,roles.size());
		
		roles = roleService.getRoles(0,10,"name",1,"","");
		assertEquals(1,roles.size());
		
		assertEquals(role,roleService.getRoleByName("ROLE_TEST"));
		assertNull(roleService.getRoleByName("ROLE_NONEXIST"));

		role.setName("ROLE_TEST2");
		role = roleService.saveRole(role);
		
		roles = roleService.getRoles(0,10,"name",2,"ROLE_TEST","name");
		assertEquals(1,roles.size());
		
		assertEquals(1,roleService.getRoleCount());
		assertEquals(role,roleService.getRoleByName("ROLE_TEST2"));
		
		roleService.removeRole(role);
		
		roles = roleService.getRoles(0,10,"name",2,"ROLE_TEST","name");
		assertEquals(0,roles.size());
		
		roles = roleService.getRoles(0,10,"name",2,"ROLE_TEST","roleId");
		assertEquals(0,roles.size());
	}

	@After
	public void destroy() {
		ejbContainer.close();
	}

}
