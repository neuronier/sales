package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class ProductTypeVO implements Serializable {

	private static final long serialVersionUID = 8890796510788499517L;

	private Long ID;

	private String name;

	private String productTypeId;

	public ProductTypeVO() {
		Long systime = System.currentTimeMillis();
		setProductTypeId("PRODUCTTYPE_" + systime.toString());
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productTypeId == null) ? 0 : productTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductTypeVO other = (ProductTypeVO) obj;
		if (productTypeId == null) {
			if (other.productTypeId != null)
				return false;
		} else if (!productTypeId.equals(other.productTypeId))
			return false;
		return true;
	}

}
