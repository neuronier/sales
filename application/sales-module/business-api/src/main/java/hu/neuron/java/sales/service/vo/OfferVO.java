package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.HashMap;

public class OfferVO implements Serializable {

	private static final long serialVersionUID = -3970831971179330872L;

	private Long ID;
	
	private long offerId;
	
	private long offerPrice;
	
	private String name;
	
	//private HashMap<ProductTypeVO, Integer> productTypes = new HashMap<ProductTypeVO, Integer>();

	public OfferVO() {
		
	}
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public HashMap<ProductTypeVO, Integer> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(HashMap<ProductTypeVO, Integer> productTypes) {
		this.productTypes = productTypes;
	}*/

	@Override
	public String toString() {
		return "OfferVO [offerId=" + offerId + ", offerPrice=" + offerPrice
				+ ", name=" + name + ", productTypes=" + /*productTypes +*/ "]";
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
} 
