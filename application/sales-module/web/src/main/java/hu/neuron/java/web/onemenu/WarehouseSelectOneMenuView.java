package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
 
@ViewScoped
@ManagedBean(name = "warehouseSelectOneMenuView")
public class WarehouseSelectOneMenuView {
     
    private WarehouseVO warehouse;   
    private List<WarehouseVO> warehouses;
     
    @ManagedProperty("#{warehouseService}")
    private WarehouseService service;
     
    @PostConstruct
    public void init() {
        warehouses = service.getWarehouses();
    }
     
    public List<WarehouseVO> getWarehouses() {
        return warehouses;
    }
     
    public void setService(WarehouseService service) {
        this.service = service;
    }    
    

	public void updateWarehouseList() {
		service.updateWarehouseList();
		warehouses = service.getWarehouses();
		
	}

	public WarehouseVO getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseVO warehouse) {
		this.warehouse = warehouse;
	}
}