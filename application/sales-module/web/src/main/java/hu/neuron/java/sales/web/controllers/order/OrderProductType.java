package hu.neuron.java.sales.web.controllers.order;

import java.io.Serializable;

public class OrderProductType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int quantity;

	public OrderProductType(String name, int q) {
		super();
		this.name = name;
		this.quantity = q;
	}
	
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

	@Override
	public String toString() {
		return "[name=" + name + ", quantity=" + quantity + "]";
	}
}
