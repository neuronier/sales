package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//Ez az entitás a rendeléshez szükséges Order és ProductType (még nem létezik)!!! közti kapcsolatot valósítja meg (Roli)
@Entity
@Table(name="OrderProductType")
public class OrderProductType extends Base {

	private static final long serialVersionUID = 1L;
	
	/*@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	@JoinTable(name="order_producttype_sw")*/
	private Long orderId;
	
	//TODO
	/* MISSING ANNOTATIONS !!! */
	private Long productTypeId;
	
	private int quantity;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderProductType [orderId=" + orderId + ", productTypeId="
				+ productTypeId + ", quantity=" + quantity + "]";
	}
	
}