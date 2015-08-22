package hu.neuron.java.sales.web.controllers.order;

import java.io.Serializable;

public class OrderProductType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private int quantity;

	public OrderProductType(String id, String name, int q) {
		super();
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
