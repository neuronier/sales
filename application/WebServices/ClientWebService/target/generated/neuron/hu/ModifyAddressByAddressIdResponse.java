
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyAddressByAddressIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyAddressByAddressIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modifyAddressByAddressId" type="{http://hu.neuron}addressWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyAddressByAddressIdResponse", propOrder = {
    "modifyAddressByAddressId"
})
public class ModifyAddressByAddressIdResponse {

    protected AddressWebServiceVO modifyAddressByAddressId;

    /**
     * Gets the value of the modifyAddressByAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link AddressWebServiceVO }
     *     
     */
    public AddressWebServiceVO getModifyAddressByAddressId() {
        return modifyAddressByAddressId;
    }

    /**
     * Sets the value of the modifyAddressByAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressWebServiceVO }
     *     
     */
    public void setModifyAddressByAddressId(AddressWebServiceVO value) {
        this.modifyAddressByAddressId = value;
    }

}
