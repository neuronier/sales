package hu.neuron.java.sales.service.converter;

import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("ProductTypeConverter")
public class ProductTypeConverter {
	
	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public ProductTypeVO toVO(ProductTypeEntity entity) {
		if (entity == null) {
			return null;
		}
		ProductTypeVO rv = new ProductTypeVO();
		
		rv.setID(entity.getId());
		rv.setProductTypeId(entity.getProductTypeId());
		rv.setName(entity.getName());
		
		return rv;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public ProductTypeEntity toEntity(ProductTypeVO vo) {
		if (vo == null) {
			return null;
		}
		ProductTypeEntity rv = new ProductTypeEntity();
		
		rv.setId(vo.getID());
		rv.setName(vo.getName());
		rv.setProductTypeId(vo.getProductTypeId());
		
		return rv;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public List<ProductTypeVO> toVO(List<ProductTypeEntity> enitites) {
		if (enitites == null) {
			return null;
		}
		List<ProductTypeVO> vos = new ArrayList<ProductTypeVO>();
		for (ProductTypeEntity dto : enitites) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	/**
	 * ÁTMENETI CONVERTER- AZ ENTITÁSOK NORMÁLIS KIALAKÍTÁSA UTÁN MÉG ÁT KELL ÍRNI!!!!
	 * */
	public List<ProductTypeEntity> toEntity(List<ProductTypeVO> vos) {
		if (vos == null) {
			return null;
		}
		List<ProductTypeEntity> enitites = new ArrayList<ProductTypeEntity>();
		for (ProductTypeVO vo : vos) {
			enitites.add(toEntity(vo));
		}
		return enitites;
	}

}
