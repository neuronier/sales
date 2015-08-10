package hu.neuron.java.sales.web.controllers.salespoint;

import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "salesPointController")
public class SalesPointController implements Serializable {

	private static final long serialVersionUID = 2441763337964957672L;

	private SalesPointVO selectedSalesPoint;

	private String newSalesPointName;

	private String updateSalesPointName;

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote salesPointService;

	private LazySalesPointModel lazySalesPointModel;

	@PostConstruct
	public void init() {
		setLazySalesPointModel(new LazySalesPointModel(salesPointService));
	}

	public void saveNewSalesPoint() {
		SalesPointVO salesPointVO = new SalesPointVO();
		salesPointVO.setName(newSalesPointName);
		salesPointService.saveSalePoint(salesPointVO);

	}

	public void onRowSelect(SelectEvent event) {
		selectedSalesPoint = (SalesPointVO) event.getObject();
		updateSalesPointName = selectedSalesPoint.getName();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						selectedSalesPoint.getName()));
	}

	public void removeSalesPoint() {
		try {
			salesPointService.removeSalePoint(selectedSalesPoint);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Deleted: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Deleted: "));
		}
	}

	public void updateSalesPoint() {
		try {
			selectedSalesPoint.setName(updateSalesPointName);
			salesPointService.saveSalePoint(selectedSalesPoint);

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Update: " + selectedSalesPoint.getName()));
			selectedSalesPoint = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Update: "));
		}
	}

	public LazySalesPointModel getLazySalesPointModel() {
		return lazySalesPointModel;
	}

	public void setLazySalesPointModel(LazySalesPointModel lazySalesPointModel) {
		this.lazySalesPointModel = lazySalesPointModel;
	}

	public SalesPointVO getSelectedSalesPoint() {
		return selectedSalesPoint;
	}

	public void setSelectedSalesPoint(SalesPointVO selectedSalesPoint) {
		this.selectedSalesPoint = selectedSalesPoint;
	}

	public String getNewSalesPointName() {
		return newSalesPointName;
	}

	public void setNewSalesPointName(String newSalesPointName) {
		this.newSalesPointName = newSalesPointName;
	}

	public String getUpdateSalesPointName() {
		return updateSalesPointName;
	}

	public void setUpdateSalesPointName(String updateSalesPointName) {
		this.updateSalesPointName = updateSalesPointName;
	}

}
