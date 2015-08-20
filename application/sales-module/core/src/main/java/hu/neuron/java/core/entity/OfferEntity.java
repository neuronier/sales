package hu.neuron.java.core.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Offer")
public class OfferEntity extends Base {

	private static final long serialVersionUID = -433947333801752871L;

	private String offerId;

	private String name;
	
	private Long offerPrice;

	public OfferEntity(){
//		setOfferId(UUID.randomUUID().toString());
	}

	public long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(long offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String offerName) {
		this.name = offerName;
	}

	@Override
	public String toString() {
		return "OfferEntity [offerId=" + getOfferId() + ", offerPrice="
				+ offerPrice + ", offerName=" + name + "]";
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

}
