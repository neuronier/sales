
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOfferByOfferIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOfferByOfferIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOfferByOfferId" type="{http://hu.neuron}offerWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOfferByOfferIdResponse", propOrder = {
    "getOfferByOfferId"
})
public class GetOfferByOfferIdResponse {

    protected OfferWebServiceVO getOfferByOfferId;

    /**
     * Gets the value of the getOfferByOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public OfferWebServiceVO getGetOfferByOfferId() {
        return getOfferByOfferId;
    }

    /**
     * Sets the value of the getOfferByOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferWebServiceVO }
     *     
     */
    public void setGetOfferByOfferId(OfferWebServiceVO value) {
        this.getOfferByOfferId = value;
    }

}
