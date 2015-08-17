package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class OrderVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long Id;

	private String orderId;

	private String name;

	private String status;

	public OrderVO() {
		super();
		setOrderId(UUID.randomUUID().toString());
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
