package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "warehouseService")
@ApplicationScoped
public class WarehouseService {

	private List<WarehouseVO> warehouses;

	@EJB(mappedName = "WarehouseService", name = "WarehouseService")
	WarehouseServiceRemote whService;

	@PostConstruct
	public void init() {
		warehouses = whService.findAll();
	}

	public List<WarehouseVO> getWarehouses() {	
		return warehouses;
	}

	public void updateWarehouseList() {
		warehouses.clear();
		warehouses = whService.findAll();
	}
}