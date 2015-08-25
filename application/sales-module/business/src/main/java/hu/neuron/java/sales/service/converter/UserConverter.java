package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.dao.SalesPointDAO;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.sales.service.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
	
	private static final Logger logger = Logger.getLogger(UserConverter.class);

	@Autowired
	SalesPointDAO spDao;
	
	@Autowired
	SalesPointConverter spConv;

	@PostConstruct
	void init() {

		logger.debug("UserConverter init");
	}

	public UserVO toVO(User dto) {
		if (dto == null) {
			return null;
		}
		UserVO uvo = new UserVO();
		uvo.setEmail(dto.getEmail());
		uvo.setId(dto.getId());
		uvo.setName(dto.getName());
		uvo.setPassword(dto.getPassword());
		uvo.setPhoneNumber(dto.getPhoneNumber());
		uvo.setUserId(dto.getUserId());
		uvo.setUserName(dto.getUserName());
		try {
			uvo.setSalesPoint(spConv.toVO(spDao.findSalesPointBySalePointId(dto.getSalePointId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return uvo;
	}

	public User toEntity(UserVO vo) {
		if (vo == null) {
			return null;
		}
		User ue = new User();
		ue.setEmail(vo.getEmail());
		ue.setId(vo.getId());
		ue.setName(vo.getName());
		ue.setPassword(vo.getPassword());
		ue.setPhoneNumber(vo.getPhoneNumber());
		ue.setUserId(vo.getUserId());
		ue.setUserName(vo.getUserName());
		if(vo.getSalesPoint()!= null){
			ue.setSalePointId(vo.getSalesPoint().getSalePointId());
		}
		return ue;
	}

	public List<UserVO> toVO(List<User> dtos) {
		if (dtos == null) {
			return null;
		}
		List<UserVO> vos = new ArrayList<UserVO>();
		for (User dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public List<User> toEntity(List<UserVO> vos) {
		if (vos == null) {
			return null;
		}
		List<User> dtos = new ArrayList<User>();
		for (UserVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
