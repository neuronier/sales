package hu.neuron.java.sales.web.controllers.sell;

import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.vo.ClientOfferVO;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.web.autocomplete.OfferAutoCompleteView;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "sellController")
public class SellController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private static OfferWebVO selectedWebOffer;
	
	private static OfferVO selectedAvailableOffer;
	
	private static List<OfferWebVO> selectedOffers;
	
	private Long total;
	
	private LazySellModel lazySellModel;
	
	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;
	
	@EJB(name = "ClientOfferService", mappedName = "ClientOfferService")
	private ClientOfferServiceRemote clientOfferService;

	@PostConstruct
	public void init() {
		setLazySellModel(new LazySellModel(offerService));
		selectedOffers = new LinkedList<>();
	}

	public void onRowSelect(SelectEvent event) {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Idejön valami"));
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
	
	public void addToSelectedOffers(){
		boolean contains = false;
		if(selectedOffers != null){
			for(OfferWebVO owv : selectedOffers){
				if(owv != null && selectedAvailableOffer != null){
					if(owv.getOfferId().equals(selectedAvailableOffer.getOfferId())){
						contains = true;
					}
				}
			}
		}
		if(!contains){
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
	
	public void sell(){
		Date now = new Date(System.currentTimeMillis());
		if(OfferAutoCompleteView.getStaticSelectedClient()!= null && selectedOffers.size() > 0){
			for(OfferWebVO owv : selectedOffers){
				ClientOfferVO purchase = new ClientOfferVO();
				purchase.setClient(OfferAutoCompleteView.getStaticSelectedClient());
				purchase.setOffer(owv.getOfferVo());
				purchase.setQuantity(owv.getQuantity());
				purchase.setDate(now);
				purchase.createId();
				try {
					clientOfferService.saveClientOffer(purchase);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		selectedOffers.clear();
		OfferAutoCompleteView.setStaticSelectedClient(null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_INFO, "Success", "The system processed the sale"));
	}

	public Long getTotal() {
		total = 0L;
		for(OfferWebVO owv : selectedOffers){
			total += owv.getOfferPrice() * owv.getQuantity();
		}
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
