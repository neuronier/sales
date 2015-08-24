
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyOfferByOfferIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyOfferByOfferIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modifyOfferByOfferId" type="{http://hu.neuron}offerWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyOfferByOfferIdResponse", propOrder = {
    "modifyOfferByOfferId"
})
public class ModifyOfferByOfferIdResponse {

    protected OfferWebServiceVO modifyOfferByOfferId;

    /**
     * Gets the value of the modifyOfferByOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public OfferWebServiceVO getModifyOfferByOfferId() {
        return modifyOfferByOfferId;
    }

    /**
     * Sets the value of the modifyOfferByOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public void setModifyOfferByOfferId(OfferWebServiceVO value) {
        this.modifyOfferByOfferId = value;
    }

}
