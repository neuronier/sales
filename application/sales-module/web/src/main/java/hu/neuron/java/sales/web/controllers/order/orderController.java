package hu.neuron.java.sales.web.controllers.order;

import java.util.ArrayList;
import java.util.List;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.OrderVO;

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
	
	private final String[] status = {"Feldolgozas alatt", "Feldolgozva", "Kezbesitve"};
	
	private String selectedStatus;

	private OrderVO selectedOrder;

	private OrderVO newOrder;

	private List<OfferVO> offerVOs;
	
	private OfferVO selectedOffer;
	
	private String selectedOfferName;
	
	private List<String> helper;

	private String orderId;

	@PostConstruct
	public void init() {
		setLazyOrderModul(new LazyOrderModel(orderService));
	}

	public orderController() {
		super();
	}

	//lekéri az a legördülű listához az jelenleg rendelhető offereket
	public void offersInit() {
		offerVOs = new ArrayList<OfferVO>();
		helper = new ArrayList<String>();
		offerVOs = offerService.findAll();
		for (OfferVO ofv : offerVOs) {
			helper.add(ofv.getName());
		}
	}

	public void addNewOrderButtonAction() {
		newOrder = new OrderVO();
		offersInit();
	}

	public void addNewOrder() {
		newOrder.setStatus(selectedStatus);
		if (selectedOfferName != null) {
			for (OfferVO ofv : offerVOs) {
				if (selectedOfferName.equals(ofv.getName())) {
					selectedOffer = ofv;
				}
			}
			orderService.addOfferToOrder(selectedOffer, newOrder);
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

	public List<String> getHelper() {
		return helper;
	}

	public void setHelper(List<String> helper) {
		this.helper = helper;
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

}
