package hu.neuron.java.web.onemenu;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
 
@ManagedBean
public class CitySelectOneMenuView {
     
    private static String city;   
    private static List<String> cities;
     
    @ManagedProperty("#{cityService}")
    private CityService service;
    
    private static CityService staticService;
     
    @PostConstruct
    public void init() {
        cities = service.getCities();
        staticService = service;
    }

    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        CitySelectOneMenuView.city = city;
    }
     
    public List<String> getCities() {
        return cities;
    }
     
    public void setService(CityService service) {
        this.service = service;
    }    
    
    public static String getStaticCity() {
        return city;
    }
    
    public static void setStaticCity(String city) {
    	CitySelectOneMenuView.city = city;
    }
    
    public static void updateCityList(){
    	staticService.updateCityList();
    	cities = staticService.getCities();
    }
}