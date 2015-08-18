package hu.neuron.java.sales.service.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "OrderList")
public class OrderListWebServiceVO {

	@XmlElement(name = "orders")
	private List<OrderWebServiceVO> list;

	public List<OrderWebServiceVO> getList() {
		return list;
	}

	public void setList(List<OrderWebServiceVO> list) {
		this.list = list;
	}
	
}
