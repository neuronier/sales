package hu.neuron.java.sales.web.controllers.sell;

import java.io.Serializable;

public class OfferWebVO implements Serializable{
	
	private static final long serialVersionUID = -7157698861294039438L;

	private String offerId;

	private String offerName;
	
	private Long offerPrice;
	
	private Long quantity;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public Long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Long offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	

}
