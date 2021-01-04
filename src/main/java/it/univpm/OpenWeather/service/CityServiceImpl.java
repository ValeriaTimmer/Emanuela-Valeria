package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;


import org.json.simple.JSONArray;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
	
	DownloadCity d = new DownloadCity();
	/**
	 * Costruttore che inizializza il JSONArray
	 * @param download Variabile della classe DownloadCity
	 * @throws UrlException Eccezione personalizzata
	 */
	
	public CityServiceImpl(DownloadCity download) throws UrlException {
		try {
		this.d = download;
		this.array = d.Parser();
		} catch (IllegalStateException e) {
			System.out.println ("Errore Scheduler");
			System.out.println ("Messaggio: " + e.getMessage());
			System.out.println ("Causa: " + e.getCause());
		}
	}
	
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
	 * @return c JSONArray contenente le città filtrate per nome e stato
	 */
	@Override
	public JSONArray getCityFiltered(String city, String state) throws UrlException, ClassNotFoundException {
		CityFilter c = new CityFilter (this.array);
		return c.filtersCity (c.getCity(), city, state);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return JSONArray contenente le statistiche filtrate
	 */
	@Override 
	public JSONArray getStats (String type, String datainiziale, String datafinale ) throws UrlException, ClassNotFoundException,
	 DataFormatException, ParseException {
		Stats s = new Stats(this.array);
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
	 * @return h JSONArray contenete le umidità filtrate
	 */
	@Override
	public JSONArray getHumidityFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		HumidityFilter h = new HumidityFilter (this.array);
		return h.filtersCity(h.getHumidity(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return t JSONArray contenete le temperature filtrate
	 */
	@Override
	public JSONArray getTemperatureFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		TemperatureFilter t = new TemperatureFilter(this.array);
		return t.filtersCity(t.getTemperature(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return w JSONArray contenente le città filtrate per tipo di meteo 
	 */
	@Override
	public JSONArray getWeatherFiltered (String weather, String city) throws UrlException, ClassNotFoundException {
		WeatherFilter w = new WeatherFilter(this.array);
		return w.filtersCity (w.getWeather(), weather, city);
	}
}
