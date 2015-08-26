
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getClientByClientIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getClientByClientIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getClientByClientId" type="{http://hu.neuron}clientWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClientByClientIdResponse", propOrder = {
    "getClientByClientId"
})
public class GetClientByClientIdResponse {

    protected ClientWebServiceVO getClientByClientId;

    /**
     * Gets the value of the getClientByClientId property.
     * 
     * @return
     *     possible object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public ClientWebServiceVO getGetClientByClientId() {
        return getClientByClientId;
    }

    /**
     * Sets the value of the getClientByClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public void setGetClientByClientId(ClientWebServiceVO value) {
        this.getClientByClientId = value;
    }

}
