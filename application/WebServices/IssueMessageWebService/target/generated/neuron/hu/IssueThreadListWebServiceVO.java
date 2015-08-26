
package neuron.hu;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for issueThreadListWebServiceVO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="issueThreadListWebServiceVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IssueThread" type="{http://hu.neuron}issueThreadWebServiceVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "issueThreadListWebServiceVO", propOrder = {
    "issueThread"
})
public class IssueThreadListWebServiceVO {

    @XmlElement(name = "IssueThread")
    protected List<IssueThreadWebServiceVO> issueThread;

    /**
     * Gets the value of the issueThread property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issueThread property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssueThread().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IssueThreadWebServiceVO }
     * 
     * 
     */
    public List<IssueThreadWebServiceVO> getIssueThread() {
        if (issueThread == null) {
            issueThread = new ArrayList<IssueThreadWebServiceVO>();
        }
        return this.issueThread;
    }

}
