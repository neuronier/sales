package hu.neuron.java.sales.web.controllers.order;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
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
	
	@EJB(name="OrderService", mappedName="OrderService")
	OrderServiceRemote orderService;
	
	@EJB(name="OfferService", mappedName="OfferService")
	OfferServiceRemote offerService;
	
	@EJB(name="ClientService", mappedName="ClientService")
	ClientServiceRemote clientService;
	
	@EJB(name="ProductTypeService", mappedName="ProductTypeService")
	ProductTypeServiceRemote productTypeService;
	
	private OrderVO selectedOrder;
	
	private OrderVO newOrder;
	
	private String orderName;
	
	@PostConstruct
	public void init(){
		setLazyOrderModul(new LazyOrderModel(orderService));
	}
	
	public orderController(){
		super();
	}
	
	
	public void addNewOrderButtonAction(){
		newOrder = new OrderVO();
	}
	public void addNewOrder(){
		newOrder.setName(orderName);
		newOrder.setStatus("Megrendelve");
		
		orderService.saveOrder(newOrder);
	}
	
	public void onRowSelected(SelectEvent event){
		orderName = selectedOrder.getName();
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOrder.getName()));
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

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
}
