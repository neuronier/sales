package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


//Mivel a diagramból és a dokumentációból nem derült ki rendesen, hogy milyen mezői legyenek az entitásnak lehet h ezt még BŐVITENI kell! (Roli)
@Entity
@Table(name="Orders")
public class Order extends Base {

	private static final long serialVersionUID = 1L;
	
	private Long orderId;
	
	private String name;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + name + "]";
	}
	
}
