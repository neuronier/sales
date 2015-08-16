package hu.neuron.java.sales.service;

import hu.neuron.java.sales.service.vo.SettingVO;

public interface SettingServiceRemote {

	public SettingVO findSettingByKey(String key);
	
}
