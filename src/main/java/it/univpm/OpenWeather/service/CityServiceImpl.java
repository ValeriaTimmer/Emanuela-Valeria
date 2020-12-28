package it.univpm.OpenWeather.service;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;

import org.json.simple.JSONArray;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


/**
 * Classe che implementa l'interfaccia
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
@Service
public class CityServiceImpl implements CityService {
	
	private static Map <String,DownloadCity> cityRepo = new HashMap<>();
	
	/**
	 * Variabile della classe CityFiltered
	 */
	private CityFilter cityFilter;
	
	/**
	 * Variabile della classe HumidityFiltered
	 */
	private HumidityFilter humFilter;
	
	/**
	 * Variabile della classe TemperatureFiltered
	 */
	private TemperatureFilter tempFilter;
	
	/**
	 * Variabile della classe StatsHumidity
	 */
	private StatsHumidity statsHum;
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public void createCity (DownloadCity city) {
		cityRepo.put (city.getApiKey(), city);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getCityFiltered(String city, String state) {
		return cityFilter.filtersCity (cityFilter.getCity(), city, state);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override 
	public Collection <City> getStatisticsFiltered(String hum, String period){
		return statsHum.StatisticsHumidity(statsHum.getHumidity(), hum, period);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getHumidityFiltered (String param1, String param2){
		return humFilter.filtersCity(humFilter.getHumidity(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getTemperatureFiltered (String param1, String param2){
		return tempFilter.filtersCity(tempFilter.getTemperature(), param1, param2);
	}

}
