
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getClientListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getClientListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getClientList" type="{http://hu.neuron}clientListWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClientListResponse", propOrder = {
    "getClientList"
})
public class GetClientListResponse {

    protected ClientListWebServiceVO getClientList;

    /**
     * Gets the value of the getClientList property.
     * 
     * @return
     *     possible object is
     *     {@link ClientListWebServiceVO }
     *     
     */
    public ClientListWebServiceVO getGetClientList() {
        return getClientList;
    }

    /**
     * Sets the value of the getClientList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientListWebServiceVO }
     *     
     */
    public void setGetClientList(ClientListWebServiceVO value) {
        this.getClientList = value;
    }

}
