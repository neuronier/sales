package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OfferOrder")
public class OfferOrderEntity extends Base {

	private static final long serialVersionUID = 1L;

	private String offerId;

	private String orderId;

	private int quantity;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
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
