package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OfferProductType")
public class OfferProductTypeEntity extends Base {

	private static final long serialVersionUID = 1L;

	private String offerId;

	private String productTypeId;

	private int quantity;

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OfferProductTypeEntity [offerId=" + offerId
				+ ", productTypeId=" + productTypeId + ", quantity=" + quantity
				+ "]";
	}

}
