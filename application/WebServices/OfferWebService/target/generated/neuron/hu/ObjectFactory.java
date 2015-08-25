
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

    private final static QName _GetOfferByOfferIdResponse_QNAME = new QName("http://hu.neuron", "getOfferByOfferIdResponse");
    private final static QName _CreateOffer_QNAME = new QName("http://hu.neuron", "createOffer");
    private final static QName _ModifyOfferByOfferIdResponse_QNAME = new QName("http://hu.neuron", "modifyOfferByOfferIdResponse");
    private final static QName _ModifyOfferByOfferId_QNAME = new QName("http://hu.neuron", "modifyOfferByOfferId");
    private final static QName _GetOfferByOfferId_QNAME = new QName("http://hu.neuron", "getOfferByOfferId");
    private final static QName _GetOfferList_QNAME = new QName("http://hu.neuron", "getOfferList");
    private final static QName _OfferList_QNAME = new QName("http://hu.neuron", "OfferList");
    private final static QName _RemoveOfferByOfferId_QNAME = new QName("http://hu.neuron", "removeOfferByOfferId");
    private final static QName _RemoveOfferByOfferIdResponse_QNAME = new QName("http://hu.neuron", "removeOfferByOfferIdResponse");
    private final static QName _Offer_QNAME = new QName("http://hu.neuron", "Offer");
    private final static QName _GetOfferListResponse_QNAME = new QName("http://hu.neuron", "getOfferListResponse");
    private final static QName _CreateOfferResponse_QNAME = new QName("http://hu.neuron", "createOfferResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: neuron.hu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OfferWebServiceVO }
     * 
     */
    public OfferWebServiceVO createOfferWebServiceVO() {
        return new OfferWebServiceVO();
    }

    /**
     * Create an instance of {@link ModifyOfferByOfferIdResponse }
     * 
     */
    public ModifyOfferByOfferIdResponse createModifyOfferByOfferIdResponse() {
        return new ModifyOfferByOfferIdResponse();
    }

    /**
     * Create an instance of {@link GetOfferByOfferIdResponse }
     * 
     */
    public GetOfferByOfferIdResponse createGetOfferByOfferIdResponse() {
        return new GetOfferByOfferIdResponse();
    }

    /**
     * Create an instance of {@link GetOfferList }
     * 
     */
    public GetOfferList createGetOfferList() {
        return new GetOfferList();
    }

    /**
     * Create an instance of {@link ModifyOfferByOfferId.Arg3 }
     * 
     */
    public ModifyOfferByOfferId.Arg3 createModifyOfferByOfferIdArg3() {
        return new ModifyOfferByOfferId.Arg3();
    }

    /**
     * Create an instance of {@link ModifyOfferByOfferId }
     * 
     */
    public ModifyOfferByOfferId createModifyOfferByOfferId() {
        return new ModifyOfferByOfferId();
    }

    /**
     * Create an instance of {@link GetOfferListResponse }
     * 
     */
    public GetOfferListResponse createGetOfferListResponse() {
        return new GetOfferListResponse();
    }

    /**
     * Create an instance of {@link RemoveOfferByOfferIdResponse }
     * 
     */
    public RemoveOfferByOfferIdResponse createRemoveOfferByOfferIdResponse() {
        return new RemoveOfferByOfferIdResponse();
    }

    /**
     * Create an instance of {@link ModifyOfferByOfferId.Arg3 .Entry }
     * 
     */
    public ModifyOfferByOfferId.Arg3 .Entry createModifyOfferByOfferIdArg3Entry() {
        return new ModifyOfferByOfferId.Arg3 .Entry();
    }

    /**
     * Create an instance of {@link CreateOfferResponse }
     * 
     */
    public CreateOfferResponse createCreateOfferResponse() {
        return new CreateOfferResponse();
    }

    /**
     * Create an instance of {@link OfferListWebServiceVO }
     * 
     */
    public OfferListWebServiceVO createOfferListWebServiceVO() {
        return new OfferListWebServiceVO();
    }

    /**
     * Create an instance of {@link CreateOffer.Arg2 }
     * 
     */
    public CreateOffer.Arg2 createCreateOfferArg2() {
        return new CreateOffer.Arg2();
    }

    /**
     * Create an instance of {@link GetOfferByOfferId }
     * 
     */
    public GetOfferByOfferId createGetOfferByOfferId() {
        return new GetOfferByOfferId();
    }

    /**
     * Create an instance of {@link CreateOffer }
     * 
     */
    public CreateOffer createCreateOffer() {
        return new CreateOffer();
    }

    /**
     * Create an instance of {@link RemoveOfferByOfferId }
     * 
     */
    public RemoveOfferByOfferId createRemoveOfferByOfferId() {
        return new RemoveOfferByOfferId();
    }

    /**
     * Create an instance of {@link CreateOffer.Arg2 .Entry }
     * 
     */
    public CreateOffer.Arg2 .Entry createCreateOfferArg2Entry() {
        return new CreateOffer.Arg2 .Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfferByOfferIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOfferByOfferIdResponse")
    public JAXBElement<GetOfferByOfferIdResponse> createGetOfferByOfferIdResponse(GetOfferByOfferIdResponse value) {
        return new JAXBElement<GetOfferByOfferIdResponse>(_GetOfferByOfferIdResponse_QNAME, GetOfferByOfferIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOffer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createOffer")
    public JAXBElement<CreateOffer> createCreateOffer(CreateOffer value) {
        return new JAXBElement<CreateOffer>(_CreateOffer_QNAME, CreateOffer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOfferByOfferIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyOfferByOfferIdResponse")
    public JAXBElement<ModifyOfferByOfferIdResponse> createModifyOfferByOfferIdResponse(ModifyOfferByOfferIdResponse value) {
        return new JAXBElement<ModifyOfferByOfferIdResponse>(_ModifyOfferByOfferIdResponse_QNAME, ModifyOfferByOfferIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOfferByOfferId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyOfferByOfferId")
    public JAXBElement<ModifyOfferByOfferId> createModifyOfferByOfferId(ModifyOfferByOfferId value) {
        return new JAXBElement<ModifyOfferByOfferId>(_ModifyOfferByOfferId_QNAME, ModifyOfferByOfferId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfferByOfferId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOfferByOfferId")
    public JAXBElement<GetOfferByOfferId> createGetOfferByOfferId(GetOfferByOfferId value) {
        return new JAXBElement<GetOfferByOfferId>(_GetOfferByOfferId_QNAME, GetOfferByOfferId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfferList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOfferList")
    public JAXBElement<GetOfferList> createGetOfferList(GetOfferList value) {
        return new JAXBElement<GetOfferList>(_GetOfferList_QNAME, GetOfferList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferListWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "OfferList")
    public JAXBElement<OfferListWebServiceVO> createOfferList(OfferListWebServiceVO value) {
        return new JAXBElement<OfferListWebServiceVO>(_OfferList_QNAME, OfferListWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOfferByOfferId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "removeOfferByOfferId")
    public JAXBElement<RemoveOfferByOfferId> createRemoveOfferByOfferId(RemoveOfferByOfferId value) {
        return new JAXBElement<RemoveOfferByOfferId>(_RemoveOfferByOfferId_QNAME, RemoveOfferByOfferId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveOfferByOfferIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "removeOfferByOfferIdResponse")
    public JAXBElement<RemoveOfferByOfferIdResponse> createRemoveOfferByOfferIdResponse(RemoveOfferByOfferIdResponse value) {
        return new JAXBElement<RemoveOfferByOfferIdResponse>(_RemoveOfferByOfferIdResponse_QNAME, RemoveOfferByOfferIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "Offer")
    public JAXBElement<OfferWebServiceVO> createOffer(OfferWebServiceVO value) {
        return new JAXBElement<OfferWebServiceVO>(_Offer_QNAME, OfferWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOfferListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOfferListResponse")
    public JAXBElement<GetOfferListResponse> createGetOfferListResponse(GetOfferListResponse value) {
        return new JAXBElement<GetOfferListResponse>(_GetOfferListResponse_QNAME, GetOfferListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOfferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "createOfferResponse")
    public JAXBElement<CreateOfferResponse> createCreateOfferResponse(CreateOfferResponse value) {
        return new JAXBElement<CreateOfferResponse>(_CreateOfferResponse_QNAME, CreateOfferResponse.class, null, value);
    }

}
