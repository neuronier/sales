package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientOfferVO implements Serializable{
	
	private static final long serialVersionUID = 8536677031967485872L;
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String clientOfferId;

	private ClientVO client;

	private OfferVO offer;
	
	private Date date;
	
	private Long quantity;
	
	private Long id;
	
	public ClientOfferVO(){}

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}
	
	public String getClientOfferId() {
		return clientOfferId;
	}

	public void setClientOfferId(String clientOfferId) {
		this.clientOfferId = clientOfferId;
	}

	public OfferVO getOffer() {
		return offer;
	}

	public void setOffer(OfferVO offer) {
		this.offer = offer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void createId(){
		if(date == null) {
			throw new NullPointerException("No Date was set for a new ClientOfferVO so no ID was generated for it");
		} else {
			String systime = format.format(date);
			setClientOfferId("PURCHASE_" + client.getName().toUpperCase() + "_" + systime);
		}
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
