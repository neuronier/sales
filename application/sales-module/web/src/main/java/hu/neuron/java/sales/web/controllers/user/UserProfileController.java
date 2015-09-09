package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.ClientOfferVO;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.web.pdf.BillGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ViewScoped
@ManagedBean(name = "userProfileController")
public class UserProfileController {

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;
	
	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;
	
	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote spService;
	
	@EJB(name = "ClientOfferService", mappedName = "ClientOfferService")
	private ClientOfferServiceRemote clientOfferService;

	private UserVO currentUser;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String oldPassword;

	private StreamedContent pdf;

	@PostConstruct
	public void init() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		try {
			currentUser = userService.findUserByUserName(user.getUsername());

			name = currentUser.getName();
			userName = currentUser.getUserName();
			email = currentUser.getEmail();
			password = "";

		} catch (Exception e) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR!", "We can't load your details."));
		}
		
		try {
			
			ClientVO client = clientService.findAll().get(0);
			
			SalesPointVO sp = spService.findAll().get(0);
			sp.setSalePointId(sp.getAddress().getCity()+"/1321");
			sp.setSalePointPhoneNumber("+36301234554");
			List<ClientOfferVO> offers = clientOfferService.findClientOfferByClientId(client.getClientId());
			client.setClientId(client.getUserName()+"/"+new SimpleDateFormat("yyyy/MM/dd").format(client.getRegistrationDate()));
			pdf = new DefaultStreamedContent(BillGenerator.createBill(client, offers, new Date(), sp), "pdf", "teszt.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveChanges() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
			currentUser.setEmail(email);
			if (!password.isEmpty()) {

				String encPassword = bCryptPasswordEncoder.encode(password);
				currentUser.setPassword(encPassword);

			}
			userService.saveUser(currentUser);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Your details are saved succesfully."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Wrong old password!"));
		}

	}

	public UserServiceRemote getUserService() {
		return userService;
	}

	public void setUserService(UserServiceRemote userService) {
		this.userService = userService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public StreamedContent getPdf() {
		return pdf;
	}

	public void setPdf(StreamedContent pdf) {
		this.pdf = pdf;
	}

}
