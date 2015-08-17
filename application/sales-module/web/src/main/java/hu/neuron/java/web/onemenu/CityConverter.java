package hu.neuron.java.web.onemenu;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cityConverter")
public class CityConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		return value;
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return object.toString();
		} else {
			return null;
		}
	}
}
