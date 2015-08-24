package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class OfferProductTypeVO implements Serializable {

	private static final long serialVersionUID = 8594623139796912889L;

	private String name;

	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OfferProductTypeVO(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OfferProductTypeVO [name=" + name + ", quantity=" + quantity
				+ "]";
	}
}
