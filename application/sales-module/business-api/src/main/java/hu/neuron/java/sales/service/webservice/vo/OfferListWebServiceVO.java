package hu.neuron.java.sales.service.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OfferList")
public class OfferListWebServiceVO {

	@XmlElement(name = "offers")
	private List<OfferWebServiceVO> list;

	public List<OfferWebServiceVO> getList() {
		return list;
	}

	public void setList(List<OfferWebServiceVO> list) {
		this.list = list;
	}
	
}
