package hu.neuron.java.sales.web.controllers.producttype;
 
import hu.neuron.java.sales.service.ProductTypeServiceRemote;
import hu.neuron.java.sales.service.vo.ProductTypeVO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("productTypeConverter")
@ManagedBean(name = "productTypeConverter")
public class ProductTypeFacesConverter implements Converter {
	
	@EJB(name = "ProductTypeService", mappedName = "ProductTypeService")
	ProductTypeServiceRemote productTypeService;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	    if(value != null && value.trim().length() > 0) {
	        try {
	            return productTypeService.findProductTypeByName(value);
	            
	        } catch(Exception e) {
	            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid product type."));
	        }
	    }
	    else { 
	        return null;
	    }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		 if(object != null) {
		        return String.valueOf(((ProductTypeVO) object).getName());
		    }
		    else {
		        return null;
		    }
	}
	
   
}         

