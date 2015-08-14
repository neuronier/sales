package hu.neuron.java.sales.web.controllers.offer;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.web.controllers.producttype.LazyProductTypeModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "offerController")
public class OfferController implements Serializable {

	private static final long serialVersionUID = 8615063688727096926L;

	OfferVO selectedOffer;

	ProductTypeVO selectedProductType;

	ProductTypeVO selectedOfferProductType;

	List<ProductTypeVO> updateOfferProductTypeList;

	List<ProductTypeVO> newOfferProductTypeList;
	
	ProductTypeVO productTypeVO;
	
	List<ProductTypeVO> productTypeList;
	
	HashMap<String, Integer> offerProductTypeList;

	private String newOfferName;

	private long newOfferId;

	private long newOfferPrice;

	private String updateOfferName;

	private long updateOfferId;

	private long updateOfferPrice;

	private long updateProductTypeId;

	private int newQuantity;

	private int updateQuantity;

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	private ProductTypeServiceRemote productTypeService;

	private LazyOfferModel lazyOfferModel;

	private LazyProductTypeModel lazyProductTypeModel;

	
	
	public ProductTypeVO getProductTypeVO() {
		return productTypeVO;
	}

	public void setProductTypeVO(ProductTypeVO productTypeVO) {
		this.productTypeVO = productTypeVO;
	}

	public List<ProductTypeVO> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<ProductTypeVO> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public HashMap<String, Integer> getOfferProductTypeList() {
		return offerProductTypeList;
	}

	public void setOfferProductTypeList(
			HashMap<String, Integer> offerProductTypeList) {
		this.offerProductTypeList = offerProductTypeList;
	}

	public ProductTypeVO getSelectedOfferProductType() {
		return selectedOfferProductType;
	}

	public void setSelectedOfferProductType(
			ProductTypeVO selectedOfferProductType) {
		this.selectedOfferProductType = selectedOfferProductType;
	}

	public List<ProductTypeVO> getUpdateOfferProductTypeList() {
		return updateOfferProductTypeList;
	}

	public void setUpdateOfferProductTypeList(
			List<ProductTypeVO> updateOfferProductTypeList) {
		this.updateOfferProductTypeList = updateOfferProductTypeList;
	}

	public List<ProductTypeVO> getNewOfferProductTypeList() {
		return newOfferProductTypeList;
	}

	public void setNewOfferProductTypeList(
			List<ProductTypeVO> newOfferProductTypeList) {
		this.newOfferProductTypeList = newOfferProductTypeList;
	}

	public ProductTypeServiceRemote getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(
			ProductTypeServiceRemote productTypeService) {
		this.productTypeService = productTypeService;
	}

	public long getUpdateProductTypeId() {
		return updateProductTypeId;
	}

	public void setUpdateProductTypeId(long updateProductTypeId) {
		this.updateProductTypeId = updateProductTypeId;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}

	public int getUpdateQuantity() {
		return updateQuantity;
	}

	public void setUpdateQuantity(int updateQuantity) {
		this.updateQuantity = updateQuantity;
	}

	public ProductTypeVO getSelectedProductType() {
		return selectedProductType;
	}

	public void setSelectedProductType(ProductTypeVO selectedProductType) {
		this.selectedProductType = selectedProductType;
	}

	public LazyProductTypeModel getLazyProductTypeModel() {
		return lazyProductTypeModel;
	}

	public void setLazyProductTypeModel(
			LazyProductTypeModel lazyProductTypeModel) {
		this.lazyProductTypeModel = lazyProductTypeModel;
	}

	public long getNewOfferId() {
		return newOfferId;
	}

	public void setNewOfferId(long newOfferId) {
		this.newOfferId = newOfferId;
	}

	public long getNewOfferPrice() {
		return newOfferPrice;
	}

	public void setNewOfferPrice(long newOfferPrice) {
		this.newOfferPrice = newOfferPrice;
	}

	public long getUpdateOfferId() {
		return updateOfferId;
	}

	public void setUpdateOfferId(long updateOfferId) {
		this.updateOfferId = updateOfferId;
	}

	public long getUpdateOfferPrice() {
		return updateOfferPrice;
	}

	public void setUpdateOfferPrice(long updateOfferPrice) {
		this.updateOfferPrice = updateOfferPrice;
	}

	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public String getNewOfferName() {
		return newOfferName;
	}

	public void setNewOfferName(String newOfferName) {
		this.newOfferName = newOfferName;
	}

	public String getUpdateOfferName() {
		return updateOfferName;
	}

	public void setUpdateOfferName(String updateOfferName) {
		this.updateOfferName = updateOfferName;
	}

	public OfferServiceRemote getOfferService() {
		return offerService;
	}

	public void setOfferService(OfferServiceRemote offerService) {
		this.offerService = offerService;
	}

	public LazyOfferModel getLazyOfferModel() {
		return lazyOfferModel;
	}

	public void setLazyOfferModel(LazyOfferModel lazyOfferModel) {
		this.lazyOfferModel = lazyOfferModel;
	}

	@PostConstruct
	public void init() {
		setLazyOfferModel(new LazyOfferModel(offerService));
		setLazyProductTypeModel(new LazyProductTypeModel(productTypeService));
		productTypeList = productTypeService.findAll();	
	}

	public void saveNewOffer() throws Exception {
		OfferVO offerVO = new OfferVO();
		offerVO.setName(newOfferName);
		offerVO.setOfferId(newOfferId);
		offerVO.setOfferPrice(newOfferPrice);
		offerService.saveOffer(offerVO);
		for (Map.Entry<String, Integer> entry : offerProductTypeList.entrySet()) {
			offerService.addProductTypeToOffer(offerVO, productTypeService.findProductTypeByName(entry.getKey()), entry.getValue());		
		}
	}

	public void onRowSelect(SelectEvent event) throws Exception {
		selectedOffer = (OfferVO) event.getObject();
		updateOfferName = selectedOffer.getName();
		updateOfferId = selectedOffer.getOfferId();
		updateOfferPrice = selectedOffer.getOfferPrice();
		updateOfferProductTypeList = offerService
				.findProductTypesToOffer(selectedOffer);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOffer.getName()));
	}

	public void onProductTypeRowSelect(SelectEvent event) {
		selectedProductType = (ProductTypeVO) event.getObject();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOffer.getName()));
	}

	public void onOfferProductTypeRowSelect(SelectEvent event) {
		selectedOfferProductType = (ProductTypeVO) event.getObject();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOffer.getName()));
	}

	public void removeOffer() {
		try {
			offerService.removeOffer(selectedOffer);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Deleted: " + selectedOffer.getName()));
			selectedOffer = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Deleted: "));
		}
	}

	public void updateOffer() {
		try {
			selectedOffer.setName(updateOfferName);
			selectedOffer.setOfferId(updateOfferId);
			selectedOffer.setOfferPrice(updateOfferPrice);
			offerService.saveOffer(selectedOffer);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedOffer.getName()));
			selectedOffer = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
	}
	
	public void addProductTypeToOffer() {
		offerProductTypeList.put(selectedProductType.getName(),newQuantity);
	}

	public void removeProductTypeFromOffer() {
		offerProductTypeList.remove(selectedOfferProductType.getName());
	}
}