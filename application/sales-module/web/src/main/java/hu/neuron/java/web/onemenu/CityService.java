package hu.neuron.java.web.onemenu;

import hu.neuron.java.sales.service.AddressServiceRemote;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "cityService")
@ApplicationScoped
public class CityService {

	private List<String> cities;

	@EJB(mappedName = "AddressService", name = "AddressService")
	AddressServiceRemote adService;

	@PostConstruct
	public void init() {
		updateCityList();
	}

	public List<String> getCities() {
		updateCityList();
		return cities;
	}

	private void updateCityList() {
		try {
			cities = adService.findAllCities();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}