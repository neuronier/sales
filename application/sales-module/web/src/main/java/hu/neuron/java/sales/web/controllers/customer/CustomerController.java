package hu.neuron.java.sales.web.controllers.customer;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "customerController")
public class CustomerController implements Serializable {
	private static final long serialVersionUID = 1L;

	private LazyCustomerModel lazyCustomerModel;

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;

	private ClientVO selectedClient;

	@PostConstruct
	public void init() {
		setLazyCustomerModel(new LazyCustomerModel(clientService));
	}

	public void onRowSelected(SelectEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("customer_selected", context) + " "
						+ selectedClient.getName());
		context.addMessage(null, msg);
	}
	
	public void removeCustomer(){
		clientService.removeClient(selectedClient.getClientId());
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("customer_deleted", context)
						+ " \n " + selectedClient.getName());
		context.addMessage(null, msg);
		selectedClient = null;
	}

	public CustomerController() {
		super();
	}

	public ClientVO getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(ClientVO selectedClient) {
		this.selectedClient = selectedClient;
	}

	public LazyCustomerModel getLazyCustomerModel() {
		return lazyCustomerModel;
	}

	public void setLazyCustomerModel(LazyCustomerModel lazyCustomerModel) {
		this.lazyCustomerModel = lazyCustomerModel;
	}
}
