package hu.neuron.java.sales.web.controllers.report;

import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;

@ViewScoped
@ManagedBean(name = "reportController")
public class ReportController {

	@EJB(name = "ClientOfferService", mappedName = "ClientOfferService")
	private ClientOfferServiceRemote clientOfferService;

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;

	@EJB(name = "ClientService", mappedName = "ClientService")
	private ClientServiceRemote clientService;

	@EJB(name = "OfferService", mappedName = "OfferService")
	private OfferServiceRemote offerService;

	private Date reportFrom;
	private Date reportTo;

	private List<ReportElement> monthlySalesDetailsList;
	private List<ReportElement> monthlyIncomeDetailsList;
	private List<ReportElement> topOffersDetailsList;
	private List<ReportElement> adminUserRegNumberDetailsList;
	private List<ReportElement> adminClientRegNumberDetailsList;

	private BarChartModel monthlySalesBarChart;
	private BarChartModel monthlyIncomeBarChart;
	private DonutChartModel topOffersPieChart;
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
		monthlySalesDetailsList = new ArrayList<>();

		MonthlySalesReportGenerate();
	}

	/*********************************************************************************/
	/****************************** Monthly Sales **************************************/
	/*********************************************************************************/
	public void generateMonthlySalesDetails(Date start, Date end) {

		monthlySalesDetailsList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			ReportElement element = new ReportElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(clientOfferService.findCountByMonth(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH)));

			monthlySalesDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateMonthlySalesDiagramms() {
		monthlySalesBarChart = new BarChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_sold_product", FacesContext.getCurrentInstance()));
		for (ReportElement element : monthlySalesDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM").format(element.getDate());
			number.set(date, element.getNumber());
		}

		monthlySalesBarChart.addSeries(number);
		monthlySalesBarChart.setLegendPosition("e");
		monthlySalesBarChart.setShowPointLabels(true);

		Axis xAxis = monthlySalesBarChart.getAxis(AxisType.X);
		xAxis.setLabel(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		xAxis.setTickAngle(-50);

		Axis yAxis = monthlySalesBarChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}

	public void MonthlySalesReportGenerate() {
		generateMonthlySalesDetails(reportFrom, reportTo);
		generateMonthlySalesDiagramms();
	}

	/*********************************************************************************/
	/****************************** Monthly Income *************************************/
	/*********************************************************************************/
	public void generateMonthlyIncomeDetails(Date start, Date end) throws Exception {

		monthlyIncomeDetailsList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			ReportElement element = new ReportElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(clientOfferService.findIncomeByMonth(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH)));

			monthlyIncomeDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateMonthlyIncomeDiagramms() {
		monthlyIncomeBarChart = new BarChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()));
		for (ReportElement element : monthlyIncomeDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM").format(element.getDate());
			number.set(date, element.getNumber());
		}

		monthlyIncomeBarChart.addSeries(number);
		monthlyIncomeBarChart.setLegendPosition("e");
		monthlyIncomeBarChart.setShowPointLabels(true);

		Axis xAxis = monthlyIncomeBarChart.getAxis(AxisType.X);
		xAxis.setLabel(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		xAxis.setTickAngle(-50);

		Axis yAxis = monthlyIncomeBarChart.getAxis(AxisType.Y);
		yAxis.setLabel("HUF");
		yAxis.setMin(0);

	}

	public void MonthlyIncomeReportGenerate() {
		try {
			generateMonthlyIncomeDetails(reportFrom, reportTo);
			generateMonthlyIncomeDiagramms();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*********************************************************************************/
	/******************************** Top Offers *************************************/
	/*********************************************************************************/
	public void generateTopOffersDetails() throws Exception {
		int sumNumber = 0;
		int sumValue = 0;
		
		topOffersDetailsList = new ArrayList<>();

		List<OfferVO> offers = offerService.findTopOffers(9);

		for (OfferVO offer : offers) {
			ReportElement element = new ReportElement();
			element.setName(offer.getName());

			int findCountByOfferId = clientOfferService.findCountByOfferId(offer.getOfferId());
			int value = (int)(offerService.findOfferEntityByOfferId(offer.getOfferId()).getOfferPrice());
			sumNumber += findCountByOfferId;
			sumValue += value;
			
			element.setNumber(findCountByOfferId);
			
			element.setValue(findCountByOfferId * value);
			topOffersDetailsList.add(element);
		}

		int othersNumber = clientOfferService.count() - sumNumber;
		int othersIncome = clientOfferService.findTotalIncome() - sumValue;
		
		ReportElement element = new ReportElement();
		element.setName(LocalizationsUtils.getText("report_others", FacesContext.getCurrentInstance()));
		element.setNumber(othersNumber);
		element.setValue(othersIncome);

		topOffersDetailsList.add(element);
	}

	public void generateTopOffersDiagramms() {
		topOffersPieChart = new DonutChartModel();

		Map<String, Number> orderNum = new LinkedHashMap<String, Number>();
		Map<String, Number> orderIncome = new LinkedHashMap<String, Number>();
        
		for (ReportElement element : topOffersDetailsList) {
			orderNum.put(element.getName(), element.getNumber());
			orderIncome.put(element.getName(), element.getValue());
		}

		topOffersPieChart.addCircle(orderIncome);
		topOffersPieChart.addCircle(orderNum);
		
		topOffersPieChart.setLegendPosition("e");
		topOffersPieChart.setShowDataLabels(true);
		topOffersPieChart.setSliceMargin(1);
		topOffersPieChart.setDataFormat("value");

	}

	public void topOffersReportGenerate() {

		try {
			generateTopOffersDetails();
			generateTopOffersDiagramms();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*********************************************************************************/
	/****************************** Registrated users **********************************/
	/*********************************************************************************/
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

			ReportElement element = new ReportElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(userService.findNumberOfUsersBeforeDate(currentCalendar.getTime()));

			adminUserRegNumberDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateAdminUserNumberDiagramm() {
		adminUserNumberLineChart = new LineChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_users", FacesContext.getCurrentInstance()));
		for (ReportElement element : adminUserRegNumberDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminUserNumberLineChart.addSeries(number);
		adminUserNumberLineChart.setLegendPosition("e");
		adminUserNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%b");

		adminUserNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminUserNumberLineChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}

	public void adminUserNumGenerate() {
		generateAdminUserNumberDetails(reportFrom, reportTo);
		generateAdminUserNumberDiagramm();
	}

	/*********************************************************************************/
	/**************************** Registrated clients **********************************/
	/*********************************************************************************/
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

			ReportElement element = new ReportElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(clientService.findNumberOfClientsBeforeDate(currentCalendar.getTime()));

			adminClientRegNumberDetailsList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}

	public void generateAdminClientNumberDiagramm() {
		adminClientNumberLineChart = new LineChartModel();
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_clients", FacesContext.getCurrentInstance()));
		for (ReportElement element : adminClientRegNumberDetailsList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminClientNumberLineChart.addSeries(number);
		adminClientNumberLineChart.setLegendPosition("e");
		adminClientNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%b");

		adminClientNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminClientNumberLineChart.getAxis(AxisType.Y);
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
		MonthlySalesReportGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerSalesTabView:monthlyProductSales");
	}

	public void incomeReportGenerateBtnAction() {
		MonthlyIncomeReportGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerSalesTabView:monthlyIncome");
	}

	public void onTabChange(TabChangeEvent event) {
		switch (event.getTab().getId()) {
		case "monthlyProductSales":
			productReportGenerateBtnAction();
			break;
		case "monthlyIncome":
			incomeReportGenerateBtnAction();
			break;
		case "topOffers":
			topOffersReportGenerate();
			break;
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

	public List<ReportElement> getAdminUserRegNumberDetailsList() {
		return adminUserRegNumberDetailsList;
	}

	public void setAdminUserRegNumberDetailsList(List<ReportElement> adminUserRegNumberDetailsList) {
		this.adminUserRegNumberDetailsList = adminUserRegNumberDetailsList;
	}

	public LineChartModel getAdminUserNumberLineChart() {
		return adminUserNumberLineChart;
	}

	public void setAdminUserNumberLineChart(LineChartModel adminUserNumberLineChart) {
		this.adminUserNumberLineChart = adminUserNumberLineChart;
	}

	public List<ReportElement> getAdminClientRegNumberDetailsList() {
		return adminClientRegNumberDetailsList;
	}

	public void setAdminClientRegNumberDetailsList(List<ReportElement> adminClientRegNumberDetailsList) {
		this.adminClientRegNumberDetailsList = adminClientRegNumberDetailsList;
	}

	public LineChartModel getAdminClientNumberLineChart() {
		return adminClientNumberLineChart;
	}

	public void setAdminClientNumberLineChart(LineChartModel adminClientNumberLineChart) {
		this.adminClientNumberLineChart = adminClientNumberLineChart;
	}

	public List<ReportElement> getMonthlySalesDetailsList() {
		return monthlySalesDetailsList;
	}

	public void setMonthlySalesDetailsList(List<ReportElement> monthlySalesDetailsList) {
		this.monthlySalesDetailsList = monthlySalesDetailsList;
	}

	public BarChartModel getMonthlySalesBarChart() {
		return monthlySalesBarChart;
	}

	public void setMonthlySalesBarChart(BarChartModel monthlySalesBarChart) {
		this.monthlySalesBarChart = monthlySalesBarChart;
	}

	public List<ReportElement> getMonthlyIncomeDetailsList() {
		return monthlyIncomeDetailsList;
	}

	public void setMonthlyIncomeDetailsList(List<ReportElement> monthlyIncomeDetailsList) {
		this.monthlyIncomeDetailsList = monthlyIncomeDetailsList;
	}

	public BarChartModel getMonthlyIncomeBarChart() {
		return monthlyIncomeBarChart;
	}

	public void setMonthlyIncomeBarChart(BarChartModel monthlyIncomeBarChart) {
		this.monthlyIncomeBarChart = monthlyIncomeBarChart;
	}

	public List<ReportElement> getTopOffersDetailsList() {
		return topOffersDetailsList;
	}

	public void setTopOffersDetailsList(List<ReportElement> topOffersDetailsList) {
		this.topOffersDetailsList = topOffersDetailsList;
	}

	public DonutChartModel getTopOffersPieChart() {
		return topOffersPieChart;
	}

	public void setTopOffersPieChart(DonutChartModel topOffersPieChart) {
		this.topOffersPieChart = topOffersPieChart;
	}

}
