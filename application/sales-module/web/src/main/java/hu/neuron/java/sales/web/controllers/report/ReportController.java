package hu.neuron.java.sales.web.controllers.report;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "reportController")
public class ReportController {

	private Date reportFrom;
	private Date reportTo;
	
	
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
	
}
