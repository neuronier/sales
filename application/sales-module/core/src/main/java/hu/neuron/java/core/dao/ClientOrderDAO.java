package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ClientOrderDAO extends JpaRepository<ClientOrder, Long>{
	
	List<ClientOrder> findClientOrderByClientId(Long clientId)throws Exception;

	List<ClientOrder> findClientOrderByOrderId(Long orderId) throws Exception;
							
	@Query("select co from ClientOrder co where co.clientId=?1 and co.orderId=?2")
	ClientOrder findClientOrderByClientIdAndOrderId(Long clientId, Long orderId);
	
}
