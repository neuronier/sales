package hu.neuron.java.web.autocomplete;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class OfferAutoCompleteView {

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;

	private String offer;

	private ClientVO selectedClient;

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

	public void completeClientData() {
		System.out.println("kiválasztott client" + offer);
		try {
			setSelectedClient(clientService.findClientByName(offer));
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		this.selectedClient = selectedClient;
	}
}