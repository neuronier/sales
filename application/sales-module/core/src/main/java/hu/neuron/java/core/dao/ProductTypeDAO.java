package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.ProductTypeEntity;
import hu.neuron.java.core.entity.SalesPoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ProductTypeDAO extends JpaRepository<ProductTypeEntity, Long>{
	
	ProductTypeEntity findProductTypeEntityByName(String name) throws Exception;
	
	ProductTypeEntity findProductTypeEntityByProductTypeId(Long productTypeId) throws Exception;

	Page<ProductTypeEntity> findByNameStartsWith(String filter, PageRequest pageRequest);
	
}

