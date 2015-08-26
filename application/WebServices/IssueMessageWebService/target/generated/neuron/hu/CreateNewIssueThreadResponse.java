
package neuron.hu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewIssueThreadResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewIssueThreadResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createNewIssueThread" type="{http://hu.neuron}issueThreadWebServiceVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createNewIssueThreadResponse", propOrder = {
    "createNewIssueThread"
})
public class CreateNewIssueThreadResponse {

    protected IssueThreadWebServiceVO createNewIssueThread;

    /**
     * Gets the value of the createNewIssueThread property.
     * 
     * @return
     *     possible object is
     *     {@link IssueThreadWebServiceVO }
     *     
     */
    public IssueThreadWebServiceVO getCreateNewIssueThread() {
        return createNewIssueThread;
    }

    /**
     * Sets the value of the createNewIssueThread property.
     * 
     * @param value
     *     allowed object is
     *     {@link IssueThreadWebServiceVO }
     *     
     */
    public void setCreateNewIssueThread(IssueThreadWebServiceVO value) {
        this.createNewIssueThread = value;
    }

}
