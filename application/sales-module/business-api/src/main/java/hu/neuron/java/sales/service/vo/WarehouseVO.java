package hu.neuron.java.sales.service.vo;

import java.io.Serializable;
import java.util.UUID;

public class WarehouseVO implements Serializable, Comparable<WarehouseVO>{

	private static final long serialVersionUID = -2286310106108872003L;
	
	private String warehouseId;
	
	private String warehouseName;
	
	private Long id;
	
	public WarehouseVO() {}
	
	public WarehouseVO(String wareHouseId) {	
		this.warehouseId = wareHouseId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	public void generateWarehouseId(){
		this.warehouseId = UUID.randomUUID().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((warehouseId == null) ? 0 : warehouseId.hashCode());
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
		WarehouseVO other = (WarehouseVO) obj;
		if (warehouseId == null) {
			if (other.warehouseId != null)
				return false;
		} else if (!warehouseId.equals(other.warehouseId))
			return false;
		return true;
	}


	@Override
	public int compareTo(WarehouseVO o) {
		
		return warehouseName.compareTo(o.warehouseName);
	}

	
}
