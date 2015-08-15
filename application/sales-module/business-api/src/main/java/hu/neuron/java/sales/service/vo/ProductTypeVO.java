package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class ProductTypeVO implements Serializable {

	private static final long serialVersionUID = 8890796510788499517L;

	private Long ID;

	private String name;

	private String productTypeId;
	
	public ProductTypeVO(){
		super();
		setProductTypeId(UUID.randomUUID().toString());
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
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
