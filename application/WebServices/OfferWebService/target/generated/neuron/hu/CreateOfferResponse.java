
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createOfferResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createOfferResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createOffer" type="{http://hu.neuron}offerWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOfferResponse", propOrder = {
    "createOffer"
})
public class CreateOfferResponse {

    protected OfferWebServiceVO createOffer;

    /**
     * Gets the value of the createOffer property.
     * 
     * @return
     *     possible object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public OfferWebServiceVO getCreateOffer() {
        return createOffer;
    }

    /**
     * Sets the value of the createOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public void setCreateOffer(OfferWebServiceVO value) {
        this.createOffer = value;
    }

}
