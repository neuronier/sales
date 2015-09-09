package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class InventoryVO implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	String name;
	Integer q;

	public InventoryVO(String name, Integer q) {
		this.name = name;
		this.q = q;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQ() {
		return q;
	}

	public void setQ(Integer q) {
		this.q = q;
	}

	@Override
	public String toString() {
		return "Inventory [name=" + name + ", q=" + q + "]";
	}

}