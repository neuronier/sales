package hu.neuron.java.sales.web.controllers.report;

import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

@ViewScoped
@ManagedBean(name = "reportController")
public class ReportController {

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;
	
	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;

	private Date reportFrom;
	private Date reportTo;

	private List<AdminRegNumberElement> adminUserRegNumberDetailsList;
	private List<AdminRegNumberElement> adminClientRegNumberDetailsList;

	private LineChartModel adminUserNumberLineChart;
	private LineChartModel adminClientNumberLineChart;

	@PostConstruct
	public void init() {
		Calendar cal = Calendar.getInstance();

		cal.set(2015, 1, 1);
		reportFrom = cal.getTime();

		cal.set(2016, 5, 1);
		reportTo = cal.getTime();

		adminUserRegNumberDetailsList = new ArrayList<>();
		adminClientRegNumberDetailsList = new ArrayList<>();
	}

	public void generateAdminUserNumberDetails(Date start, Date end) {

		adminUserRegNumberDetailsList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			currentCalendar.set(Calendar.DAY_OF_MONTH, 1);

			AdminRegNumberElement element = new AdminRegNumberElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(userService.findNumberOfUsersBeforeDate(currentCalendar.getTime()));

			adminUserRegNumberDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateAdminUserNumberDiagramm() {
		adminUserNumberLineChart = new LineChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel("Number of registrated users");
		for (AdminRegNumberElement element : adminUserRegNumberDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminUserNumberLineChart.addSeries(number);
		adminUserNumberLineChart.setLegendPosition("e");
		adminUserNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%b");

		adminUserNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminUserNumberLineChart.getAxis(AxisType.Y);
		yAxis.setLabel("Number");
		yAxis.setMin(0);

	}

	public void adminUserNumGenerate() {
		generateAdminUserNumberDetails(reportFrom, reportTo);
		generateAdminUserNumberDiagramm();
	}
	
	public void generateAdminClientNumberDetails(Date start, Date end) {

		adminClientRegNumberDetailsList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			currentCalendar.set(Calendar.DAY_OF_MONTH, 1);

			AdminRegNumberElement element = new AdminRegNumberElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(clientService.findNumberOfClientsBeforeDate(currentCalendar.getTime()));

			adminClientRegNumberDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateAdminClientNumberDiagramm() {
		adminClientNumberLineChart = new LineChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel("Number of registrated clients.");
		for (AdminRegNumberElement element : adminClientRegNumberDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminClientNumberLineChart.addSeries(number);
		adminClientNumberLineChart.setLegendPosition("e");
		adminClientNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%b");

		adminClientNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminClientNumberLineChart.getAxis(AxisType.Y);
		yAxis.setLabel("Number");
		yAxis.setMin(0);

	}

	public void adminClientNumGenerate() {
		generateAdminClientNumberDetails(reportFrom, reportTo);
		generateAdminClientNumberDiagramm();
	}

	// ////////////////////////////////////////////////////////////////////////
	// --------------------------Event listeners-----------------------------//
	// ////////////////////////////////////////////////////////////////////////

	public void adminUserNumGenerateBtnAction() {
		adminUserNumGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerTabView:adminUserNumber");
	}
	
	public void adminClientNumGenerateBtnAction() {
		adminClientNumGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerTabView:adminClientNumber");
	}
	
	public void productReportGenerateBtnAction() {
		adminClientNumGenerate();
	}

	public void onTabChange(TabChangeEvent event) {
		String id = event.getTab().getId();
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + id);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		switch (id) {
		case "tabSales":
			break;
		case "tabSalesPoint":
			break;
		case "tabAdministration":
			adminUserNumGenerateBtnAction();
			break;
		}

	}
	
	public void salesOnTabChange(TabChangeEvent event) {
		switch (event.getTab().getId()) {
		case "product":
			
			break;
		case "income":
			
			break;
		case "topOffers":
			break;
		}
	}

	public void adminOnTabChange(TabChangeEvent event) {
		switch (event.getTab().getId()) {
		case "adminUserNumber":
			adminUserNumGenerateBtnAction();
			break;
		case "adminClientNumber":
			adminClientNumGenerateBtnAction();
			break;
		case "adminIssue":
			break;
		}
	}

	// ////////////////////////////////////////////////////////////////////////
	// --------------------------Getters & Setters---------------------------//
	// ////////////////////////////////////////////////////////////////////////

	public Date getReportFrom() {
		return reportFrom;
	}

	public void setReportFrom(Date reportFrom) {
		this.reportFrom = reportFrom;
	}

	public Date getReportTo() {
		return reportTo;
	}

	public void setReportTo(Date reportTo) {
		this.reportTo = reportTo;
	}

	public List<AdminRegNumberElement> getAdminUserRegNumberDetailsList() {
		return adminUserRegNumberDetailsList;
	}

	public void setAdminUserRegNumberDetailsList(List<AdminRegNumberElement> adminUserRegNumberDetailsList) {
		this.adminUserRegNumberDetailsList = adminUserRegNumberDetailsList;
	}

	public LineChartModel getAdminUserNumberLineChart() {
		return adminUserNumberLineChart;
	}

	public void setAdminUserNumberLineChart(LineChartModel adminUserNumberLineChart) {
		this.adminUserNumberLineChart = adminUserNumberLineChart;
	}

	public List<AdminRegNumberElement> getAdminClientRegNumberDetailsList() {
		return adminClientRegNumberDetailsList;
	}

	public void setAdminClientRegNumberDetailsList(List<AdminRegNumberElement> adminClientRegNumberDetailsList) {
		this.adminClientRegNumberDetailsList = adminClientRegNumberDetailsList;
	}

	public LineChartModel getAdminClientNumberLineChart() {
		return adminClientNumberLineChart;
	}

	public void setAdminClientNumberLineChart(LineChartModel adminClientNumberLineChart) {
		this.adminClientNumberLineChart = adminClientNumberLineChart;
	}
	
}
