package hu.neuron.java.sales.service.webservice.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Offer")
public class OfferWebServiceVO implements Serializable{

	private static final long serialVersionUID = -5855601548376937822L;
	
	private String offerId;

	private String name;
	
	private Long offerPrice;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Long offerPrice) {
		this.offerPrice = offerPrice;
	}
}
