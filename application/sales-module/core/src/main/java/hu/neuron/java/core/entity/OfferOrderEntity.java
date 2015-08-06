package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OfferOrder")
public class OfferOrderEntity extends Base {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = OfferEntity.class)
	@JoinTable(name = "Offer")
	private long offerId;
	
	/* MISSING ANNOTATIONS !!! */
	private long orderId;
	
	private int quantity;

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OfferOrderEntity [offerId=" + offerId + ", orderId=" + orderId
				+ ", quantity=" + quantity + "]";
	}
	
	
}
