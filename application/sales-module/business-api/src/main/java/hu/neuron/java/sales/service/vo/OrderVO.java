package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.List;

public class OrderVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public OrderVO(){
	}
	
	private Long identifier;
	
	private Long orderId;
	
	private String orderName;
	
	List<ProductTypeVO> productTypeVOs;
	
	List<UserVO> userVOs;
	
	List<OfferVO> offerVOs;

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public List<ProductTypeVO> getProductTypeVOs() {
		return productTypeVOs;
	}

	public void setProductTypeVOs(List<ProductTypeVO> productTypeVOs) {
		this.productTypeVOs = productTypeVOs;
	}

	public List<UserVO> getUserVOs() {
		return userVOs;
	}

	public void setUserVOs(List<UserVO> userVOs) {
		this.userVOs = userVOs;
	}

	public List<OfferVO> getOfferVOs() {
		return offerVOs;
	}

	public void setOfferVOs(List<OfferVO> offerVOs) {
		this.offerVOs = offerVOs;
	}
	
}
