package hu.neuron.java.sales.web.controllers.offer;

import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "offerController")
public class OfferController implements Serializable{

	private static final long serialVersionUID = 8615063688727096926L;

	OfferVO selectedOffer;
	
	private String newOfferName;
	
	private String updateOfferName;
	
	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;
	
	private LazyOfferModel lazyOfferModel;
	
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
	}

	public void saveNewOffer() {
		OfferVO offerVO = new OfferVO();
		offerVO.setName(newOfferName);
		offerService.saveOffer(offerVO);
	}

	public void onRowSelect(SelectEvent event) {
		selectedOffer = (OfferVO) event.getObject();
		updateOfferName = selectedOffer.getName();
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
}