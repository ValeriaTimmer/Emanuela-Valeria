package it.univpm.OpenWeather.service;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import java.util.Collection;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe che implementa l'interfaccia
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */
@Service
public class CityServiceImpl implements CityService {
	
	private static Map <String, City> cityRepo = new HashMap();
	
	/**
	 * Costruttore della classe 
	 */
	public void CityServiceImpl() {
		//City c = new City();
		//c.setCityName(toString());
		//c.setStateCode(toString());
		//cityRepo.put(c.getCityName() + c.getStateCode(), c);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getCity() {
		return cityRepo.values();
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override 
	public Collection <City> getStatistics(){
		return cityRepo.values();
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getFiltered(String param1, String param2, String param3){
		return cityRepo.values();
	}

}
