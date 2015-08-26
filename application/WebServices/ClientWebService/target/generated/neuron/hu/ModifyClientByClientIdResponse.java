
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyClientByClientIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyClientByClientIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modifyClientByClientId" type="{http://hu.neuron}clientWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyClientByClientIdResponse", propOrder = {
    "modifyClientByClientId"
})
public class ModifyClientByClientIdResponse {

    protected ClientWebServiceVO modifyClientByClientId;

    /**
     * Gets the value of the modifyClientByClientId property.
     * 
     * @return
     *     possible object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public ClientWebServiceVO getModifyClientByClientId() {
        return modifyClientByClientId;
    }

    /**
     * Sets the value of the modifyClientByClientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientWebServiceVO }
     *     
     */
    public void setModifyClientByClientId(ClientWebServiceVO value) {
        this.modifyClientByClientId = value;
    }

}
