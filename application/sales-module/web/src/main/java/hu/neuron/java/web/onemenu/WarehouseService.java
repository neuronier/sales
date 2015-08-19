package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.WarehouseServiceRemote;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "warehouseService")
@ApplicationScoped
public class WarehouseService {

	private List<String> warehouses;

	@EJB(mappedName = "WarehouseService", name = "WarehouseService")
	WarehouseServiceRemote whService;

	@PostConstruct
	public void init() {
		List<WarehouseVO> vos = whService.findAll();
		warehouses = new ArrayList<>(vos.size());
		for(WarehouseVO vo : vos){
			warehouses.add(vo.getWarehouseName());
		}
	}

	public List<String> getWarehouses() {
		
		return warehouses;
	}
}