package hu.neuron.java.sales.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.List;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.apache.openejb.junit.jee.EJBContainerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EJBContainerRunner.class)
public class UserServiceTest {

	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	
	@EJB(mappedName = "UserService", name = "UserService")
	UserServiceRemote userService;
	
	@EJB(mappedName = "RoleService", name = "RoleService")
	RoleServiceRemote roleService;

	@Test
	public void test1CRUD() throws Exception{
		logger.info("Create new user");
		UserVO user = new UserVO();
		user.setName("Teszt Elek");
		user.setUserId("7fdea050-8748-4119-b90b-d85354a035d6");
		user.setPassword("$2a$04$7zgROKBLHzQ2mDe8suiXMODIFreyXRlXcn0SeBl9Bo5Efj8uckn6m");
		user.setUserName("telek");
		user.setPhoneNumber("+36703571591");
		user.setEmail("teszt@ier-sales.hu");
		user = userService.saveUser(user);

		List<UserVO> users = userService.findAll();
		
		assertEquals(1, users.size());
		assertEquals(user, users.get(0));
		assertEquals(user, userService.findUserByUserName("telek"));
		
		users = userService.getUserList(0, 10, "userName", 2, "telek", "userName");
		assertEquals(1, users.size());
		assertEquals(user, users.get(0));
		
		logger.info("Edit user");
		user.setName("Teszt Name");
		user = userService.saveUser(user);
		
		users = userService.getUserList();
		assertEquals(1, users.size());
		assertEquals(user, users.get(0));
		assertEquals(0, userService.findUserByName("Teszt Elek").size());
		assertEquals(user, userService.findUserByName("Teszt Name").get(0));
		assertEquals(1, userService.getUserCount());
		users = userService.getUserList(0, 10, "name", 1, "Teszt", "name");
		assertEquals(1, users.size());
		
		users = userService.getUserList(0, 10, "name", 1, "", "name");
		assertEquals(1, users.size());
		
		logger.info("Remove user");
		assertEquals(user, userService.findUserByUserId("7fdea050-8748-4119-b90b-d85354a035d6"));
		userService.removeUser(user);
		assertEquals(0, userService.findUserByName("Teszt Name").size());
		assertNull(userService.findUserByUserId("7fdea050-8748-4119-b90b-d85354a035d6"));
	}
	
	@Test
	public void test2CRUDWithRole() throws Exception{
		
		logger.info("Create new user");
		UserVO user = new UserVO();
		user.setName("Teszt Elek");
		user.setUserId("7fdea050-8748-4119-b90b-d85354a035d6");
		user.setPassword("$2a$04$7zgROKBLHzQ2mDe8suiXMODIFreyXRlXcn0SeBl9Bo5Efj8uckn6m");
		user.setUserName("telek");
		user.setPhoneNumber("+36703571591");
		user.setEmail("teszt@ier-sales.hu");
		user = userService.saveUser(user);
		
		assertEquals(0, userService.findRolesToUser(user).size());
		
		RoleVO role = new RoleVO();
		role.setName("ROLE_TEST");
		role = roleService.saveRole(role);
		
		userService.addRoleToUser(user, role);
		assertEquals(1, userService.findRolesToUser(user).size());
		assertEquals(role, userService.findRolesToUser(user).get(0));
		
		userService.removeRoleFromUser(user, role);
		assertEquals(0, userService.findRolesToUser(user).size());
		
		userService.removeUser(user);
		assertEquals(0, userService.findUserByName("Teszt Name").size());
		assertNull(userService.findUserByUserId("7fdea050-8748-4119-b90b-d85354a035d6"));
		
		List<UserVO> users = userService.getUserList(0, 10, "name", 1, "", "name");
		assertEquals(0, users.size());
		
	}
	
	@Test
	public void test3getDefaultPassword(){
		logger.info("test3getDefaultPassword: ");
		try{
			String defaultPassword = userService.getDefaultPassword();
			logger.info(defaultPassword);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
}
