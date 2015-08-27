
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createAddressResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createAddressResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createAddress" type="{http://hu.neuron}addressWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createAddressResponse", propOrder = {
    "createAddress"
})
public class CreateAddressResponse {

    protected AddressWebServiceVO createAddress;

    /**
     * Gets the value of the createAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressWebServiceVO }
     *     
     */
    public AddressWebServiceVO getCreateAddress() {
        return createAddress;
    }

    /**
     * Sets the value of the createAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressWebServiceVO }
     *     
     */
    public void setCreateAddress(AddressWebServiceVO value) {
        this.createAddress = value;
    }

}
