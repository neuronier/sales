package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.Count;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface CountDAO extends JpaRepository<Count, Long>{
	
	Count findCountById(Long id);

}
