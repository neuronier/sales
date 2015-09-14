package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class OfferProductTypeVO implements Serializable {

	private static final long serialVersionUID = 8594623139796912889L;

	private String offerId;

	private String productTypeId;

	private int quantity;
	
	private Long id;
	
	public OfferProductTypeVO(){}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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


	@Override
	public String toString() {
		return "OfferProductTypeVO [quantity=" + quantity
				+ "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
}
