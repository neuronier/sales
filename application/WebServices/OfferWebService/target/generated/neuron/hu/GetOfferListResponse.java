
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOfferListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOfferListResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getOfferList" type="{http://hu.neuron}offerListWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOfferListResponse", propOrder = {
    "getOfferList"
})
public class GetOfferListResponse {

    protected OfferListWebServiceVO getOfferList;

    /**
     * Gets the value of the getOfferList property.
     * 
     * @return
     *     possible object is
     *     {@link OfferListWebServiceVO }
     *     
     */
    public OfferListWebServiceVO getGetOfferList() {
        return getOfferList;
    }

    /**
     * Sets the value of the getOfferList property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferListWebServiceVO }
     *     
     */
    public void setGetOfferList(OfferListWebServiceVO value) {
        this.getOfferList = value;
    }

}
