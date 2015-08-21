package hu.neuron.java.sales.web.controllers.order;

import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	// SZERKESZTENI KELL
	private final String[] status = { "Feldolgozas alatt", "Feldolgozva",
			"Kezbesitve" };

	private String selectedStatus;

	private OrderVO newOrder;

	private OrderVO selectedOrder;

	private String selectedOrderId;

	private List<ProductTypeVO> prodTypeVOs;

	private ProductTypeVO selectedProdType;

	private List<String> productHelper;

	private String selectedProductTypeName;

	private int quantity;

	private List<PList> products;

	@PostConstruct
	public void init() {
		setLazyOrderModul(new LazyOrderModel(orderService));
	}

	public orderController() {
		super();
	}

	public List<String> completeTextProduct(String query) {
		prodTypeVOs = new ArrayList<ProductTypeVO>();
		productHelper = new ArrayList<String>();
		try {
			prodTypeVOs = productTypeService.findAll();
			//System.out.println("Product type SIZE" + prodTypeVOs.size());
			query.toLowerCase();
			for (ProductTypeVO pvo : prodTypeVOs) {
				if (pvo.getName().toLowerCase().startsWith(query)) {
					productHelper.add(pvo.getName());
				}
			}
			return productHelper;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addNewOrderButtonAction() {
		newOrder = new OrderVO();
		products = new ArrayList<PList>();
		quantity = 1;
	}

	public void addNewOrder() {
		//initOrder();
		newOrder.setStatus(selectedStatus);
		newOrder.setDate(new Date());

		for (int i = 0; i < products.size(); i++) {
			selectedProductTypeName = products.get(i).getName();
			quantity = products.get(i).getQuantity();

			if (selectedProductTypeName != null) {
				selectedProdType = new ProductTypeVO();
				for (ProductTypeVO ptvo : prodTypeVOs) {
					if (selectedProductTypeName.equals(ptvo.getName())) {
						selectedProdType = ptvo;
					}
				}
			}
			orderService.addProductTypeToOrder(selectedProdType, newOrder, quantity);
			selectedProductTypeName = null;
			orderService.saveOrder(newOrder);
		}
	}

	public void onRowSelected(SelectEvent event) {
		setSelectedOrderId(selectedOrder.getOrderId());

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOrder.getOrderId()));
	}

	public void addToList() {
		String id = newOrder.getOrderId();
		getProducts().add(new PList(id, selectedProductTypeName, quantity));
		//System.out.println(getProducts());
		selectedProductTypeName = "";
		quantity = 1;
	}
	
	//UJRA KELL GONDOLNI
//	public void initOrder(){
//		setInitOrderList(new ArrayList<PList>());
//		String id = newOrder.getOrderId();
//		System.out.println("Order ID" +id);
//		
//		try {
//			for (PList pList : all) {
//				if (pList.getId().equals(id)) {
//					initOrderList.add(new PList(id, pList.getName(), pList.getQuantity()));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

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

	public String[] getStatus() {
		return status;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
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

	public String getSelectedOrderId() {
		return selectedOrderId;
	}

	public void setSelectedOrderId(String selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<PList> getProducts() {
		return products;
	}

	public void setProducts(List<PList> products) {
		this.products = products;
	}
}
