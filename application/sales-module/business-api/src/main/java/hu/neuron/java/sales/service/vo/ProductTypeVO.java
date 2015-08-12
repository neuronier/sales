package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class ProductTypeVO implements Serializable{
	
	private static final long serialVersionUID = 8890796510788499517L;

	private Long ID;
	
	private String name;
	
	private Long productTypeId;


	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}
}
