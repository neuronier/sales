package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Offer")
public class OfferEntity extends Base {

	private static final long serialVersionUID = 1L;

	private long offerId;
	
	private long offerPrice;
	
	private String offerName;

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(long offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	@Override
	public String toString() {
		return "OfferEntity [offerId=" + offerId + ", offerPrice=" + offerPrice
				+ ", offerName=" + offerName + "]";
	}

}
