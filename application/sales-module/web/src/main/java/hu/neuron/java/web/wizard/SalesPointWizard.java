package hu.neuron.java.web.wizard;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class SalesPointWizard {
	
	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
    }

}
