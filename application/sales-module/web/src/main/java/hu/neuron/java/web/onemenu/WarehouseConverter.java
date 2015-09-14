package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.vo.WarehouseVO;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("warehouseConverter")
public class WarehouseConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
            	WarehouseService service = (WarehouseService) fc.getExternalContext().getApplicationMap().get("warehouseService");
            	WarehouseVO rv = null;
            	for(WarehouseVO wh : service.getWarehouses()){
            		if(wh.getWarehouseName().equals(value)){
            			rv = wh;
            		}
            	}
                return rv;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid warehouse."));
            }
        }
        else {
            return null;
        }
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			WarehouseVO w = (WarehouseVO)object;
			return w.getWarehouseName();
		} else {
			return null;
		}
	}
}
