package hu.neuron.java.core.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ClientOffer")
public class ClientOffer extends Base {

	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	private String clientOfferId;

	private String clientId;

	private String offerId;
	
	private Date date;
	
	public ClientOffer(){}

	public String getClientOfferId() {
		return clientOfferId;
	}

	public void setClientOfferId(String clientOfferId) {
		this.clientOfferId = clientOfferId;
	}
	
	public void createId(){
		String systime = format.format(new Date(System.currentTimeMillis()));
		setClientOfferId("PURCHASE_" + systime);
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
}
