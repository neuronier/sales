package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "OfferProductType")
public class OfferProductTypeEntity extends Base {

	private static final long serialVersionUID = 1L;

	/*@ManyToOne(fetch = FetchType.LAZY, targetEntity = OfferEntity.class)
	@JoinTable(name = "Offer")*/
	private Long offerId;
	 
	//TODO
	/* MISSING ANNOTATIONS !!! */
	private long productTypeId;
	
	private int quantity;

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(long productTypeId) {
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
