package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.vo.OfferVO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "sellController")
public class SellController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private static OfferVO selectedOffer;
	
	private static OfferVO selectedAvailableOffer;
	
	private static List<OfferVO> offers;
	
	private static List<OfferWebVO> selectedOffers;
	
	private LazySellModel lazySellModel;

	@PostConstruct
	public void init() {

	}

	public void onRowSelect(SelectEvent event) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Idejön valami"));
	}



	public OfferVO getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(OfferVO selectedOffer) {
		SellController.selectedOffer = selectedOffer;
	}

	public List<OfferVO> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferVO> offers) {
		SellController.offers = offers;
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
	
	
}
