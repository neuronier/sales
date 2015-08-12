package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


//Mivel a diagramból és a dokumentációból nem derült ki rendesen, hogy milyen mezői legyenek az entitásnak lehet h ezt még BŐVITENI kell! (Roli)
@Entity
@Table(name="Orders")
public class Order extends Base {

	private static final long serialVersionUID = 1L;
	
	private Long orderId;
	
	private String orderName;

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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + "]";
	}
	
}
