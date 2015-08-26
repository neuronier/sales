package hu.neuron.java.sales.web.controllers.offer;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.ProductTypeVO;
import hu.neuron.java.sales.web.controllers.producttype.LazyProductTypeModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "offerController")
public class OfferController implements Serializable {

	private static final long serialVersionUID = 8615063688727096926L;

	OfferVO selectedOffer;

	ProductTypeVO selectedProductType;
	
	OfferProductType selectedOfferProductType;

	List<ProductTypeVO> productTypeList;

	List<OfferProductType> selectedOfferProductTypeList = new ArrayList<OfferProductType>();
	
	List<ProductTypeVO> selectOneMenuOfferProductTypeList = new ArrayList<ProductTypeVO>();
	
	List<ProductTypeVO> existingPts = new ArrayList<ProductTypeVO>();
	
	List<ProductTypeVO> trashProductTypeList = new ArrayList<ProductTypeVO>();

	private String newOfferName;


	private long newOfferPrice;

	private String updateOfferName;

	private long updateOfferPrice;

	private String updateProductTypeId;

	private int newQuantity;

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	private ProductTypeServiceRemote productTypeService;

	private LazyOfferModel lazyOfferModel;

	private LazyProductTypeModel lazyProductTypeModel;
	
	public List<ProductTypeVO> getSelectOneMenuOfferProductTypeList() {
		return selectOneMenuOfferProductTypeList;
	}

	public void setSelectOneMenuOfferProductTypeList(
			List<ProductTypeVO> selectOneMenuOfferProductTypeList) {
		this.selectOneMenuOfferProductTypeList = selectOneMenuOfferProductTypeList;
	}

	public List<ProductTypeVO> getTrashProductTypeList() {
		return trashProductTypeList;
	}

	public void setTrashProductTypeList(List<ProductTypeVO> trashProductTypeList) {
		this.trashProductTypeList = trashProductTypeList;
	}

	public List<ProductTypeVO> getExistingPts() {
		return existingPts;
	}

	public void setExistingPts(List<ProductTypeVO> existingPts) {
		this.existingPts = existingPts;
	}

	public List<OfferProductType> getSelectedOfferProductTypeList() {
		return selectedOfferProductTypeList;
	}

	public void setSelectedOfferProductTypeList(
			List<OfferProductType> selectedOfferProductTypeList) {
		this.selectedOfferProductTypeList = selectedOfferProductTypeList;
	}

	public String getUpdateProductTypeId() {
		return updateProductTypeId;
	}

	public void setUpdateProductTypeId(String updateProductTypeId) {
		this.updateProductTypeId = updateProductTypeId;
	}

	public List<ProductTypeVO> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<ProductTypeVO> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public OfferProductType getSelectedOfferProductType() {
		return selectedOfferProductType;
	}

	public void setSelectedOfferProductType(
			OfferProductType selectedOfferProductType) {
		this.selectedOfferProductType = selectedOfferProductType;
	}

	public ProductTypeServiceRemote getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(
			ProductTypeServiceRemote productTypeService) {
		this.productTypeService = productTypeService;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
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

	public long getNewOfferPrice() {
		return newOfferPrice;
	}

	public void setNewOfferPrice(long newOfferPrice) {
		this.newOfferPrice = newOfferPrice;
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
		offerVO.setOfferPrice(newOfferPrice);
		offerService.saveOffer(offerVO);
		
		for (OfferProductType offerProductType : selectedOfferProductTypeList) {
			if(!(existingPts.contains(productTypeService.findProductTypeByName(offerProductType.getName()))))
			{
				offerService.addProductTypeToOffer(offerVO, productTypeService.findProductTypeByName(offerProductType.getName()), offerProductType.getQuantity());
			}
		}
	}

	public void onRowSelect(SelectEvent event) throws Exception {
		selectedOffer = (OfferVO) event.getObject();
		updateOfferName = selectedOffer.getName();
		updateOfferPrice = selectedOffer.getOfferPrice();
		
		getProductTypesToSelectedOffer();
		
		selectOneMenuOfferProductTypeList = productTypeList;
		
		for (OfferProductType offerProductType : selectedOfferProductTypeList) {
			selectOneMenuOfferProductTypeList.remove(productTypeService.findProductTypeByName(offerProductType.getName()));
		}	
		
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOffer.getName()));
	}

	public void onOfferProductTypeRowSelect(SelectEvent event) {
		selectedOfferProductType = (OfferProductType) event.getObject();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedOfferProductType.getName()));
	}

	public void removeOffer() {
		try {
			for (OfferProductType offerProductType : selectedOfferProductTypeList) {
				ProductTypeVO productType = productTypeService.findProductTypeByName(offerProductType.getName());
				offerService.removeProductTypeFromOffer(selectedOffer, productType);
			}
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
			selectedOffer.setOfferPrice(updateOfferPrice);
			offerService.saveOffer(selectedOffer);
			if(!(trashProductTypeList.isEmpty()))
			{
				for (ProductTypeVO productTypeVO : trashProductTypeList) {
					offerService.removeProductTypeFromOffer(selectedOffer, productTypeVO);
				}
			}
			trashProductTypeList.clear();
			existingPts = offerService.findProductTypesToOffer(selectedOffer);
			for (OfferProductType offerProductType : selectedOfferProductTypeList) {
				if(!(existingPts.contains(productTypeService.findProductTypeByName(offerProductType.getName()))))
				{
					offerService.addProductTypeToOffer(selectedOffer, productTypeService.findProductTypeByName(offerProductType.getName()), offerProductType.getQuantity());
				}
			}
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedOffer.getName()));
			selectedOffer = null;
			newQuantity = 0;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
	}

	public void addProductTypeToOffer(ActionEvent actionEvent) {
		selectedOfferProductTypeList.add(new OfferProductType(selectedProductType.getName(), newQuantity));
		selectOneMenuOfferProductTypeList.remove(selectedProductType);
	}

	public void removeProductTypeFromOffer(ActionEvent actionEvent) throws Exception {
		trashProductTypeList.add(productTypeService.findProductTypeByName(selectedOfferProductType.getName()));
		selectedOfferProductTypeList.remove(selectedOfferProductType);
		selectOneMenuOfferProductTypeList.add(productTypeService.findProductTypeByName(selectedOfferProductType.getName()));
	}
	
	public void getProductTypesToSelectedOffer(){
		selectedProductType = null;
		selectedOfferProductTypeList.clear();
		existingPts.clear();
		try {
			existingPts = offerService.findProductTypesToOffer(selectedOffer);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	
		for(ProductTypeVO productTypeVO : existingPts) {
			selectedOfferProductTypeList.add(new OfferProductType(productTypeVO.getName(),offerService.findQuantityToOfferProductType(selectedOffer, productTypeVO)));
		} 
	}
	
	public void saveSelectOneMenu(){
		newOfferName = null;
		newOfferPrice = 0;
		newQuantity = 0;
		selectedOfferProductTypeList.clear();
		productTypeList = productTypeService.findAll();
		selectOneMenuOfferProductTypeList = productTypeList;
	}
}