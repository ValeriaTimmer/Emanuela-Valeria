package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;


import org.json.simple.JSONArray;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.text.ParseException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe che implementa l'interfaccia
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
@Service
public class CityServiceImpl implements CityService {
	
	/**
	 * Array contente i dati salvati
	 */
	JSONArray array = new JSONArray();
	
	/**
	 * Variabile di tipo DownloadCity
	 */
	DownloadCity d = new DownloadCity();
	
	/**
	 * Costruttore
	 */
	public CityServiceImpl() {}
	
	/**
	 * Metodo che inizializza 
	 * @return
	 * @throws UrlException
	 */
	public JSONArray getArray() throws UrlException {
		return this.array = d.Parser();
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return c HashMap contenente i metadata
	 */
	@Override
	public HashMap <String, String > getMetadata(){
		HashMap <String, String> c = new HashMap <String,String>();
		c.put("city", "Contiene il nome della città");
		c.put("country", "Contiene la sigla dello stato corrispondente alla città");
		c.put("humidity", "Contiene il valore dell'umidità in percentuale");
		c.put("temp", "Contiene il valore della temperatura in kelvin");
		c.put("description", "Contiene la descrizione del meteo della città");
		c.put("min", "Contiene il valore minimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("max", "Contiene il valore massimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("avg", "Contiene il valore medio dell'umidità o della temperatura in base al periodo scelto");
		c.put("var", "Contiene il valore della varianza dell'umidità o della temperatura in base al periodo scelto");
		return c ;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return c JSONArray contenente le città filtrate per nome e stato
	 */
	@Override
	public JSONArray getCityFiltered(String city, String state) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		CityFilter c = new CityFilter (service.getArray());
		return c.filtersCity (c.getCity(), city, state);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return JSONArray contenente le statistiche filtrate
	 */
	@Override 
	public JSONArray getStats (String city, String state, String type, String from, String to ) 
			throws UrlException, ClassNotFoundException,
	 DataFormatException, ParseException {
		
		CityServiceImpl service = new CityServiceImpl();
		
		Stats s = new Stats(service.getArray());
		
		City c = new City(city, state);
		
		JSONArray stats = s.Statistics(s.getArray(), c.getCityName(), c.getStateCode(), type, from, to);
		
		return stats;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return h JSONArray contenete le umidità filtrate
	 */
	@Override
	public JSONArray getHumidityFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		HumidityFilter h = new HumidityFilter (service.getArray());
		return h.filtersCity(h.getArray(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return t JSONArray contenete le temperature filtrate
	 */
	@Override
	public JSONArray getTemperatureFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		TemperatureFilter t = new TemperatureFilter(service.getArray());
		return t.filtersCity(t.getTemperature(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return w JSONArray contenente le città filtrate per tipo di meteo 
	 */
	@Override
	public JSONArray getWeatherFiltered (String weather, String city) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		WeatherFilter w = new WeatherFilter(service.getArray());
		return w.filtersCity (w.getWeather(), weather, city);
	}
}
