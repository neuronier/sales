package hu.neuron.java.web.autocomplete;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="customerAutoCompleteView")
public class CustomerAutoCompleteView {

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;

	private String customerName;
	
	private static ClientVO selectedClient;

	public CustomerAutoCompleteView() {
		super();
	}

	public List<String> completeText(String query) {
		selectedClient = new ClientVO();
		List<ClientVO> clients = clientService.findByNameStartsWith(query.toLowerCase());
		List<String> results = new ArrayList<String>();
		if(clients != null){
			for (ClientVO cvo : clients) {
				results.add(cvo.getName());
			}
		}
		return results;
	}

	public void completeClientData() {
		ClientVO client = null;
		if(customerName != null){
			try {
				client = clientService.findClientByName(customerName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		setSelectedClient(client);
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ClientVO getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(ClientVO selectedClient) {
		CustomerAutoCompleteView.selectedClient = selectedClient;
	}

}