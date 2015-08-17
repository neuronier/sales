package hu.neuron.java.sales.service.webservice;

import hu.neuron.java.sales.service.webservice.vo.IssueMessageListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.IssueThreadListWebServiceVO;
import hu.neuron.java.sales.service.webservice.vo.IssueThreadWebServiceVO;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://hu.neuron")
public interface IssueMessageWebService {
	

	@WebMethod(operationName = "issueThreadListByClient")
	@WebResult(name = "issueThreadListByClient")
	public IssueThreadListWebServiceVO getIssueThreadListByClientWebMethod(String clientId);
	
	@WebMethod(operationName = "createNewIssueThread")
	@WebResult(name = "createNewIssueThread")
	public IssueThreadWebServiceVO createNewIssueThreadWebMethod(String clientId, String subject);
	
	@WebMethod(operationName = "issueMessageListByThreadId")
	@WebResult(name = "issueMessageListByThreadId")
	public IssueMessageListWebServiceVO getIssueMessageListByThreadIdWebMethod(String threadId);
	
	@WebMethod(operationName = "createIssueMessageInThread")
	@WebResult(name = "createIssueMessageInThread")
	public void createIssueMessageInThreadWebMethod(String threadId, String messageText, String owner);
	
	@WebMethod(operationName = "modifyIssueThreadStatus")
	@WebResult(name = "modifyIssueThreadStatus")
	public void modifyIssueThreadStatusWebMethod(String threadId, String status) throws Exception;

}
