package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.City;
import java.util.Collection;

/**
 * Interfaccia 
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */
public interface CityService {
	
	public abstract Collection <City> getCity();
	
	public abstract Collection <City> getStatistics();
	
	public abstract Collection <City> getFiltered(String param1, String param2, String param3);
	
}
