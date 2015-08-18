package hu.neuron.java.sales.service.webservice.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Order")
public class OrderWebServiceVO implements Serializable{

	private static final long serialVersionUID = -1171095541780625318L;

	private String orderId;

	private String name;

	private String status;

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
