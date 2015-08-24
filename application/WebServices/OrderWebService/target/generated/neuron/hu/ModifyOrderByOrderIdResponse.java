
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyOrderByOrderIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyOrderByOrderIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modifyOrderByOrderId" type="{http://hu.neuron}orderWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyOrderByOrderIdResponse", propOrder = {
    "modifyOrderByOrderId"
})
public class ModifyOrderByOrderIdResponse {

    protected OrderWebServiceVO modifyOrderByOrderId;

    /**
     * Gets the value of the modifyOrderByOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link OrderWebServiceVO }
     *     
     */
    public OrderWebServiceVO getModifyOrderByOrderId() {
        return modifyOrderByOrderId;
    }

    /**
     * Sets the value of the modifyOrderByOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderWebServiceVO }
     *     
     */
    public void setModifyOrderByOrderId(OrderWebServiceVO value) {
        this.modifyOrderByOrderId = value;
    }

}
