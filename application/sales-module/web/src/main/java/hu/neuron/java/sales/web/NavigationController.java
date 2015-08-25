package hu.neuron.java.sales.web;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "navigationController")
public class NavigationController {
	
	private HashMap<String,String> position = new HashMap<>();

	
	@PostConstruct
	private void init(){
		position.put("/secured/admin/userManagement.xhtml", "User management");
		position.put("/secured/salepoint.xhtml", "Salepoint management");
		position.put("/secured/issueManagement.xhtml", "Issue management");
		position.put("/secured/offers.xhtml", "Offer management");
		position.put("/secured/order.xhtml", "Order management");
		position.put("/secured/userProfile.xhtml", "User profile");
		position.put("/secured/reports.xhtml", "Reports");
		position.put("/secured/sell.xhtml", "Selling");
	}
	
	
	public String getPosition(String url){
		String rv = position.get(url);
		if(rv == null){
			rv = "Sales Module";
		}
		
		return "You are here: " + rv;
	}
	
	public HashMap<String,String> getPosition() {
		return position;
	}

	public void setPosition(HashMap<String,String> position) {
		this.position = position;
	}
	
	

}
