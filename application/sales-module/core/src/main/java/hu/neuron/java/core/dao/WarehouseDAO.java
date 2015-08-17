package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Warehouse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface WarehouseDAO extends JpaRepository<Warehouse, Long>{
	
	Warehouse findWarehouseByWarehouseId(String warehouseId) throws Exception;
	
	Page<Warehouse> findByWarehouseNameStartsWith(String filter, Pageable pageRequest);
}
