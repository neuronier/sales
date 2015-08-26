package hu.neuron.java.web.autocomplete;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class OfferAutoCompleteView {
       
	private String offer; 

	public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
	
	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}
   
	
}