
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOrderListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOrderListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOrderList" type="{http://hu.neuron}orderListWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOrderListResponse", propOrder = {
    "getOrderList"
})
public class GetOrderListResponse {

    protected OrderListWebServiceVO getOrderList;

    /**
     * Gets the value of the getOrderList property.
     * 
     * @return
     *     possible object is
     *     {@link OrderListWebServiceVO }
     *     
     */
    public OrderListWebServiceVO getGetOrderList() {
        return getOrderList;
    }

    /**
     * Sets the value of the getOrderList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderListWebServiceVO }
     *     
     */
    public void setGetOrderList(OrderListWebServiceVO value) {
        this.getOrderList = value;
    }

}
