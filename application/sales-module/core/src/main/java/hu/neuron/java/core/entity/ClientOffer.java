package hu.neuron.java.core.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ClientOffer")
public class ClientOffer extends Base {

	private static final long serialVersionUID = 1L;
	
	private String clientOfferId;

	private String clientId;

	private String offerId;
	
	private Long quantity;
	
	private Date date;
	
	private String salesPointId;
	
	public ClientOffer(){}

	public String getClientOfferId() {
		return clientOfferId;
	}

	public void setClientOfferId(String clientOfferId) {
		this.clientOfferId = clientOfferId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getSalesPointId() {
		return salesPointId;
	}

	public void setSalesPointId(String salesPointId) {
		this.salesPointId = salesPointId;
	}
	
}
