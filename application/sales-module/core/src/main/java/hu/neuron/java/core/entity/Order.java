package hu.neuron.java.core.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

//Mivel a diagramból és a dokumentációból nem derült ki rendesen, hogy milyen mezői legyenek az entitásnak lehet h ezt még BŐVITENI kell! (Roli)
@Entity
@Table(name = "Orders")
public class Order extends Base {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String name;

	private String status;
	
	public Order(){
		super();
		setOrderId(UUID.randomUUID().toString());
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

	public void setOrderName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
