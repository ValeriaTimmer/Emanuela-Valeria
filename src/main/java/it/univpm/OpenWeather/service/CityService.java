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
}
