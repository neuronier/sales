package hu.neuron.java.sales.web.controllers.order;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.ClientVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "orderController")
public class orderController {

	private LazyOrderModel lazyOrderModul;

	@EJB(name = "OrderService", mappedName = "OrderService")
	OrderServiceRemote orderService;

	@EJB(name = "OfferService", mappedName = "OfferService")
	OfferServiceRemote offerService;

	@EJB(name = "ClientService", mappedName = "ClientService")
	ClientServiceRemote clientService;

	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	private final String[] status = { "Feldolgozas alatt", "Feldolgozva",
			"Kezbesitve" };

	private String selectedStatus;

	private OrderVO selectedOrder;

	private OrderVO newOrder;

	private List<OfferVO> offerVOs;

	private OfferVO selectedOffer;

	private List<ClientVO> clientVOs;

	private ClientVO selectedClient;

	private String selectedOfferName;

	private List<String> clientHelper;

	private List<String> offerHelper;

	private String selectedClientName;

	private String orderId;

	private List<ProductTypeVO> prodTypeVOs;

	private ProductTypeVO selectedProdType;

	private List<String> productHelper;

	private String selectedProductTypeName;

	private int counterOffer;
	
	private int counterProduct;

	private String counterOfferString;
	
	private String counterProductString;

	@PostConstruct
	public void init() {
		setLazyOrderModul(new LazyOrderModel(orderService));
	}
	
	
	public orderController() {
		super();
	}
	
	public void productInit(){
		prodTypeVOs = new ArrayList<ProductTypeVO>();
		productHelper = new ArrayList<String>();
		prodTypeVOs = productTypeService.findAll();
		for (ProductTypeVO ptvo : prodTypeVOs) {
			productHelper.add(ptvo.getName());
		}
	}
	
	
	public List<String> completeTextClient(String query) {
		clientVOs = new ArrayList<ClientVO>();
		clientHelper = new ArrayList<String>();
		try {
			clientVOs = clientService.findAll();
			query.toLowerCase();
			for (ClientVO cvo : clientVOs) {
				if (cvo.getName().toLowerCase().startsWith(query)) {
					clientHelper.add(cvo.getName());
				}
			}
			return clientHelper;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void offersInit() {
		offerVOs = new ArrayList<OfferVO>();
		offerHelper = new ArrayList<String>();
		offerVOs = offerService.findAll();
		for (OfferVO ofv : offerVOs) {
			offerHelper.add(ofv.getName());
		}
	}

	public void addNewOrderButtonAction() {
		newOrder = new OrderVO();
		offersInit();
		productInit();
		counterOffer = 1;
		
		counterProduct = 1;
		
		counterProductString = counterProduct + " pcs";
		counterOfferString = counterOffer + " pcs";
	}

	public void addNewOrder() {
		newOrder.setStatus(selectedStatus);
		if (selectedOfferName != null) {
			selectedOffer = new OfferVO();
			for (OfferVO ofv : offerVOs) {
				if (selectedOfferName.equals(ofv.getName())) {
					selectedOffer = ofv;
				}
			}
			orderService.addOfferToOrder(selectedOffer, newOrder, counterOffer);

		}

		if (selectedClientName != null) {
			selectedClient = new ClientVO();
			// System.out.println(selectedClientName);
			for (ClientVO cvo : clientVOs) {
				if (cvo.getName().equals(selectedClientName)) {
					selectedClient = cvo;
				}
			}
			orderService.addClientToOrder(selectedClient, newOrder);
		}
		
		if(selectedProductTypeName != null){
			selectedProdType = new ProductTypeVO();
			for (ProductTypeVO ptvo : prodTypeVOs) {
				if(selectedProductTypeName.equals(ptvo.getName())){
					selectedProdType = ptvo;
				}
			}
			orderService.addProductTypeToOrder(selectedProdType, newOrder, counterProduct);
		}
		orderService.saveOrder(newOrder);
	}

	public void onRowSelected(SelectEvent event) {
		orderId = selectedOrder.getOrderId();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOrder.getOrderId()));
	}

	public void increment() {
		counterOffer++;

		counterOfferString = counterOffer + " pcs";
	}

	public void dcrement() {
		counterOffer--;

		counterOfferString = counterOffer + " pcs";
	}
	
	public void increment2() {
		
		setCounterProduct(getCounterProduct() + 1);

		counterProductString = counterProduct + " pcs";
	}

	public void dcrement2() {
		setCounterProduct(getCounterProduct() - 1);

		setCounterProductString(counterProduct + " pcs");
	}

	public OrderVO getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(OrderVO selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public OrderVO getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(OrderVO newOrder) {
		this.newOrder = newOrder;
	}

	public LazyOrderModel getLazyOrderModul() {
		return lazyOrderModul;
	}

	public void setLazyOrderModul(LazyOrderModel lazyOrderModul) {
		this.lazyOrderModul = lazyOrderModul;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OfferVO> getOfferVOs() {
		return offerVOs;
	}

	public void setOfferVOs(List<OfferVO> offerVOs) {
		this.offerVOs = offerVOs;
	}

	public List<String> getofferHelper() {
		return offerHelper;
	}

	public void setofferHelper(List<String> helper) {
		this.offerHelper = helper;
	}

	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public String getSelectedOfferName() {
		return selectedOfferName;
	}

	public void setSelectedOfferName(String selectedOfferName) {
		this.selectedOfferName = selectedOfferName;
	}

	public String[] getStatus() {
		return status;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public List<ClientVO> getClientVOs() {
		return clientVOs;
	}

	public void setClientVOs(List<ClientVO> clientVOs) {
		this.clientVOs = clientVOs;
	}

	public ClientVO getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(ClientVO selectedClient) {
		this.selectedClient = selectedClient;
	}

	public List<String> getClientHelper() {
		return clientHelper;
	}

	public void setClientHelper(List<String> clientHelper) {
		this.clientHelper = clientHelper;
	}

	public String getSelectedClientName() {
		return selectedClientName;
	}

	public void setSelectedClientName(String selectedClientName) {
		this.selectedClientName = selectedClientName;
	}

	public int getCounterOffer() {
		return counterOffer;
	}

	public void setCounterOffer(int counterOffer) {
		this.counterOffer = counterOffer;
	}

	public String getCounterOfferString() {
		return counterOfferString;
	}

	public void setCounterOfferString(String counterOfferString) {
		this.counterOfferString = counterOfferString;
	}

	public List<ProductTypeVO> getProdTypeVOs() {
		return prodTypeVOs;
	}

	public void setProdTypeVOs(List<ProductTypeVO> prodTypeVOs) {
		this.prodTypeVOs = prodTypeVOs;
	}

	public ProductTypeVO getSelectedProdType() {
		return selectedProdType;
	}

	public void setSelectedProdType(ProductTypeVO selectedProdType) {
		this.selectedProdType = selectedProdType;
	}

	public List<String> getProductHelper() {
		return productHelper;
	}

	public void setProductHelper(List<String> productHelper) {
		this.productHelper = productHelper;
	}

	public String getSelectedProductTypeName() {
		return selectedProductTypeName;
	}

	public void setSelectedProductTypeName(String selectedProductTypeName) {
		this.selectedProductTypeName = selectedProductTypeName;
	}

	public int getCounterProduct() {
		return counterProduct;
	}

	public void setCounterProduct(int counterProduct) {
		this.counterProduct = counterProduct;
	}

	public String getCounterProductString() {
		return counterProductString;
	}

	public void setCounterProductString(String counterProductString) {
		this.counterProductString = counterProductString;
	}

}
