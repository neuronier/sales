package hu.neuron.java.core.dao;

import hu.neuron.java.core.dao.AddressDAO;
import hu.neuron.java.core.dao.ClientDAO;
import hu.neuron.java.core.entity.Address;
import hu.neuron.java.core.entity.Client;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ClientDaoTest {

	private static final Logger logger = Logger.getLogger(ClientDaoTest.class);
	
		private static Client client;
		private static Address address;
		
		@Autowired
		ClientDAO clientDAO;
		
		@Autowired
		AddressDAO addressDAO;
		
		@Test
		public void test1Save() {
			try {
				client = new Client();
				client.setUserName("testUser");
				client.setPassword("test");
				client.setEmailAddress("test@test.test");
				client.setName("Test Elek");
				client.setPhoneNumber("06905346754");
				
				address = new Address();
				address.setCity("City");
				address.setStreet("Street");
				address.setZipCode("0242");
				address.setHouseNumber("43");
				address.setAddressId("1");
				
				address = addressDAO.save(address);
				
				List<Address> listOfAddress = new ArrayList<>();
				listOfAddress.add(address);
				
//				client.setDeliveryAddress(listOfAddress);
//				client.setBillingAddress(listOfAddress);
				client = clientDAO.save(client);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

		@Test
		public void test2Update() {
			try {
				client.setUserName("TestUpdate");
				clientDAO.save(client);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

		@Test
		public void test3Find() {
			try {
				Client rvDTO = clientDAO.findOne(client.getId());
				logger.info("rv: " + rvDTO);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

		@Test
		public void test4Find() {
			try {
			Client rvDTO = clientDAO.findClientByUserName(client.getUserName());
				logger.info("rv: " + rvDTO);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

		@Test
		public void test5FindAll() {
			try {
				List<Client> clients = clientDAO.findAll();
				for (Client clientDTO : clients) {
					logger.info("rv: " + clientDTO);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}

		@Test
		public void test6Delete() {
			try {
				addressDAO.delete(address.getId());
				clientDAO.delete(client.getId());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
}
