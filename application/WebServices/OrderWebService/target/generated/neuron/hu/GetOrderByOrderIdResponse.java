
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOrderByOrderIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOrderByOrderIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOrderByOrderId" type="{http://hu.neuron}orderWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOrderByOrderIdResponse", propOrder = {
    "getOrderByOrderId"
})
public class GetOrderByOrderIdResponse {

    protected OrderWebServiceVO getOrderByOrderId;

    /**
     * Gets the value of the getOrderByOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link OrderWebServiceVO }
     *     
     */
    public OrderWebServiceVO getGetOrderByOrderId() {
        return getOrderByOrderId;
    }

    /**
     * Sets the value of the getOrderByOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderWebServiceVO }
     *     
     */
    public void setGetOrderByOrderId(OrderWebServiceVO value) {
        this.getOrderByOrderId = value;
    }

}
