package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.vo.UserVO;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("userConverter")
public class UserConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	            	UserListService service = (UserListService) fc.getExternalContext().getApplicationMap().get("userListService");
	            	UserVO rv = null;
	            	for(UserVO u : service.getUserList()){
	            		if(u.getUserId().equals(value)){
	            			rv = u;
	            		}
	            	}
	                return rv;
	            } catch(NumberFormatException e) {
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid user."));
	            }
	        }
	        else {
	            return null;
	        }
	}
	
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return ((UserVO)object).getUserId();
		} else {
			return null;
		}
	}
}
