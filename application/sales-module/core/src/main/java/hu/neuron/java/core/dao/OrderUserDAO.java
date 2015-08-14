package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.OrderUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderUserDAO extends JpaRepository<OrderUser, Long>{
	List<OrderUser> findOrderUserByOrderId(@Param("orderId") Long orderId) throws Exception;
	
	List<OrderUser> findOrderUserByUserId(@Param("userId") Long userId) throws Exception;
	
	@Query("select ou from OrderUser ou where ou.orderId=?1 and ou.userId=?2")
	OrderUser findOrderUserByOrderIdAndUserId(Long orderId, Long userId);
}
