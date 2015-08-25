package hu.neuron.java.sales.controller.issue;

import hu.neuron.java.sales.service.IssueMessageServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote.Status;
import hu.neuron.java.sales.service.vo.IssueMessageVO;
import hu.neuron.java.sales.service.vo.IssueThreadVO;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ViewScoped
@ManagedBean(name = "issueManagementController")
public class IssueManagementController {
	
	@EJB(name = "IssueThreadService", mappedName = "IssueThreadService")
	private IssueThreadServiceRemote issueThreadService;
	
	@EJB(name = "IssueMessageService", mappedName = "IssueMessageService")
	private IssueMessageServiceRemote issueMessageService;

	private LazyIssueThreadModel lazyIssueThreadModel;

	private IssueThreadVO selectedIssueThread;
	
	private List<IssueMessageVO> issueMessageList;
	
	private String comment;
	
	
	@PostConstruct
	public void init() {
		setLazyIssueThreadModel(new LazyIssueThreadModel(issueThreadService));
	}
	
	public void initIssueThreadViewerDialog(){
		if(selectedIssueThread.getStatus().equals(Status.NEW.toString())){
			selectedIssueThread.setStatus(Status.ONGOING.toString());
			selectedIssueThread.setLastUpdate(new Date());
			issueThreadService.saveIssueThread(selectedIssueThread);
		}
		issueMessageList = issueMessageService.findByThreadId(selectedIssueThread.getThreadId()); 
	}
	
	public void sendMessage(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		IssueMessageVO issueMessage = new IssueMessageVO();
		issueMessage.setText(comment);
		issueMessage.setThreadId(selectedIssueThread.getThreadId());
		issueMessage.setDate(new Date());
		issueMessage.setOwner(user.getUsername());
		issueMessageService.saveIssueMessage(issueMessage);
		
		IssueThreadVO thread = issueThreadService.findByThreadId(selectedIssueThread.getThreadId());
		thread.setLastUpdate(new Date());
		issueThreadService.saveIssueThread(thread);
		
		issueMessageList = issueMessageService.findByThreadId(selectedIssueThread.getThreadId());
	}
	
	public long getOngoingIssuesCount(){
		return issueThreadService.countOngoingIssueThread();
	}
	
	public LazyIssueThreadModel getLazyIssueThreadModel() {
		return lazyIssueThreadModel;
	}

	public void setLazyIssueThreadModel(LazyIssueThreadModel lazyIssueThreadModel) {
		this.lazyIssueThreadModel = lazyIssueThreadModel;
	}

	public IssueThreadVO getSelectedIssueThread() {
		return selectedIssueThread;
	}

	public void setSelectedIssueThread(IssueThreadVO selectedIssueThread) {
		this.selectedIssueThread = selectedIssueThread;
	}

	public List<IssueMessageVO> getIssueMessageList() {
		return issueMessageList;
	}

	public void setIssueMessageList(List<IssueMessageVO> issueMessageList) {
		this.issueMessageList = issueMessageList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
