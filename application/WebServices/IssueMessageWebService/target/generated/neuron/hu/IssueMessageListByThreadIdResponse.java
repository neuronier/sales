
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for issueMessageListByThreadIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="issueMessageListByThreadIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="issueMessageListByThreadId" type="{http://hu.neuron}issueMessageListWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issueMessageListByThreadIdResponse", propOrder = {
    "issueMessageListByThreadId"
})
public class IssueMessageListByThreadIdResponse {

    protected IssueMessageListWebServiceVO issueMessageListByThreadId;

    /**
     * Gets the value of the issueMessageListByThreadId property.
     * 
     * @return
     *     possible object is
     *     {@link IssueMessageListWebServiceVO }
     *     
     */
    public IssueMessageListWebServiceVO getIssueMessageListByThreadId() {
        return issueMessageListByThreadId;
    }

    /**
     * Sets the value of the issueMessageListByThreadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssueMessageListWebServiceVO }
     *     
     */
    public void setIssueMessageListByThreadId(IssueMessageListWebServiceVO value) {
        this.issueMessageListByThreadId = value;
    }

}
