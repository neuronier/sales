package hu.neuron.java.sales.service.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IssueThreadList")
public class IssueThreadListWebServiceVO {
	
	@XmlElement(name = "IssueThread")
	private List<IssueThreadWebServiceVO> list;

	public List<IssueThreadWebServiceVO> getList() {
		return list;
	}

	public void setList(List<IssueThreadWebServiceVO> list) {
		this.list = list;
	}

}
