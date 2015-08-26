
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for issueThreadListByClientResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="issueThreadListByClientResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="issueThreadListByClient" type="{http://hu.neuron}issueThreadListWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issueThreadListByClientResponse", propOrder = {
    "issueThreadListByClient"
})
public class IssueThreadListByClientResponse {

    protected IssueThreadListWebServiceVO issueThreadListByClient;

    /**
     * Gets the value of the issueThreadListByClient property.
     * 
     * @return
     *     possible object is
     *     {@link IssueThreadListWebServiceVO }
     *     
     */
    public IssueThreadListWebServiceVO getIssueThreadListByClient() {
        return issueThreadListByClient;
    }

    /**
     * Sets the value of the issueThreadListByClient property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssueThreadListWebServiceVO }
     *     
     */
    public void setIssueThreadListByClient(IssueThreadListWebServiceVO value) {
        this.issueThreadListByClient = value;
    }

}
