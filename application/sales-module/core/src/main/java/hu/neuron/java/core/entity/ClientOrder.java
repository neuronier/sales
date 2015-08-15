package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ClientOrder")
public class ClientOrder extends Base {

	private static final long serialVersionUID = 1503106905028296942L;
	
	private String clientId;
	
	private String orderId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
}
