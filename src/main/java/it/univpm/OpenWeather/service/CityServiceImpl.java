package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;


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
	
	JSONArray array = new JSONArray();
	
	private City c;
	
	private DownloadCity d;
	
	
	/**
	 * Costruttore della classe CityServiceImpl
	 *
	public CityServiceImpl() throws NoClassDefFoundError , UrlException {
		this.c = new City();
		this.d = new DownloadCity();
		cityRepo.put(d.Parser(),c);
	}
	*/
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return c HashMap contenente i metadata
	 */
	@Override
	public HashMap <String, String > getMetadata(){
		HashMap <String, String> c = new HashMap <String,String>();
		c.put("city", "Contiene il nome della città");
		c.put("min", "Contiene il valore minimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("max", "Contiene il valore massimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("avg", "Contiene il valore medio dell'umidità o della temperatura in base al periodo scelto");
		c.put("var", "Contiene il valore della varianza dell'umidità o della temperatura in base al periodo scelto");
		return c ;
	}
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public JSONArray getCityFiltered(String city, String state) throws UrlException {
		array = d.Parser();
		CityFilter c = new CityFilter (array);
		return c.filtersCity (c.getCity(), city, state);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override 
	public JSONArray getStats (String type, String datainiziale, String datafinale ) throws UrlException{
		array = d.Parser();
		Stats s = new Stats(array);
		if (type.equals("humidity")) {
			JSONArray statsHum = s.Statistics (s.getArray(), "humidity", datainiziale, datafinale);
			return  statsHum; 
		}
		else if (type.equals("temperature")) {
			JSONArray statsTemp = s.Statistics(s.getArray(), "temperature", datainiziale, datafinale);
			return statsTemp;
		}
		return null;
	}
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public JSONArray getHumidityFiltered (String param1, String param2) throws UrlException {
		array = d.Parser();
		HumidityFilter h = new HumidityFilter (array);
		return h.filtersCity(h.getHumidity(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public JSONArray getTemperatureFiltered (String param1, String param2) throws UrlException {
		array = d.Parser();
		TemperatureFilter t = new TemperatureFilter(array);
		return t.filtersCity(t.getTemperature(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 */
	@Override
	public JSONArray getWeatherFiltered (String weather, String city) throws UrlException {
		array = d.Parser();
		WeatherFilter w = new WeatherFilter(array);
		return w.filtersCity (w.getWeather(), weather, city);
	}
}
