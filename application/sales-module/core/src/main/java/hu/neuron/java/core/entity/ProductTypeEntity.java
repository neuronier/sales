package hu.neuron.java.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ProductType")
public class ProductTypeEntity extends Base {

	private static final long serialVersionUID = -4534980851713861745L;

	private String name;

	private String productTypeId;

	public ProductTypeEntity() {
		Long systime = System.currentTimeMillis();
		setProductTypeId("PRODUCTTYPE_" + systime.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

}
