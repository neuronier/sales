package hu.neuron.java.sales.web.controllers.offer;

import java.io.Serializable;

public class OfferProductType implements Serializable {

	private static final long serialVersionUID = 2814196519414942896L;

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

	public OfferProductType(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
}
