package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.dao.AddressDAO;
import hu.neuron.java.core.dao.WarehouseDAO;
import hu.neuron.java.core.entity.SalesPoint;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesPointConverter {
	
	
	@Autowired
	WarehouseDAO warehouseDao;
	
	@Autowired
	WarehouseConverter whConverter;
	
	@Autowired
	AddressDAO addressDao;
	
	@Autowired
	AddressConverter adConverter;

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public SalesPointVO toVO(SalesPoint entity) {
		if (entity == null) {
			return null;
		}
		SalesPointVO rv = new SalesPointVO();
		try {
			rv.setAddress(adConverter.toVO(addressDao.findAddressByAddressId(entity.getAdressId())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rv.setSalePointPhoneNumber(entity.getSalePointPhoneNumber());
		rv.setId(entity.getId());
		rv.setSalePointId(entity.getSalePointId());
		rv.setName(entity.getName());
		//TODO HIÁNYZIK AZ ENTITYBŐL A SALE POINT USEREK TÁROLÁSA
		try {
			rv.setWarehouse(whConverter.toVO(warehouseDao.findWarehouseByWarehouseId(entity.getWareHouseId())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public SalesPoint toEntity(SalesPointVO vo) {
		if (vo == null) {
			return null;
		}
		SalesPoint rv = new SalesPoint();
		if(vo.getAddress()!=null){
			rv.setAdressId(vo.getAddress().getAddressId());
		}
		rv.setName(vo.getName());
		rv.setSalePointId(vo.getSalePointId());
		rv.setSalePointPhoneNumber(vo.getSalePointPhoneNumber());
		if(vo.getWarehouse() != null){
			rv.setWareHouseId(vo.getWarehouse().getWareHouseId());
		}
		rv.setId(vo.getId());;
		return rv;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public List<SalesPointVO> toVO(List<SalesPoint> enitites) {
		if (enitites == null) {
			return null;
		}
		List<SalesPointVO> vos = new ArrayList<SalesPointVO>();
		for (SalesPoint dto : enitites) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public List<SalesPoint> toEntity(List<SalesPointVO> vos) {
		if (vos == null) {
			return null;
		}
		List<SalesPoint> enitites = new ArrayList<SalesPoint>();
		for (SalesPointVO vo : vos) {
			enitites.add(toEntity(vo));
		}
		return enitites;
	}

}
