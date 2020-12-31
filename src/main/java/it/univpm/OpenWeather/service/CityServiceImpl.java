package it.univpm.OpenWeather.service;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;

import org.json.simple.parser.ParseException;
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
	
	private static Map <JSONArray, City> cityRepo = new HashMap<>();
	
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
	private Stats stats;
	
	/**
	 * Variabile della classe WeatherFiltered
	 */
	private WeatherFilter weatherFilter;
	
	
	/**
	 * Costruttore della classe CityServiceImpl
	 */
	public CityServiceImpl() throws ParseException {
		City c = new City();
		DownloadCity d = new DownloadCity();
		cityRepo.put(d.Parser(),c);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getMetadata(){
		return cityRepo.values();
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
	public Collection <City> getStatistics(String type, String datainiziale, String datafinale ) {
		return stats.Statistics (stats.getArray(), type, datainiziale, datafinale );
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
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public Collection <City> getWeatherFiltered (String weather, String city){
	 return weatherFilter.filtersCity(weatherFilter.getWeather(), weather, city);
	}
}
