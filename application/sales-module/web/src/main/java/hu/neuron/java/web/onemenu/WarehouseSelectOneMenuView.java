package hu.neuron.java.web.onemenu;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
 
@ManagedBean
public class WarehouseSelectOneMenuView {
     
    private static String warehouseName;   
    private static List<String> warehouses;
     
    @ManagedProperty("#{warehouseService}")
    private WarehouseService service;
    
    private static WarehouseService staticService;
     
    @PostConstruct
    public void init() {
        warehouses = service.getWarehouses();
        staticService = service;
    }

    public String getWarehouseName() {
        return warehouseName;
    }
 
    public void setWarehouseName(String name) {
        WarehouseSelectOneMenuView.warehouseName = name;
    }
     
    public List<String> getWarehouses() {
        return warehouses;
    }
     
    public void setService(WarehouseService service) {
        this.service = service;
    }    
    
    public static String getStaticWarehouseName() {
        return warehouseName;
    }
    
    public static void setStaticWarehouseName(String warehoseName) {
    	WarehouseSelectOneMenuView.warehouseName = warehoseName;
    }

	public static void updateWarehouseList() {
		staticService.updateWarehouseList();
		warehouses = staticService.getWarehouses();
		
	}
}