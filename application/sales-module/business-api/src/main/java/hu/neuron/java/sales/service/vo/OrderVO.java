package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class OrderVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long Id;

	private String orderId;

	private String name;

	private String status;

	private ClientVO client;

	private OfferVO offers;

	private ProductTypeVO productType;
	
	private String oQuantity;
	
	private String pQuantity;

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

	public ClientVO getClient() {
		return client;
	}

	public void setClient(ClientVO client) {
		this.client = client;
	}

	public OfferVO getOffers() {
		return offers;
	}

	public void setOffers(OfferVO offers) {
		this.offers = offers;
	}

	public ProductTypeVO getProductType() {
		return productType;
	}

	public void setProductType(ProductTypeVO productType) {
		this.productType = productType;
	}

	public String getoQuantity() {
		return oQuantity;
	}

	public void setoQuantity(String oQuantity) {
		this.oQuantity = oQuantity;
	}

	public String getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(String pQuantity) {
		this.pQuantity = pQuantity;
	}
}
