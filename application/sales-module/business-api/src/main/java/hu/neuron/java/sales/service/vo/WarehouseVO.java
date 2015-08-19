package hu.neuron.java.sales.service.vo;

import java.io.Serializable;

public class WarehouseVO implements Serializable{

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

}
