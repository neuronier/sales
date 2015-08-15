package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.OrderProductType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface OrderProductTypeDAO extends JpaRepository<OrderProductType, Long> {

	List<OrderProductType> findOrderProductTypeByOrderId(@Param("orderId") String orderId) throws Exception;
	
	List<OrderProductType> findOrderProductTypeByProductTypeId(@Param("productTypeId") String productTypeId) throws Exception;
	
	@Query("select opt from OrderProductType opt where opt.orderId=?1 and opt.productTypeId=?2")
	OrderProductType findOrderProductTypeByOrderIdAndProductTypeId(String orderId, String productTypeId);
}
