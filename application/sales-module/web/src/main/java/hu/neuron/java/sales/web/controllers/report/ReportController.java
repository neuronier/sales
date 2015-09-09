package hu.neuron.java.sales.web.controllers.report;

import hu.neuron.java.sales.service.ClientOfferServiceRemote;
import hu.neuron.java.sales.service.ClientServiceRemote;
import hu.neuron.java.sales.service.OfferServiceRemote;
import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.UserServiceRemote;
import hu.neuron.java.sales.service.vo.OfferVO;
import hu.neuron.java.sales.service.vo.SalesPointVO;
import hu.neuron.java.sales.web.LocalizationsUtils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	private SalesPointServiceRemote salePointService;

	private SalesPointVO selectedSalesPoint1;
	private SalesPointVO selectedSalesPoint2;
	private List<SalesPointVO> salesPointList;
	private int yearStart;
	private int monthStart;
	private int yearEnd;
	private int monthEnd;
	private String calibration;

	private Map<String, Integer> monthsList;

	private List<ReportElement> monthlySalesDetailsList;
	private List<ReportElement> monthlyIncomeDetailsList;
	private List<ReportElement> topOffersDetailsList;
	private List<ReportElement> adminUserRegNumberDetailsList;
	private List<ReportElement> adminClientRegNumberDetailsList;

	private BarChartModel totalSalesBarChart;
	private BarChartModel totalIncomeBarChart;
	private DonutChartModel topOffersPieChart;
	private LineChartModel adminUserNumberLineChart;
	private LineChartModel adminClientNumberLineChart;
	private LineChartModel incomeBySalesPointLineChart;

	@PostConstruct
	public void init() {
		monthsList = new LinkedHashMap<String, Integer>();

		DateFormatSymbols dfs = new DateFormatSymbols(FacesContext.getCurrentInstance().getExternalContext().getRequestLocale());
		String[] months = dfs.getMonths();
		for (int i = 1; i <= 12; i++) {
			monthsList.put(months[i - 1], i);
		}


		adminUserRegNumberDetailsList = new ArrayList<>();
		adminClientRegNumberDetailsList = new ArrayList<>();
		monthlySalesDetailsList = new ArrayList<>();

		salesPointList = salePointService.findAll();
		selectedSalesPoint1 = salesPointList.get(0);
		selectedSalesPoint2 = salesPointList.get(1);

		yearStart = 2014;
		monthStart = 8;
		yearEnd = 2016;
		monthEnd = 2;
		calibration = "Month";
		
		salesReportGenerate();
	}

	/*********************************************************************************/
	/****************************** Total Sales **************************************/
	/*********************************************************************************/
	public List<ReportElement> generateSalesDetails() {

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Calendar currentFrom = Calendar.getInstance();
		Calendar currentTo = Calendar.getInstance();

		if (calibration.equals("Month")) {
			from.set(yearStart, monthStart, 1);
			to.set(yearEnd, monthEnd, 1);
			to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
			currentFrom.setTime(from.getTime());
			currentFrom.add(Calendar.MONTH, -1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.MONTH, 1);
		} else {
			from.set(yearStart, 0, 1);
			to.set(yearEnd + 2, 0, 1);

			currentFrom.setTime(from.getTime());
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.YEAR, 1);
		}

		List<ReportElement> rv = new ArrayList<>();
		int increment = getIncrementAsCalendarFiled();

		while (currentTo.before(to)) {
			ReportElement element = new ReportElement();
			element.setDate(currentFrom.getTime());
			element.setNumber(clientOfferService.findCountByInterval(currentFrom.getTime(), currentTo.getTime()));

			rv.add(element);

			currentFrom.add(increment, 1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(increment, 1);
		}

		return rv;
	}

	public void generateSalesDiagramms() {
		totalSalesBarChart = new BarChartModel();
		totalSalesBarChart.setAnimate(true);
		
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_sold_product", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateSalesDetails()) {
			String date;
			if(calibration.equals("Month")){
				date = new SimpleDateFormat("yyyy.MM").format(element.getDate());	
			}else{
				date = new SimpleDateFormat("yyyy").format(element.getDate());
			}
			number.set(date, element.getNumber());
			number.set(date, element.getNumber());
		}

		totalSalesBarChart.addSeries(number);
		totalSalesBarChart.setLegendPosition("ne");
		totalSalesBarChart.setShowPointLabels(true);

		Axis xAxis = totalSalesBarChart.getAxis(AxisType.X);
		xAxis.setLabel(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		xAxis.setTickAngle(-50);

		Axis yAxis = totalSalesBarChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}

	public void salesReportGenerate() {
		generateSalesDiagramms();
	}

	/*********************************************************************************/
	/****************************** Total Income *************************************/
	/** *******************************************************************************/
	
	public List<ReportElement> generateIncomeDetails() throws Exception {

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Calendar currentFrom = Calendar.getInstance();
		Calendar currentTo = Calendar.getInstance();

		if (calibration.equals("Month")) {
			from.set(yearStart, monthStart, 1);
			to.set(yearEnd, monthEnd, 1);
			to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
			currentFrom.setTime(from.getTime());
			currentFrom.add(Calendar.MONTH, -1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.MONTH, 1);
		} else {
			from.set(yearStart, 0, 1);
			to.set(yearEnd + 2, 0, 1);

			currentFrom.setTime(from.getTime());
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.YEAR, 1);
		}

		List<ReportElement> rv = new ArrayList<>();
		int increment = getIncrementAsCalendarFiled();

		while (currentTo.before(to)) {
			ReportElement element = new ReportElement();
			element.setDate(currentFrom.getTime());
			element.setNumber(clientOfferService.findIncomeByInterval(currentFrom.getTime(), currentTo.getTime()));

			rv.add(element);

			currentFrom.add(increment, 1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(increment, 1);
		}

		return rv;
	}

	public void generateIncomeDiagramms() throws Exception {
		totalIncomeBarChart = new BarChartModel();
		totalIncomeBarChart.setAnimate(true);
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateIncomeDetails()) {
			String date;
			if(calibration.equals("Month")){
				date = new SimpleDateFormat("yyyy.MM").format(element.getDate());	
			}else{
				date = new SimpleDateFormat("yyyy").format(element.getDate());
			}
			number.set(date, element.getNumber());
		}

		totalIncomeBarChart.addSeries(number);
		totalIncomeBarChart.setLegendPosition("ne");
		totalIncomeBarChart.setShowPointLabels(true);

		Axis xAxis = totalIncomeBarChart.getAxis(AxisType.X);
		xAxis.setLabel(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		xAxis.setTickAngle(-50);

		Axis yAxis = totalIncomeBarChart.getAxis(AxisType.Y);
		yAxis.setLabel("HUF");
		yAxis.setMin(0);

	}

	public void totalIncomeReportGenerate() {
		try {
			generateIncomeDiagramms();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*********************************************************************************/
	/************************* SalesPointIncome **************************************/
	/*********************************************************************************/

	public List<ReportElement> generateIncomeBySalesPointDetails(SalesPointVO salesPoint) throws Exception {

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Calendar currentFrom = Calendar.getInstance();
		Calendar currentTo = Calendar.getInstance();

		if (calibration.equals("Month")) {
			from.set(yearStart, monthStart, 1);
			to.set(yearEnd, monthEnd, 1);
			to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
			currentFrom.setTime(from.getTime());
			currentFrom.add(Calendar.MONTH, -1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.MONTH, 1);
		} else {
			from.set(yearStart, 0, 1);
			to.set(yearEnd + 2, 0, 1);

			currentFrom.setTime(from.getTime());
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.YEAR, 1);
		}

		int increment = getIncrementAsCalendarFiled();
		List<ReportElement> rv = new ArrayList<>();
		while (currentTo.before(to)) {
			ReportElement element = new ReportElement();
			element.setDate(currentFrom.getTime());
			int number = clientOfferService.findIncomeBySalesPointIdInDateInterval(salesPoint.getSalePointId(), currentFrom.getTime(), currentTo.getTime());
			element.setNumber(number);

			rv.add(element);

			currentFrom.add(increment, 1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(increment, 1);
		}

		return rv;
	}

	public void generateMonthIncomeBySalesPointDiagramms() throws Exception {
		incomeBySalesPointLineChart = new LineChartModel();
		incomeBySalesPointLineChart.setAnimate(true);
		LineChartSeries line1 = new LineChartSeries();

		line1.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()) + ": "
				+ selectedSalesPoint1.getName());
		List<ReportElement> list1 = generateIncomeBySalesPointDetails(selectedSalesPoint1);
		for (ReportElement element : list1) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			line1.set(date, element.getNumber());
		}

		LineChartSeries line2 = new LineChartSeries();
		line2.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()) + ": "
				+ selectedSalesPoint2.getName());
		List<ReportElement> list2 = generateIncomeBySalesPointDetails(selectedSalesPoint2);
		for (ReportElement element : list2) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			line2.set(date, element.getNumber());
		}

		incomeBySalesPointLineChart.addSeries(line1);
		incomeBySalesPointLineChart.addSeries(line2);
		incomeBySalesPointLineChart.setLegendPosition("nw");
		incomeBySalesPointLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%m");
		incomeBySalesPointLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = incomeBySalesPointLineChart.getAxis(AxisType.Y);
		yAxis.setLabel("HUF");
		yAxis.setMin(0);

	}

	public void generateYearIncomeBySalesPointDiagramms() throws Exception {
		incomeBySalesPointLineChart = new LineChartModel();
		incomeBySalesPointLineChart.setAnimate(true);
		ChartSeries line1 = new ChartSeries();

		line1.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()) + ": "
				+ selectedSalesPoint1.getName());
		List<ReportElement> list1 = generateIncomeBySalesPointDetails(selectedSalesPoint1);
		for (ReportElement element : list1) {
			String date = new SimpleDateFormat("yyyy").format(element.getDate());
			line1.set(date, element.getNumber());
		}

		ChartSeries line2 = new ChartSeries();

		line2.setLabel(LocalizationsUtils.getText("report_amount_of_income", FacesContext.getCurrentInstance()) + ": "
				+ selectedSalesPoint2.getName());
		List<ReportElement> list2 = generateIncomeBySalesPointDetails(selectedSalesPoint2);
		for (ReportElement element : list2) {
			String date = new SimpleDateFormat("yyyy").format(element.getDate());
			line2.set(date, element.getNumber());
		}

		incomeBySalesPointLineChart.addSeries(line1);
		incomeBySalesPointLineChart.addSeries(line2);
		incomeBySalesPointLineChart.setLegendPosition("ne");
		incomeBySalesPointLineChart.setShowPointLabels(true);

		incomeBySalesPointLineChart.getAxes().put(AxisType.X,
				new CategoryAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance())));

		Axis yAxis = incomeBySalesPointLineChart.getAxis(AxisType.Y);
		yAxis.setLabel("HUF");
		yAxis.setMin(0);

	}

	public void incomeBySalesPointReportGenerate() {
		try {
			if (calibration.equals("Month")) {
				generateMonthIncomeBySalesPointDiagramms();
			} else {
				generateYearIncomeBySalesPointDiagramms();
			}
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
			int value = (int) (offerService.findOfferEntityByOfferId(offer.getOfferId()).getOfferPrice());
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
	public List<ReportElement> generateAdminUserNumberDetails() {

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Calendar currentFrom = Calendar.getInstance();
		Calendar currentTo = Calendar.getInstance();

		if (calibration.equals("Month")) {
			from.set(yearStart, monthStart, 1);
			to.set(yearEnd, monthEnd, 1);
			to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
			currentFrom.setTime(from.getTime());
			currentFrom.add(Calendar.MONTH, -1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.MONTH, 1);
		} else {
			from.set(yearStart, 0, 1);
			to.set(yearEnd + 2, 0, 1);

			currentFrom.setTime(from.getTime());
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.YEAR, 1);
		}

		List<ReportElement> rv = new ArrayList<>();
		int increment = getIncrementAsCalendarFiled();

		while (currentTo.before(to)) {
			ReportElement element = new ReportElement();
			element.setDate(currentFrom.getTime());
			element.setNumber(userService.findNumberOfUsersBeforeDate(currentTo.getTime()));

			rv.add(element);

			currentFrom.add(increment, 1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(increment, 1);
		}

		return rv;
	}

	public void generateMonthlyAdminUserNumberDiagramm() {
		adminUserNumberLineChart = new LineChartModel();
		adminUserNumberLineChart.setAnimate(true);
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_users", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateAdminUserNumberDetails()) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminUserNumberLineChart.addSeries(number);
		adminUserNumberLineChart.setLegendPosition("e");
		adminUserNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%m");

		adminUserNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminUserNumberLineChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}
	
	public void generateYearlyAdminUserNumberDiagramm() {
		adminUserNumberLineChart = new LineChartModel();
		adminUserNumberLineChart.setAnimate(true);
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_users", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateAdminUserNumberDetails()) {
			String date = new SimpleDateFormat("yyyy").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminUserNumberLineChart.addSeries(number);
		adminUserNumberLineChart.setLegendPosition("e");
		adminUserNumberLineChart.setShowPointLabels(true);

		adminUserNumberLineChart.getAxes().put(AxisType.X, new CategoryAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance())));

		Axis yAxis = adminUserNumberLineChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}

	public void adminUserNumGenerate() {
		try {
			if (calibration.equals("Month")) {
				generateMonthlyAdminUserNumberDiagramm();
			} else {
				generateYearlyAdminUserNumberDiagramm();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*********************************************************************************/
	/**************************** Registrated clients **********************************/
	/*********************************************************************************/
	
	public List<ReportElement> generateAdminClientNumberDetails() {

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		Calendar currentFrom = Calendar.getInstance();
		Calendar currentTo = Calendar.getInstance();

		if (calibration.equals("Month")) {
			from.set(yearStart, monthStart, 1);
			to.set(yearEnd, monthEnd, 1);
			to.set(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));
			currentFrom.setTime(from.getTime());
			currentFrom.add(Calendar.MONTH, -1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.MONTH, 1);
		} else {
			from.set(yearStart, 0, 1);
			to.set(yearEnd + 2, 0, 1);

			currentFrom.setTime(from.getTime());
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(Calendar.YEAR, 1);
		}

		List<ReportElement> rv = new ArrayList<>();
		int increment = getIncrementAsCalendarFiled();

		while (currentTo.before(to)) {
			ReportElement element = new ReportElement();
			element.setDate(currentFrom.getTime());
			element.setNumber(clientService.findNumberOfClientsBeforeDate(currentFrom.getTime()));

			rv.add(element);

			currentFrom.add(increment, 1);
			currentTo.setTime(currentFrom.getTime());
			currentTo.add(increment, 1);
		}

		return rv;
	}

	public void generateMonthlyAdminClietNumberDiagramm() {
		adminClientNumberLineChart = new LineChartModel();
		adminClientNumberLineChart.setAnimate(true);
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_clients", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateAdminClientNumberDetails()) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminClientNumberLineChart.addSeries(number);
		adminClientNumberLineChart.setLegendPosition("e");
		adminClientNumberLineChart.setShowPointLabels(true);

		DateAxis axis = new DateAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance()));
		axis.setTickAngle(-50);
		axis.setTickFormat("%Y.%m");

		adminClientNumberLineChart.getAxes().put(AxisType.X, axis);

		Axis yAxis = adminClientNumberLineChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}
	
	public void generateYearlyAdminClientNumberDiagramm() {
		adminClientNumberLineChart = new LineChartModel();
		adminClientNumberLineChart.setAnimate(true);
		ChartSeries number = new ChartSeries();

		number.setLabel(LocalizationsUtils.getText("report_number_of_registrated_clients", FacesContext.getCurrentInstance()));
		for (ReportElement element : generateAdminClientNumberDetails()) {
			String date = new SimpleDateFormat("yyyy").format(element.getDate());
			number.set(date, element.getNumber());
		}

		adminClientNumberLineChart.addSeries(number);
		adminClientNumberLineChart.setLegendPosition("e");
		adminClientNumberLineChart.setShowPointLabels(true);

		adminClientNumberLineChart.getAxes().put(AxisType.X, new CategoryAxis(LocalizationsUtils.getText("report_dates", FacesContext.getCurrentInstance())));

		Axis yAxis = adminClientNumberLineChart.getAxis(AxisType.Y);
		yAxis.setMin(0);

	}

	public void adminClientNumGenerate() {
		try {
			if (calibration.equals("Month")) {
				generateMonthlyAdminClietNumberDiagramm();
			} else {
				generateYearlyAdminClientNumberDiagramm();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		salesReportGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerSalesTabView:monthlyProductSales");
	}

	public void incomeReportGenerateBtnAction() {
		totalIncomeReportGenerate();
		RequestContext.getCurrentInstance().update("reportForm:tabView:innerSalesTabView:monthlyIncome");
	}

	public void salesPointIncomeReportGenerateBtnAction() {
		incomeBySalesPointReportGenerate();
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
		case "salesPointIncome":
			salesPointIncomeReportGenerateBtnAction();
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
	// --------------------------------Utils---------------------------------//
	// ////////////////////////////////////////////////////////////////////////

	private int getIncrementAsCalendarFiled() {
		return calibration.equals("Month") ? Calendar.MONTH : Calendar.YEAR;
	}

	// ////////////////////////////////////////////////////////////////////////
	// --------------------------Getters & Setters---------------------------//
	// ////////////////////////////////////////////////////////////////////////

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

	public BarChartModel getTotalSalesBarChart() {
		return totalSalesBarChart;
	}

	public void setTotalSalesBarChart(BarChartModel totalSalesBarChart) {
		this.totalSalesBarChart = totalSalesBarChart;
	}

	public List<ReportElement> getMonthlyIncomeDetailsList() {
		return monthlyIncomeDetailsList;
	}

	public void setMonthlyIncomeDetailsList(List<ReportElement> monthlyIncomeDetailsList) {
		this.monthlyIncomeDetailsList = monthlyIncomeDetailsList;
	}

	public BarChartModel getTotalIncomeBarChart() {
		return totalIncomeBarChart;
	}

	public void setTotalIncomeBarChart(BarChartModel totalIncomeBarChart) {
		this.totalIncomeBarChart = totalIncomeBarChart;
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

	public LineChartModel getIncomeBySalesPointLineChart() {
		return incomeBySalesPointLineChart;
	}

	public void setIncomeBySalesPointLineChart(LineChartModel incomeBySalesPointLineChart) {
		this.incomeBySalesPointLineChart = incomeBySalesPointLineChart;
	}

	public SalesPointVO getSelectedSalesPoint1() {
		return selectedSalesPoint1;
	}

	public void setSelectedSalesPoint1(SalesPointVO selectedSalesPoint1) {
		this.selectedSalesPoint1 = selectedSalesPoint1;
	}

	public SalesPointVO getSelectedSalesPoint2() {
		return selectedSalesPoint2;
	}

	public void setSelectedSalesPoint2(SalesPointVO selectedSalesPoint2) {
		this.selectedSalesPoint2 = selectedSalesPoint2;
	}

	public List<SalesPointVO> getSalesPointList() {
		return salesPointList;
	}

	public void setSalesPointList(List<SalesPointVO> salesPointList) {
		this.salesPointList = salesPointList;
	}

	public int getYearStart() {
		return yearStart;
	}

	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}

	public int getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(int monthStart) {
		this.monthStart = monthStart;
	}

	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	public int getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(int monthEnd) {
		this.monthEnd = monthEnd;
	}

	public String getCalibration() {
		return calibration;
	}

	public void setCalibration(String calibration) {
		this.calibration = calibration;
	}

	public Map<String, Integer> getMonthsList() {
		return monthsList;
	}

	public void setMonthsList(Map<String, Integer> monthsList) {
		this.monthsList = monthsList;
	}

}
