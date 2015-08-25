package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "userListService")
@ApplicationScoped
public class UserListService {

	private List<UserVO> userList;

	@EJB(mappedName = "UserService", name = "UserService")
	UserServiceRemote userService;

	@PostConstruct
	public void init() {
		updateUserList();
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void updateUserList() {
		try {
			userList = userService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}