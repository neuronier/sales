
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

    private final static QName _GetOrderList_QNAME = new QName("http://hu.neuron", "getOrderList");
    private final static QName _OrderList_QNAME = new QName("http://hu.neuron", "OrderList");
    private final static QName _GetOrderByOrderIdResponse_QNAME = new QName("http://hu.neuron", "getOrderByOrderIdResponse");
    private final static QName _ModifyOrderByOrderIdResponse_QNAME = new QName("http://hu.neuron", "modifyOrderByOrderIdResponse");
    private final static QName _Order_QNAME = new QName("http://hu.neuron", "Order");
    private final static QName _GetOrderListResponse_QNAME = new QName("http://hu.neuron", "getOrderListResponse");
    private final static QName _GetOrderByOrderId_QNAME = new QName("http://hu.neuron", "getOrderByOrderId");
    private final static QName _ModifyOrderByOrderId_QNAME = new QName("http://hu.neuron", "modifyOrderByOrderId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: neuron.hu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModifyOrderByOrderId }
     * 
     */
    public ModifyOrderByOrderId createModifyOrderByOrderId() {
        return new ModifyOrderByOrderId();
    }

    /**
     * Create an instance of {@link ModifyOrderByOrderIdResponse }
     * 
     */
    public ModifyOrderByOrderIdResponse createModifyOrderByOrderIdResponse() {
        return new ModifyOrderByOrderIdResponse();
    }

    /**
     * Create an instance of {@link GetOrderListResponse }
     * 
     */
    public GetOrderListResponse createGetOrderListResponse() {
        return new GetOrderListResponse();
    }

    /**
     * Create an instance of {@link OrderListWebServiceVO }
     * 
     */
    public OrderListWebServiceVO createOrderListWebServiceVO() {
        return new OrderListWebServiceVO();
    }

    /**
     * Create an instance of {@link GetOrderByOrderIdResponse }
     * 
     */
    public GetOrderByOrderIdResponse createGetOrderByOrderIdResponse() {
        return new GetOrderByOrderIdResponse();
    }

    /**
     * Create an instance of {@link OrderWebServiceVO }
     * 
     */
    public OrderWebServiceVO createOrderWebServiceVO() {
        return new OrderWebServiceVO();
    }

    /**
     * Create an instance of {@link GetOrderByOrderId }
     * 
     */
    public GetOrderByOrderId createGetOrderByOrderId() {
        return new GetOrderByOrderId();
    }

    /**
     * Create an instance of {@link GetOrderList }
     * 
     */
    public GetOrderList createGetOrderList() {
        return new GetOrderList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOrderList")
    public JAXBElement<GetOrderList> createGetOrderList(GetOrderList value) {
        return new JAXBElement<GetOrderList>(_GetOrderList_QNAME, GetOrderList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderListWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "OrderList")
    public JAXBElement<OrderListWebServiceVO> createOrderList(OrderListWebServiceVO value) {
        return new JAXBElement<OrderListWebServiceVO>(_OrderList_QNAME, OrderListWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderByOrderIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOrderByOrderIdResponse")
    public JAXBElement<GetOrderByOrderIdResponse> createGetOrderByOrderIdResponse(GetOrderByOrderIdResponse value) {
        return new JAXBElement<GetOrderByOrderIdResponse>(_GetOrderByOrderIdResponse_QNAME, GetOrderByOrderIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOrderByOrderIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyOrderByOrderIdResponse")
    public JAXBElement<ModifyOrderByOrderIdResponse> createModifyOrderByOrderIdResponse(ModifyOrderByOrderIdResponse value) {
        return new JAXBElement<ModifyOrderByOrderIdResponse>(_ModifyOrderByOrderIdResponse_QNAME, ModifyOrderByOrderIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderWebServiceVO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "Order")
    public JAXBElement<OrderWebServiceVO> createOrder(OrderWebServiceVO value) {
        return new JAXBElement<OrderWebServiceVO>(_Order_QNAME, OrderWebServiceVO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOrderListResponse")
    public JAXBElement<GetOrderListResponse> createGetOrderListResponse(GetOrderListResponse value) {
        return new JAXBElement<GetOrderListResponse>(_GetOrderListResponse_QNAME, GetOrderListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderByOrderId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "getOrderByOrderId")
    public JAXBElement<GetOrderByOrderId> createGetOrderByOrderId(GetOrderByOrderId value) {
        return new JAXBElement<GetOrderByOrderId>(_GetOrderByOrderId_QNAME, GetOrderByOrderId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyOrderByOrderId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hu.neuron", name = "modifyOrderByOrderId")
    public JAXBElement<ModifyOrderByOrderId> createModifyOrderByOrderId(ModifyOrderByOrderId value) {
        return new JAXBElement<ModifyOrderByOrderId>(_ModifyOrderByOrderId_QNAME, ModifyOrderByOrderId.class, null, value);
    }

}
