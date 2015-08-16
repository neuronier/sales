package hu.neuron.java.sales.service.webservice.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UserList")
public class UserListWebServiceVO {

	@XmlElement(name = "users")
	private List<UserWebServiceVO> list;

	public List<UserWebServiceVO> getList() {
		return list;
	}

	public void setList(List<UserWebServiceVO> list) {
		this.list = list;
	}
	
}
