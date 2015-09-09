package hu.neuron.java.sales.web.controllers.issue;

import hu.neuron.java.sales.service.IssueMessageServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote;
import hu.neuron.java.sales.service.IssueThreadServiceRemote.Status;
import hu.neuron.java.sales.service.vo.IssueMessageVO;
import hu.neuron.java.sales.service.vo.IssueThreadVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	private HashMap<String,String> statusList;
	
	
	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		statusList = new LinkedHashMap<String,String>();
		statusList.put(LocalizationsUtils.getText("issue_status_new", context), "New");
		statusList.put(LocalizationsUtils.getText("issue_status_ongoing", context), "Ongoing");
		statusList.put(LocalizationsUtils.getText("issue_status_resolved", context), "Resolved");
		
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
	
	public String getLocalizedStatus(String status){
		FacesContext context = FacesContext.getCurrentInstance();
		return LocalizationsUtils.getText("issue_status_"+status.toLowerCase(), context);
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

	public HashMap<String, String> getStatusList() {
		return statusList;
	}

	public void setStatusList(HashMap<String, String> statusList) {
		this.statusList = statusList;
	}

	
}
