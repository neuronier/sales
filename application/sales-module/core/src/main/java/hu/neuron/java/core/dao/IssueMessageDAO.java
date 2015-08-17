package hu.neuron.java.core.dao;

import hu.neuron.java.core.entity.IssueMessage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface IssueMessageDAO extends JpaRepository<IssueMessage, Long>{
	
	List<IssueMessage> findByThreadId(@Param("threadId") String threadId);
	
	IssueMessage findByMessageId(@Param("messageId") String messageId);
	
	IssueMessage findFirst1ByThreadId(String threadId, Sort sort);
}
