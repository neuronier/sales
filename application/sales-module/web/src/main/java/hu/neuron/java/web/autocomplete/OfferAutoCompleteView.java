package hu.neuron.java.web.autocomplete;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
public class OfferAutoCompleteView {

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;

	private String offer;

	private boolean checkBox;

	private boolean helper;

	private static ClientVO selectedClient;

	@PostConstruct
	public void init() {
		offer = null ;
		checkBox = false;
	}

	public OfferAutoCompleteView() {
		super();
	}

	public List<String> completeText(String query) {
		selectedClient = new ClientVO();
		List<ClientVO> clients = clientService.findAll();
		List<String> results = new ArrayList<String>();
		query.toLowerCase();
		for (ClientVO vos : clients) {
			if (vos.getName().toLowerCase().startsWith(query)) {
				results.add(vos.getName());
			}
		}

		return results;
	}

	// Properties!
	public void completeClientData() {
		if (offer == null) {
		} else {

			List<ClientVO> list = clientService.findAll();
			try {
				ClientVO c = clientService.findClientByName(offer);
				if (list.contains(c)) {
					setSelectedClient(c);
					helper = true;
					offer = "";
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							LocalizationsUtils.getText("user_info", context),
							LocalizationsUtils.getText("customer_selected",
									context) + " " + selectedClient.getName());
					context.addMessage(null, msg);
				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							LocalizationsUtils.getText("error", context),
							LocalizationsUtils.getText("customer_notexist",
									context) + " " + offer);
					context.addMessage(null, msg);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void registerClient() {
		if (selectedClient.getName().equals("")
				|| selectedClient.getUserName().equals("")
				|| selectedClient.getPhoneNumber().equals("")
				|| selectedClient.getEmailAddress().equals("")) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LocalizationsUtils.getText("error", context),
					LocalizationsUtils.getText("customer_required", context));
			context.addMessage(null, msg);
		} else {

			CharSequence defaultPassword = "welcome1";
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encPassword = bCryptPasswordEncoder.encode(defaultPassword);

			selectedClient.setPassword(encPassword);
			selectedClient.setRegistrationDate(new Date());

			clientService.saveClient(selectedClient);

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					LocalizationsUtils.getText("user_info", context),
					LocalizationsUtils.getText("customer_registered", context)
							+ " \n " + selectedClient.getName());
			context.addMessage(null, msg);

			checkBox = false;
			helper = true;

			try {
				ClientVO c = clientService.findClientByName(selectedClient
						.getName());
				selectedClient = c;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addMessage() {
		selectedClient = new ClientVO();
		String summary = checkBox ? "Checked" : "Unchecked";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(summary));
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public ClientVO getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(ClientVO selectedClient) {
		OfferAutoCompleteView.selectedClient = selectedClient;
	}

	public static ClientVO getStaticClient() {
		return selectedClient;
	}

	public static void setStaticSelectedClient(ClientVO client) {
		selectedClient = client;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public boolean isHelper() {
		return helper;
	}

	public void setHelper(boolean helper) {
		this.helper = helper;
	}
}