package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderDAO extends JpaRepository<Order, Long> {

	Order findOrderByName(@Param("orderName") String name) throws Exception;
	
	Order findOrderByOrderId(@Param("id") Long id) throws Exception;

	Page<Order> findByNameStartsWith(String filter, Pageable pageable);

}
