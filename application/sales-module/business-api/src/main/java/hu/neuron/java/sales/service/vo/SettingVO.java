package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class SettingVO implements Serializable{

	private static final long serialVersionUID = -7118869186425124292L;

	private String key;

	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
