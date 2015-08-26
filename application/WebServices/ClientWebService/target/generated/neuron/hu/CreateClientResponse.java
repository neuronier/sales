
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createClientResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createClientResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createClient" type="{http://hu.neuron}clientWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createClientResponse", propOrder = {
    "createClient"
})
public class CreateClientResponse {

    protected ClientWebServiceVO createClient;

    /**
     * Gets the value of the createClient property.
     * 
     * @return
     *     possible object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public ClientWebServiceVO getCreateClient() {
        return createClient;
    }

    /**
     * Sets the value of the createClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public void setCreateClient(ClientWebServiceVO value) {
        this.createClient = value;
    }

}
