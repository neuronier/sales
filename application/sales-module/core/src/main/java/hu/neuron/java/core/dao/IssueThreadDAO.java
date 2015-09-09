package hu.neuron.java.core.dao;

import java.util.List;

import hu.neuron.java.core.entity.IssueThread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface IssueThreadDAO extends JpaRepository<IssueThread, Long>{
	
	IssueThread findByThreadId(@Param("threadId") String threadId);
	
	List<IssueThread> findByClientId(@Param("clientId") String clientId);
	
	Page<IssueThread> findByThreadIdStartsWith(String filter,Pageable pageable);
	
	Page<IssueThread> findByStatusStartsWith(String filter,Pageable pageable);
	
	Page<IssueThread> findByStatusIn(List<String> filter,Pageable pageable);
	
	Page<IssueThread> findBySubjectStartsWith(String filter,Pageable pageable);
	
	Page<IssueThread> findByClientIdIn(List<String> clientIdList,Pageable pageable);
	
	Long countByStatus(String status);
}
