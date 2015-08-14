package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.SalesPoint;
import hu.neuron.java.sales.service.vo.AddressVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SalesPointConverter {

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public SalesPointVO toVO(SalesPoint entity) {
		if (entity == null) {
			return null;
		}
		SalesPointVO rv = new SalesPointVO();
		rv.setAddress(new AddressVO(entity.getSalePointAdress()));
		rv.setID(entity.getId());
		rv.setName(entity.getName());
		//TODO HIÁNYZIK AZ ENTITYBŐL A SALE POINT USEREK TÁROLÁSA
		rv.setWarehouse(new WarehouseVO(entity.getWareHouseId()));
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
			rv.setSalePointAdress(vo.getAddress().getSalePointAdress());
		}
		rv.setName(vo.getName());
		if(vo.getID() != null){
			rv.setSalePointId(vo.getID());
		}
		if(vo.getWarehouse() != null){
			rv.setWareHouseId(vo.getWarehouse().getWareHouseId());
		}
		rv.setId(vo.getID());;
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
