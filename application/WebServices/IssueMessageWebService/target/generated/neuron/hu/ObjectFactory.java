
package neuron.hu;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the neuron.hu package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IssueThreadListByClientResponse_QNAME = new QName("http://hu.neuron", "issueThreadListByClientResponse");
    private final static QName _Exception_QNAME = new QName("http://hu.neuron", "Exception");
    private final static QName _ModifyIssueThreadStatus_QNAME = new QName("http://hu.neuron", "modifyIssueThreadStatus");
    private final static QName _IssueMessageListByThreadId_QNAME = new QName("http://hu.neuron", "issueMessageListByThreadId");
    private final static QName _CreateNewIssueThread_QNAME = new QName("http://hu.neuron", "createNewIssueThread");
    private final static QName _IssueMessageVO_QNAME = new QName("http://hu.neuron", "IssueMessageVO");
    private final static QName _ModifyIssueThreadStatusResponse_QNAME = new QName("http://hu.neuron", "modifyIssueThreadStatusResponse");
    private final static QName _IssueThreadListByClient_QNAME = new QName("http://hu.neuron", "issueThreadListByClient");
    private final static QName _IssueMessageList_QNAME = new QName("http://hu.neuron", "IssueMessageList");
    private final static QName _CreateIssueMessageInThread_QNAME = new QName("http://hu.neuron", "createIssueMessageInThread");
    private final static QName _CreateIssueMessageInThreadResponse_QNAME = new QName("http://hu.neuron", "createIssueMessageInThreadResponse");
    private final static QName _IssueThreadList_QNAME = new QName("http://hu.neuron", "IssueThreadList");
    private final static QName _CreateNewIssueThreadResponse_QNAME = new QName("http://hu.neuron", "createNewIssueThreadResponse");
    private final static QName _IssueThreadVO_QNAME = new QName("http://hu.neuron", "IssueThreadVO");
    private final static QName _IssueMessageListByThreadIdResponse_QNAME = new QName("http://hu.neuron", "issueMessageListByThreadIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: neuron.hu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IssueThreadListByClientResponse }
     * 
     */
    public IssueThreadListByClientResponse createIssueThreadListByClientResponse() {
        return new IssueThreadListByClientResponse();
    }

    /**
     * Create an instance of {@link CreateNewIssueThread }
     * 
     */
    public CreateNewIssueThread createCreateNewIssueThread() {
        return new CreateNewIssueThread();
    }

    /**
     * Create an instance of {@link IssueMessageListByThreadIdResponse }
     * 
     */
    public IssueMessageListByThreadIdResponse createIssueMessageListByThreadIdResponse() {
        return new IssueMessageListByThreadIdResponse();
    }

    /**
     * Create an instance of {@link IssueMessageListWebServiceVO }
     * 
     */
    public IssueMessageListWebServiceVO createIssueMessageListWebServiceVO() {
        return new IssueMessageListWebServiceVO();
    }

    /**
     * Create an instance of {@link ModifyIssueThreadStatusResponse }
     * 
     */
    public ModifyIssueThreadStatusResponse createModifyIssueThreadStatusResponse() {
        return new ModifyIssueThreadStatusResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CreateIssueMessageInThread }
     * 
     */
    public CreateIssueMessageInThread createCreateIssueMessageInThread() {
        return new CreateIssueMessageInThread();
    }

    /**
     * Create an instance of {@link ModifyIssueThreadStatus }
     * 
     */
    public ModifyIssueThreadStatus createModifyIssueThreadStatus() {
        return new ModifyIssueThreadStatus();
    }

    /**
     * Create an instance of {@link IssueThreadListByClient }
     * 
     */
    public IssueThreadListByClient createIssueThreadListByClient() {
        return new IssueThreadListByClient();
    }

    /**
     * Create an instance of {@link IssueMessageWebServiceVO }
     * 
     */
    public IssueMessageWebServiceVO createIssueMessageWebServiceVO() {
        return new IssueMessageWebServiceVO();
    }

    /**
     * Create an instance of {@link IssueThreadWebServiceVO }
     * 
     */
    public IssueThreadWebServiceVO createIssueThreadWebServiceVO() {
        return new IssueThreadWebServiceVO();
    }

    /**
     * Create an instance of {@link CreateNewIssueThreadResponse }
     * 
     */
    public CreateNewIssueThreadResponse createCreateNewIssueThreadResponse() {
        return new CreateNewIssueThreadResponse();
    }

    /**
     * Create an instance of {@link CreateIssueMessageInThreadResponse }
     * 
     */
    public CreateIssueMessageInThreadResponse createCreateIssueMessageInThreadResponse() {
        return new CreateIssueMessageInThreadResponse();
    }

    /**
     * Create an instance of {@link IssueThreadListWebServiceVO }
     * 
     */
    public IssueThreadListWebServiceVO createIssueThreadListWebServiceVO() {
        return new IssueThreadListWebServiceVO();
    }

    /**
     * Create an instance of {@link IssueMessageListByThreadId }
     * 
     */
    public IssueMessageListByThreadId createIssueMessageListByThreadId() {
        return new IssueMessageListByThreadId();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueThreadListByClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "issueThreadListByClientResponse")
    public JAXBElement<IssueThreadListByClientResponse> createIssueThreadListByClientResponse(IssueThreadListByClientResponse value) {
        return new JAXBElement<IssueThreadListByClientResponse>(_IssueThreadListByClientResponse_QNAME, IssueThreadListByClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyIssueThreadStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyIssueThreadStatus")
    public JAXBElement<ModifyIssueThreadStatus> createModifyIssueThreadStatus(ModifyIssueThreadStatus value) {
        return new JAXBElement<ModifyIssueThreadStatus>(_ModifyIssueThreadStatus_QNAME, ModifyIssueThreadStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueMessageListByThreadId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "issueMessageListByThreadId")
    public JAXBElement<IssueMessageListByThreadId> createIssueMessageListByThreadId(IssueMessageListByThreadId value) {
        return new JAXBElement<IssueMessageListByThreadId>(_IssueMessageListByThreadId_QNAME, IssueMessageListByThreadId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewIssueThread }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createNewIssueThread")
    public JAXBElement<CreateNewIssueThread> createCreateNewIssueThread(CreateNewIssueThread value) {
        return new JAXBElement<CreateNewIssueThread>(_CreateNewIssueThread_QNAME, CreateNewIssueThread.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueMessageWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "IssueMessageVO")
    public JAXBElement<IssueMessageWebServiceVO> createIssueMessageVO(IssueMessageWebServiceVO value) {
        return new JAXBElement<IssueMessageWebServiceVO>(_IssueMessageVO_QNAME, IssueMessageWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyIssueThreadStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyIssueThreadStatusResponse")
    public JAXBElement<ModifyIssueThreadStatusResponse> createModifyIssueThreadStatusResponse(ModifyIssueThreadStatusResponse value) {
        return new JAXBElement<ModifyIssueThreadStatusResponse>(_ModifyIssueThreadStatusResponse_QNAME, ModifyIssueThreadStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueThreadListByClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "issueThreadListByClient")
    public JAXBElement<IssueThreadListByClient> createIssueThreadListByClient(IssueThreadListByClient value) {
        return new JAXBElement<IssueThreadListByClient>(_IssueThreadListByClient_QNAME, IssueThreadListByClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueMessageListWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "IssueMessageList")
    public JAXBElement<IssueMessageListWebServiceVO> createIssueMessageList(IssueMessageListWebServiceVO value) {
        return new JAXBElement<IssueMessageListWebServiceVO>(_IssueMessageList_QNAME, IssueMessageListWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIssueMessageInThread }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createIssueMessageInThread")
    public JAXBElement<CreateIssueMessageInThread> createCreateIssueMessageInThread(CreateIssueMessageInThread value) {
        return new JAXBElement<CreateIssueMessageInThread>(_CreateIssueMessageInThread_QNAME, CreateIssueMessageInThread.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateIssueMessageInThreadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createIssueMessageInThreadResponse")
    public JAXBElement<CreateIssueMessageInThreadResponse> createCreateIssueMessageInThreadResponse(CreateIssueMessageInThreadResponse value) {
        return new JAXBElement<CreateIssueMessageInThreadResponse>(_CreateIssueMessageInThreadResponse_QNAME, CreateIssueMessageInThreadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueThreadListWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "IssueThreadList")
    public JAXBElement<IssueThreadListWebServiceVO> createIssueThreadList(IssueThreadListWebServiceVO value) {
        return new JAXBElement<IssueThreadListWebServiceVO>(_IssueThreadList_QNAME, IssueThreadListWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateNewIssueThreadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createNewIssueThreadResponse")
    public JAXBElement<CreateNewIssueThreadResponse> createCreateNewIssueThreadResponse(CreateNewIssueThreadResponse value) {
        return new JAXBElement<CreateNewIssueThreadResponse>(_CreateNewIssueThreadResponse_QNAME, CreateNewIssueThreadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueThreadWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "IssueThreadVO")
    public JAXBElement<IssueThreadWebServiceVO> createIssueThreadVO(IssueThreadWebServiceVO value) {
        return new JAXBElement<IssueThreadWebServiceVO>(_IssueThreadVO_QNAME, IssueThreadWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IssueMessageListByThreadIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "issueMessageListByThreadIdResponse")
    public JAXBElement<IssueMessageListByThreadIdResponse> createIssueMessageListByThreadIdResponse(IssueMessageListByThreadIdResponse value) {
        return new JAXBElement<IssueMessageListByThreadIdResponse>(_IssueMessageListByThreadIdResponse_QNAME, IssueMessageListByThreadIdResponse.class, null, value);
    }

}
