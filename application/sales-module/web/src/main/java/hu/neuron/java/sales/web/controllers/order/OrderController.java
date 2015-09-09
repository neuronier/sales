package hu.neuron.java.sales.web.controllers.order;

import hu.neuron.java.sales.service.InventoryWebClient;
import hu.neuron.java.sales.service.OrderServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.InventoryVO;
import hu.neuron.java.sales.service.vo.OrderVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.service.vo.UserVO;
import hu.neuron.java.sales.service.vo.WarehouseVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ViewScoped
@ManagedBean(name = "orderController")
public class OrderController implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyOrderModel lazyOrderModul;

	@EJB(name = "OrderService", mappedName = "OrderService")
	OrderServiceRemote orderService;

	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	SalesPointServiceRemote salePointService;

	@EJB(name = "UserService", mappedName = "UserService")
	UserServiceRemote userService;

	@EJB(name = "InventoryWebClient", mappedName = "InventoryWebClient")
	InventoryWebClient in;

	private OrderVO newOrder;

	private OrderVO selectedOrder;

	private String selectedOrderId;

	private List<ProductTypeVO> prodTypeVOs;

	private ProductTypeVO selectedProdType;

	private List<String> productHelper;

	private String selectedProductTypeName;

	private int quantity;

	private List<OrderProductType> products;

	private OrderProductType selectedProduct;

	private boolean disableSaveOrderValue;

	private boolean disableAddOrdeValue;

	private boolean disableCreateOrder;

	private boolean disableEditDeleteOrderValue;

	private SalesPointVO selectedSalePoint;

	private List<String> salePoints;

	private String selectedSalePointName;

	private final String new_order_status = "Új";

	private List<InventoryVO> inventory;

	@PostConstruct
	public void init() {
		initSalePoints();
		setLazyOrderModul(new LazyOrderModel(orderService));
	}

	public OrderController() {
		super();
	}

	public WarehouseVO getWarehouse() {
		WarehouseVO def = new WarehouseVO();
		def.setWarehouseId("none");
		def.setWarehouseName("none");
		try {
			User user = null;

			user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();

			UserVO userVO = userService.findUserByUserName(user.getUsername());

			SalesPointVO salepoint = userVO.getSalesPoint();

			WarehouseVO warehouse = salepoint.getWarehouse();
			return warehouse;

		} catch (Exception e) {
			return def;
		}

	}

	public List<String> completeTextProduct(String query) {
		prodTypeVOs = new ArrayList<ProductTypeVO>();
		productHelper = new ArrayList<String>();
		try {
			prodTypeVOs = productTypeService.findAll();
			// System.out.println("Product type SIZE" + prodTypeVOs.size());
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

	public void initSalePoints() {
		List<SalesPointVO> vos = new ArrayList<SalesPointVO>();

		vos = salePointService.findAll();
		salePoints = new ArrayList<String>();
		selectedSalePointName = null;

		for (SalesPointVO salesPointVO : vos) {
			salePoints.add(salesPointVO.getName());
		}

		if (getWarehouse().getWarehouseName().equals("none")) {
			setDisableCreateOrder(true);
		}
	}

	public void addNewOrderButtonAction() {
		newOrder = new OrderVO();
		products = new ArrayList<OrderProductType>();
		disableSaveOrderValue = true;
		disableAddOrdeValue = true;
		quantity = 1;
	}

	public void editOrderButtonAction() {
		products = new ArrayList<OrderProductType>();
		selectedProductTypeName = null;
		quantity = 1;

		List<ProductTypeVO> vos = new ArrayList<ProductTypeVO>();
		try {
			vos = orderService.findProductTypesToOrder(selectedOrder);
			for (ProductTypeVO productTypeVO : vos) {
				int q = orderService.findQuantityToOrderProductType(
						productTypeVO, selectedOrder);
				products.add(new OrderProductType(productTypeVO.getName(), q));
			}
			System.out.println(products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNewOrder() {
		newOrder.setStatus(new_order_status);
		newOrder.setDate(new Date());

		newOrder.setWarehouse(getWarehouse());

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
			orderService.addProductTypeToOrder(selectedProdType, newOrder,
					quantity);
			selectedProductTypeName = null;
		}
		orderService.saveOrder(newOrder);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("order_created", context) + " \n "
						+ newOrder.getOrderId());
		context.addMessage(null, msg);
	}

	// FEJESZTÉS ALATT
	public void editOrder() {

		try {
			List<ProductTypeVO> vos = orderService
					.findProductTypesToOrder(selectedOrder);
			for (ProductTypeVO productTypeVO : vos) {
				orderService.removeProductTypeFromOrder(productTypeVO,
						selectedOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			orderService.addProductTypeToOrder(selectedProdType, selectedOrder,
					quantity);
			selectedProductTypeName = null;
		}

		orderService.updateOrder(selectedOrder);

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("order_edited", context) + " \n "
						+ selectedOrder.getOrderId());
		context.addMessage(null, msg);
		selectedOrder = null;
	}

	public void removeOrder() {
		orderService.removeOrder(selectedOrder);
		try {
			List<ProductTypeVO> vos = orderService
					.findProductTypesToOrder(selectedOrder);
			for (ProductTypeVO productTypeVO : vos) {
				orderService.removeProductTypeFromOrder(productTypeVO,
						selectedOrder);
			}

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					LocalizationsUtils.getText("user_info", context),
					LocalizationsUtils.getText("order_deleted", context)
							+ " \n " + selectedOrder.getOrderId());
			context.addMessage(null, msg);
			selectedOrder = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onRowSelected(SelectEvent event) {
		disableEditDeleteOrder();
		setSelectedOrderId(selectedOrder.getOrderId());

		System.out.println("aki be van jelentkezve : "
				+ getWarehouse().getWarehouseName());
		System.out.println("selected : "
				+ selectedOrder.getWarehouse().getWarehouseName());

		if (!(getWarehouse().getWarehouseName().equals(selectedOrder
				.getWarehouse().getWarehouseName()))) {
			setDisableEditDeleteOrderValue(true);

		}
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("order_selected", context)
						+ selectedOrder.getOrderId());
		context.addMessage(null, msg);
	}

	public void addToList() {
		if (selectedProductTypeName == null) {
		} else {

			if (!productHelper.contains(selectedProductTypeName)) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						LocalizationsUtils.getText("error", context),
						LocalizationsUtils.getText("orders_beer_not_exists",
								context) + "\n" + selectedProductTypeName);
				context.addMessage(null, msg);
			} else {
				if (quantity <= 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							LocalizationsUtils.getText("error", context),
							LocalizationsUtils.getText("order_quantity_error",
									context));
					context.addMessage(null, msg);
				} else {
					boolean helper = false;
					int index = 0;
					for (OrderProductType opt : products) {
						if (opt.getName().equals(selectedProductTypeName)) {
							helper = true;
							index = products.indexOf(opt);
						}
					}

					if (helper) {
						products.get(index).setQuantity(
								products.get(index).getQuantity() + quantity);
					} else {
						getProducts().add(
								new OrderProductType(selectedProductTypeName,
										quantity));
					}

					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage msg = new FacesMessage(
							FacesMessage.SEVERITY_INFO,
							LocalizationsUtils.getText("user_info", context),
							LocalizationsUtils.getText("order_added_info",
									context)
									+ "\n"
									+ selectedProductTypeName
									+ " "
									+ quantity
									+ " "
									+ LocalizationsUtils.getText("orders_q",
											context));
					context.addMessage(null, msg);

					System.out.println(getProducts());
					selectedProductTypeName = null;
					quantity = 1;
					disableSaveOrder();
				}
			}
		}
	}

	public void removeFromList() {
		products.remove(selectedProduct);
		System.out.println(products);

		quantity = selectedProduct.getQuantity();
		selectedProductTypeName = selectedProduct.getName();

		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				LocalizationsUtils.getText("user_info", context),
				LocalizationsUtils.getText("orders_delete_info", context)
						+ "\n" + selectedProductTypeName + " " + quantity + " "
						+ LocalizationsUtils.getText("orders_q", context));
		context.addMessage(null, msg);
		disableSaveOrder();
		selectedProduct = null;
		selectedProductTypeName = null;
		quantity = 1;
	}

	public void disableSaveOrder() {
		if (products.size() == 0) {
			disableSaveOrderValue = true;
		} else {
			disableSaveOrderValue = false;
		}
	}

	public void disableEditDeleteOrder() {

		if (selectedOrder.getStatus().equals(new_order_status)) {
			setDisableEditDeleteOrderValue(false);
		}
		// else if (selectedOrder.getWarehouse().getWarehouseName()
		// .equals(vo.getWarehouseName())) {
		//
		// setDisableEditDeleteOrderValue(false);
		// }
		else {
			setDisableEditDeleteOrderValue(true);
		}
	}

	public void handleToggle(ToggleEvent event) {
	}

	public void loadWarehouseButtonAction(ActionEvent actionEvent) {
		SalesPointVO vo = new SalesPointVO();
		inventory = new ArrayList<InventoryVO>();
		try {
			if (selectedSalePointName != null) {
				vo = salePointService
						.findSalePointByName(selectedSalePointName);

				inventory = in.refreshInventory(vo.getWarehouse()
						.getWarehouseId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disableAddOrder() {
		setDisableAddOrdeValue(false);
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

	public List<OrderProductType> getProducts() {
		return products;
	}

	public void setProducts(List<OrderProductType> products) {
		this.products = products;
	}

	public OrderProductType getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(OrderProductType selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public boolean isDisableSaveOrderValue() {
		return disableSaveOrderValue;
	}

	public void setDisableSaveOrderValue(boolean disableSaveOrderValue) {
		this.disableSaveOrderValue = disableSaveOrderValue;
	}

	public boolean isDisableAddOrdeValue() {
		return disableAddOrdeValue;
	}

	public void setDisableAddOrdeValue(boolean disableAddOrdeValue) {
		this.disableAddOrdeValue = disableAddOrdeValue;
	}

	public SalesPointVO getSelectedSalePoint() {
		return selectedSalePoint;
	}

	public void setSelectedSalePoint(SalesPointVO selectedSalePoint) {
		this.selectedSalePoint = selectedSalePoint;
	}

	public List<String> getSalePoints() {
		return salePoints;
	}

	public void setSalePoints(List<String> salePoints) {
		this.salePoints = salePoints;
	}

	public String getSelectedSalePointName() {
		return selectedSalePointName;
	}

	public void setSelectedSalePointName(String selectedSalePointName) {
		this.selectedSalePointName = selectedSalePointName;
	}

	public boolean isDisableEditDeleteOrderValue() {
		return disableEditDeleteOrderValue;
	}

	public void setDisableEditDeleteOrderValue(
			boolean disableEditDeleteOrderValue) {
		this.disableEditDeleteOrderValue = disableEditDeleteOrderValue;
	}

	public boolean isDisableCreateOrder() {
		return disableCreateOrder;
	}

	public void setDisableCreateOrder(boolean disableCreateOrder) {
		this.disableCreateOrder = disableCreateOrder;
	}

	public List<InventoryVO> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryVO> inventory) {
		this.inventory = inventory;
	}
}
