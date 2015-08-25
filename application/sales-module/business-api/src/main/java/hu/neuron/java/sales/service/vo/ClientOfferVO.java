package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Date;

public class ClientOfferVO implements Serializable{
	
	private static final long serialVersionUID = 8536677031967485872L;

	private String purchaseId;

	private ClientVO client;

	private String offerId;
	
	private Date purchaseDate;

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
}
