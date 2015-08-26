
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

    private final static QName _RemoveClientByClientId_QNAME = new QName("http://hu.neuron", "removeClientByClientId");
    private final static QName _GetClientList_QNAME = new QName("http://hu.neuron", "getClientList");
    private final static QName _ModifyClientByClientIdResponse_QNAME = new QName("http://hu.neuron", "modifyClientByClientIdResponse");
    private final static QName _CreateClientResponse_QNAME = new QName("http://hu.neuron", "createClientResponse");
    private final static QName _ClientList_QNAME = new QName("http://hu.neuron", "ClientList");
    private final static QName _GetClientByClientId_QNAME = new QName("http://hu.neuron", "getClientByClientId");
    private final static QName _GetClientByClientIdResponse_QNAME = new QName("http://hu.neuron", "getClientByClientIdResponse");
    private final static QName _GetClientListResponse_QNAME = new QName("http://hu.neuron", "getClientListResponse");
    private final static QName _CreateClient_QNAME = new QName("http://hu.neuron", "createClient");
    private final static QName _RemoveClientByClientIdResponse_QNAME = new QName("http://hu.neuron", "removeClientByClientIdResponse");
    private final static QName _Client_QNAME = new QName("http://hu.neuron", "Client");
    private final static QName _ModifyClientByClientId_QNAME = new QName("http://hu.neuron", "modifyClientByClientId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: neuron.hu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetClientByClientId }
     * 
     */
    public GetClientByClientId createGetClientByClientId() {
        return new GetClientByClientId();
    }

    /**
     * Create an instance of {@link GetClientListResponse }
     * 
     */
    public GetClientListResponse createGetClientListResponse() {
        return new GetClientListResponse();
    }

    /**
     * Create an instance of {@link ClientListWebServiceVO }
     * 
     */
    public ClientListWebServiceVO createClientListWebServiceVO() {
        return new ClientListWebServiceVO();
    }

    /**
     * Create an instance of {@link RemoveClientByClientId }
     * 
     */
    public RemoveClientByClientId createRemoveClientByClientId() {
        return new RemoveClientByClientId();
    }

    /**
     * Create an instance of {@link GetClientList }
     * 
     */
    public GetClientList createGetClientList() {
        return new GetClientList();
    }

    /**
     * Create an instance of {@link ModifyClientByClientId }
     * 
     */
    public ModifyClientByClientId createModifyClientByClientId() {
        return new ModifyClientByClientId();
    }

    /**
     * Create an instance of {@link CreateClient }
     * 
     */
    public CreateClient createCreateClient() {
        return new CreateClient();
    }

    /**
     * Create an instance of {@link ModifyClientByClientIdResponse }
     * 
     */
    public ModifyClientByClientIdResponse createModifyClientByClientIdResponse() {
        return new ModifyClientByClientIdResponse();
    }

    /**
     * Create an instance of {@link ClientWebServiceVO }
     * 
     */
    public ClientWebServiceVO createClientWebServiceVO() {
        return new ClientWebServiceVO();
    }

    /**
     * Create an instance of {@link CreateClientResponse }
     * 
     */
    public CreateClientResponse createCreateClientResponse() {
        return new CreateClientResponse();
    }

    /**
     * Create an instance of {@link RemoveClientByClientIdResponse }
     * 
     */
    public RemoveClientByClientIdResponse createRemoveClientByClientIdResponse() {
        return new RemoveClientByClientIdResponse();
    }

    /**
     * Create an instance of {@link GetClientByClientIdResponse }
     * 
     */
    public GetClientByClientIdResponse createGetClientByClientIdResponse() {
        return new GetClientByClientIdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveClientByClientId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "removeClientByClientId")
    public JAXBElement<RemoveClientByClientId> createRemoveClientByClientId(RemoveClientByClientId value) {
        return new JAXBElement<RemoveClientByClientId>(_RemoveClientByClientId_QNAME, RemoveClientByClientId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getClientList")
    public JAXBElement<GetClientList> createGetClientList(GetClientList value) {
        return new JAXBElement<GetClientList>(_GetClientList_QNAME, GetClientList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyClientByClientIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyClientByClientIdResponse")
    public JAXBElement<ModifyClientByClientIdResponse> createModifyClientByClientIdResponse(ModifyClientByClientIdResponse value) {
        return new JAXBElement<ModifyClientByClientIdResponse>(_ModifyClientByClientIdResponse_QNAME, ModifyClientByClientIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createClientResponse")
    public JAXBElement<CreateClientResponse> createCreateClientResponse(CreateClientResponse value) {
        return new JAXBElement<CreateClientResponse>(_CreateClientResponse_QNAME, CreateClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientListWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "ClientList")
    public JAXBElement<ClientListWebServiceVO> createClientList(ClientListWebServiceVO value) {
        return new JAXBElement<ClientListWebServiceVO>(_ClientList_QNAME, ClientListWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientByClientId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getClientByClientId")
    public JAXBElement<GetClientByClientId> createGetClientByClientId(GetClientByClientId value) {
        return new JAXBElement<GetClientByClientId>(_GetClientByClientId_QNAME, GetClientByClientId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientByClientIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getClientByClientIdResponse")
    public JAXBElement<GetClientByClientIdResponse> createGetClientByClientIdResponse(GetClientByClientIdResponse value) {
        return new JAXBElement<GetClientByClientIdResponse>(_GetClientByClientIdResponse_QNAME, GetClientByClientIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getClientListResponse")
    public JAXBElement<GetClientListResponse> createGetClientListResponse(GetClientListResponse value) {
        return new JAXBElement<GetClientListResponse>(_GetClientListResponse_QNAME, GetClientListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createClient")
    public JAXBElement<CreateClient> createCreateClient(CreateClient value) {
        return new JAXBElement<CreateClient>(_CreateClient_QNAME, CreateClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveClientByClientIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "removeClientByClientIdResponse")
    public JAXBElement<RemoveClientByClientIdResponse> createRemoveClientByClientIdResponse(RemoveClientByClientIdResponse value) {
        return new JAXBElement<RemoveClientByClientIdResponse>(_RemoveClientByClientIdResponse_QNAME, RemoveClientByClientIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "Client")
    public JAXBElement<ClientWebServiceVO> createClient(ClientWebServiceVO value) {
        return new JAXBElement<ClientWebServiceVO>(_Client_QNAME, ClientWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyClientByClientId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyClientByClientId")
    public JAXBElement<ModifyClientByClientId> createModifyClientByClientId(ModifyClientByClientId value) {
        return new JAXBElement<ModifyClientByClientId>(_ModifyClientByClientId_QNAME, ModifyClientByClientId.class, null, value);
    }

}
