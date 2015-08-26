package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.Date;

public class OrderVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long Id;

	private String orderId;

	private String name;

	private String status;

	private Date date;

	private ProductTypeVO productType;
	
	private WarehouseVO warehouse;

	private String quantity;

	public OrderVO() {
		super();
		Long systime = System.currentTimeMillis();
		setOrderId("ORDER_" + systime.toString());
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public ProductTypeVO getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeVO productType) {
		this.productType = productType;
	}

	public WarehouseVO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseVO warehouse) {
		this.warehouse = warehouse;
	}
}
