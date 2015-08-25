package hu.neuron.java.sales.web.controllers.user;

import hu.neuron.java.sales.service.UserServiceRemote;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "uniqueUsernameValidator")
@RequestScoped
public class UniqueUsernameValidator implements Validator {

	@EJB(name = "UserService", mappedName = "UserService")
	private UserServiceRemote userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = (String) value;

        try {
			if (userService.findUserByUserName(username) != null) {
			    throw new ValidatorException(new FacesMessage(
			        FacesMessage.SEVERITY_ERROR, "Username already in use. Please choose another", null));
			}
		} catch (Exception e) {
			throw new ValidatorException(new FacesMessage(
			        FacesMessage.SEVERITY_ERROR, "Username already in use. Please choose another", null));
		}
    }

}