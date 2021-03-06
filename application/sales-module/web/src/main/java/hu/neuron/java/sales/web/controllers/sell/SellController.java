package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.OfferProductTypeServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.ClientOfferVO;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.OfferProductTypeVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.web.LocalizationsUtils;
import hu.neuron.java.sales.web.pdf.BillGenerator;
import hu.neuron.java.warehouse.whweb.web.service.WareWebService;
import hu.neuron.java.warehouse.whweb.web.service.WareWebServiceImplService;
import hu.neuron.java.web.autocomplete.CustomerAutoCompleteView;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.namespace.QName;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itextpdf.text.DocumentException;

@ViewScoped
@ManagedBean(name = "sellController")
public class SellController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private static OfferWebVO selectedWebOffer;

	private static OfferVO selectedAvailableOffer;

	private static List<OfferWebVO> selectedOffers;

	private Long total;

	private boolean disabled;

	private boolean required;
	
	private boolean registerPressedOnce;
	
	private boolean sold;

	private LazySellModel lazySellModel;
	
	private StreamedContent pdf;

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	@EJB(name = "ClientOfferService", mappedName = "ClientOfferService")
	private ClientOfferServiceRemote clientOfferService;

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;
	
	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote salesPointService;
	
	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;
	
	@EJB(mappedName = "OfferProductTypeService",name = "OfferProductTypeService")
	private OfferProductTypeServiceRemote offProdTypeService;
	
	@EJB(mappedName = "ProductTypeService", name = "ProductTypeService")
	private ProductTypeServiceRemote productTypeService;

	@ManagedProperty(value = "#{customerAutoCompleteView}")
	private CustomerAutoCompleteView customerBean;
	
	private WareWebService warehouseWebService;
	
	private UserVO currentUser;

	@PostConstruct
	public void init() {
		selectedOffers = new LinkedList<>();
		disabled = true;
		required = false;
		registerPressedOnce = false;
		sold = false;
		URL wsdl = null;
		try {
			wsdl = new URL(
					"http://javatraining.neuron.hu/warehouseApp/WareWebServiceImplService?wsdl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qName = new QName(
				"http://service.web.whWeb.warehouse.java.neuron.hu/",
				"WareWebServiceImplService");
		WareWebServiceImplService exampleImplService = new WareWebServiceImplService(wsdl, qName);
		warehouseWebService = exampleImplService.getWareWebServiceImplPort();
		setLazySellModel(new LazySellModel(offerService, userService, 
				offProdTypeService, warehouseWebService,productTypeService, this));
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			currentUser = userService.findUserByUserName(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Item Selected"));
	}

	public LazySellModel getLazySellModel() {
		return lazySellModel;
	}

	public void setLazySellModel(LazySellModel lazySellModel) {
		this.lazySellModel = lazySellModel;
	}

	public OfferVO getSelectedAvailableOffer() {
		return selectedAvailableOffer;
	}

	public void setSelectedAvailableOffer(OfferVO selectedAvailableOffer) {
		SellController.selectedAvailableOffer = selectedAvailableOffer;
	}

	public List<OfferWebVO> getSelectedOffers() {
		return selectedOffers;
	}

	public void setSelectedOffers(List<OfferWebVO> selectedOffers) {
		SellController.selectedOffers = selectedOffers;
	}

	public void addToSelectedOffers() {
		boolean contains = false;
		if (selectedOffers != null) {
			for (OfferWebVO owv : selectedOffers) {
				if (owv != null && selectedAvailableOffer != null) {
					if (owv.getOfferId().equals(
							selectedAvailableOffer.getOfferId())) {
						contains = true;
					}
				}
			}
		}
		if (!contains) {
			OfferWebVO owv = new OfferWebVO(selectedAvailableOffer);
			selectedOffers.add(owv);
		}

	}

	public OfferWebVO getSelectedWebOffer() {
		return selectedWebOffer;
	}

	public void setSelectedWebOffer(OfferWebVO selectedWebOffer) {
		SellController.selectedWebOffer = selectedWebOffer;
	}

	public void sell() {
		Date now = new Date(System.currentTimeMillis());
		if (customerBean.getSelectedClient() != null
				&& selectedOffers.size() > 0) {
			List<ClientOfferVO> offers = new LinkedList<>();
			for (OfferWebVO owv : selectedOffers) {
				ClientOfferVO purchase = new ClientOfferVO();
				purchase.setClient(customerBean.getSelectedClient());
				purchase.setOffer(owv.getOfferVo());
				purchase.setQuantity(owv.getQuantity());
				purchase.setDate(now);
				purchase.createId();
				purchase.setSalesPointId(currentUser.getSalesPoint().getSalePointId());
				offers.add(purchase);
				List<OfferProductTypeVO> optList = null;
				try {
					optList = offProdTypeService.findOfferProductTypeByOfferId(owv.getOfferId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(optList != null){
					for(OfferProductTypeVO opt : optList){
						ProductTypeVO prod;
						try {
							prod = productTypeService.	//Can throw Exception
									findProductTypeByProductTypeId(opt.getProductTypeId());
							//Can throw it's own Exception if the service call fails
							warehouseWebService.decreaseNumberOfWares( 
									currentUser.getSalesPoint().getWarehouse().getWarehouseId(),
									prod.getName(), (int)(owv.getQuantity() * opt.getQuantity()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				try {
					clientOfferService.saveClientOffer(purchase);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				pdf = new DefaultStreamedContent(BillGenerator.createBill(customerBean.getSelectedClient(),
						offers, now, currentUser.getSalesPoint()), "pdf", "szamla.pdf");// TODO
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			RequestContext requestContext = RequestContext.getCurrentInstance();  
			requestContext.execute("document.getElementsByClassName('filedownload')[0].click()");
			selectedOffers.clear();
			customerBean.setSelectedClient(null);
			customerBean.setCustomerName(null);
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							LocalizationsUtils.getText("success", context),
							LocalizationsUtils.getText("processed_sale", context));
			context.addMessage(null, msg);
			sold = true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							LocalizationsUtils.getText("error", context),
							LocalizationsUtils.getText("sale_error", context));
			context.addMessage(null, msg);
		}
	}

	public Long getTotal() {
		total = 0L;
		for (OfferWebVO owv : selectedOffers) {
			total += owv.getOfferPrice() * owv.getQuantity();
		}
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean bool) {
		this.disabled = bool;
	}

	public void registerClient() {
		
		CharSequence defaultPassword = "welcome1";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encPassword = bCryptPasswordEncoder.encode(defaultPassword);

		customerBean.getSelectedClient().setPassword(encPassword);
		customerBean.getSelectedClient().setRegistrationDate(new Date());
		clientService.saveClient(customerBean.getSelectedClient());
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("customer_registered", context)
						+ " \n " + customerBean.getSelectedClient().getName());
		context.addMessage(null, msg);
		customerBean.setSelectedClient(null);
	}

	public void setCustomerBean(CustomerAutoCompleteView bean) {
		customerBean = bean;
	}

	public CustomerAutoCompleteView getCustomerBean() {
		return customerBean;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public void registerPressed(){
		if(registerPressedOnce == false){
			registerPressedOnce = true;
			disabled = false;
			required = true;
			customerBean.setSelectedClient(new ClientVO());
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					LocalizationsUtils.getText("tip", context),
					LocalizationsUtils.getText("register_tip", context));
			context.addMessage(null, msg);
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			if(!context.isValidationFailed()){
				registerClient();
				registerPressedOnce = false;
				disabled = true;
				required = false;
			}
		}
	}
	
	public void cancelRegister() {
		if(registerPressedOnce == true){
			registerPressedOnce = false;
			disabled = true;
			required = false;
		}
	}

	public boolean isRegisterPressed() {
		return registerPressedOnce;
	}

	public void setRegisterPressed(boolean registerPressed) {
		this.registerPressedOnce = registerPressed;
	}
	
	public void removeSelectedOffer(){
		selectedOffers.remove(selectedWebOffer);
		selectedWebOffer=null;
	}

	public StreamedContent getPdf() {
		return pdf;
	}

	public void setPdf(StreamedContent pdf) {
		this.pdf = pdf;
	}

	public WareWebService getWarehouseWebService() {
		return warehouseWebService;
	}

	public void setWarehouseWebService(WareWebService warehouseWebService) {
		this.warehouseWebService = warehouseWebService;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public UserVO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserVO currentUser) {
		this.currentUser = currentUser;
	}
}
