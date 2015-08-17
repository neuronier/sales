package hu.neuron.java.sales.web;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class LocalizationsUtils {

	public static String getText(String key, FacesContext context){
		ResourceBundle bundle = ResourceBundle.getBundle("hu.neuron.java.sales.web.Messages", context.getViewRoot().getLocale());
	    return bundle.getString(key);
	}
	
}
