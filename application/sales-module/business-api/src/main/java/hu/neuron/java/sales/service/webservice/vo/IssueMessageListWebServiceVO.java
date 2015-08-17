package hu.neuron.java.sales.service.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "IssueMessageList")
public class IssueMessageListWebServiceVO {

	@XmlElement(name = "IssueMessage")
	private List<IssueMessageWebServiceVO> list;

	public List<IssueMessageWebServiceVO> getList() {
		return list;
	}

	public void setList(List<IssueMessageWebServiceVO> list) {
		this.list = list;
	}	
}
