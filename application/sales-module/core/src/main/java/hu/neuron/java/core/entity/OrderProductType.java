package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

//Ez az entitás a rendeléshez szükséges Order és ProductType (még nem létezik)!!! közti kapcsolatot valósítja meg (Roli)
//Mostmár létezik és csináltam ehhez is DAO-t. (Jocó)
@Entity
@Table(name = "OrderProductType")
public class OrderProductType extends Base {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String productTypeId;

	private int quantity;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
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