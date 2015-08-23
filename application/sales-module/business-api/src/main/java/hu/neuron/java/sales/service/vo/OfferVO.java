package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class OfferVO implements Serializable {

	private static final long serialVersionUID = -3970831971179330872L;

	private Long ID;

	private String offerId;

	private String name;
	
	private Long offerPrice;

	public OfferVO() {
		Long systime = System.currentTimeMillis();
		setOfferId("OFFER_" + systime.toString());
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

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public HashMap<ProductTypeVO, Integer> getProductTypes() { return
	 * productTypes; }
	 * 
	 * public void setProductTypes(HashMap<ProductTypeVO, Integer> productTypes)
	 * { this.productTypes = productTypes; }
	 */

	@Override
	public String toString() {
		return "OfferVO [offerId=" + getOfferId() + ", offerPrice="
				+ offerPrice + ", name=" + name + ", productTypes=" + /*
																	 * productTypes
																	 * +
																	 */"]";
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
}
