package hu.neuron.java.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


//Ez az entitás a rendeléshez szükséges Order és User közti kapcsolatot valósítja meg (Roli)
@Entity
@Table(name="OrderUser")
public class OrderUser extends Base {

	private static final long serialVersionUID = 1L;
	
	/*@ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
	@JoinTable(name="order_user_sw")*/
	private Long orderId;
	
	/*@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinTable(name="order_user_sw")*/
	private Long userId;
	
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean complete;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean getComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@Override
	public String toString() {
		return "OrderUser [orderId=" + orderId + ", userId=" + userId
				+ ", complete=" + complete + "]";
	}
}