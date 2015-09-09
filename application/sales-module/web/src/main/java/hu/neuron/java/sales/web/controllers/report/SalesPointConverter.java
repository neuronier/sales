package hu.neuron.java.sales.web.controllers.report;

import hu.neuron.java.sales.service.SalesPointServiceRemote;
import hu.neuron.java.sales.service.vo.SalesPointVO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("salesPointConverter")
@ManagedBean(name = "salesPointConverter")
public class SalesPointConverter implements Converter {

	@EJB(name = "SalesPointService", mappedName = "SalesPointService")
	SalesPointServiceRemote salesPointService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	    if(value != null && value.trim().length() > 0) {
	        try {
	        	SalesPointVO sp = salesPointService.findSalesPointBySalePointId(value);
	            return sp;
	            
	        } catch(Exception e) {
	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid salespoint."));
	        }
	    }
	    else { 
	        return null;
	    }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		 if(object != null) {
		        return ((SalesPointVO) object).getSalePointId();
		    }
		    else {
		        return null;
		    }
	}

}
