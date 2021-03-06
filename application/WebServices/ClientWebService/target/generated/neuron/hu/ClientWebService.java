
package neuron.hu;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3-b02-
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ClientWebService", targetNamespace = "http://hu.neuron")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ClientWebService {


    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @param arg6
     * @return
     *     returns neuron.hu.ClientWebServiceVO
     */
    @WebMethod
    @WebResult(name = "modifyClientByClientId", targetNamespace = "")
    @RequestWrapper(localName = "modifyClientByClientId", targetNamespace = "http://hu.neuron", className = "neuron.hu.ModifyClientByClientId")
    @ResponseWrapper(localName = "modifyClientByClientIdResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.ModifyClientByClientIdResponse")
    public ClientWebServiceVO modifyClientByClientId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        String arg6);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "removeClientByClientId", targetNamespace = "http://hu.neuron", className = "neuron.hu.RemoveClientByClientId")
    @ResponseWrapper(localName = "removeClientByClientIdResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.RemoveClientByClientIdResponse")
    public void removeClientByClientId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns neuron.hu.ClientWebServiceVO
     */
    @WebMethod
    @WebResult(name = "createClient", targetNamespace = "")
    @RequestWrapper(localName = "createClient", targetNamespace = "http://hu.neuron", className = "neuron.hu.CreateClient")
    @ResponseWrapper(localName = "createClientResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.CreateClientResponse")
    public ClientWebServiceVO createClient(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5);

    /**
     * 
     * @return
     *     returns neuron.hu.ClientListWebServiceVO
     */
    @WebMethod
    @WebResult(name = "getClientList", targetNamespace = "")
    @RequestWrapper(localName = "getClientList", targetNamespace = "http://hu.neuron", className = "neuron.hu.GetClientList")
    @ResponseWrapper(localName = "getClientListResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.GetClientListResponse")
    public ClientListWebServiceVO getClientList();

    /**
     * 
     * @param arg0
     * @return
     *     returns neuron.hu.ClientWebServiceVO
     */
    @WebMethod
    @WebResult(name = "getClientByClientId", targetNamespace = "")
    @RequestWrapper(localName = "getClientByClientId", targetNamespace = "http://hu.neuron", className = "neuron.hu.GetClientByClientId")
    @ResponseWrapper(localName = "getClientByClientIdResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.GetClientByClientIdResponse")
    public ClientWebServiceVO getClientByClientId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns neuron.hu.AddressWebServiceVO
     */
    @WebMethod
    @WebResult(name = "modifyAddressByAddressId", targetNamespace = "")
    @RequestWrapper(localName = "modifyAddressByAddressId", targetNamespace = "http://hu.neuron", className = "neuron.hu.ModifyAddressByAddressId")
    @ResponseWrapper(localName = "modifyAddressByAddressIdResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.ModifyAddressByAddressIdResponse")
    public AddressWebServiceVO modifyAddressByAddressId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns neuron.hu.AddressWebServiceVO
     */
    @WebMethod
    @WebResult(name = "createAddress", targetNamespace = "")
    @RequestWrapper(localName = "createAddress", targetNamespace = "http://hu.neuron", className = "neuron.hu.CreateAddress")
    @ResponseWrapper(localName = "createAddressResponse", targetNamespace = "http://hu.neuron", className = "neuron.hu.CreateAddressResponse")
    public AddressWebServiceVO createAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

}
