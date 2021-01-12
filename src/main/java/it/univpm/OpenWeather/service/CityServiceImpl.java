package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.net.MalformedURLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.time.LocalDate;
import java.text.ParseException;
import java.io.*;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Classe che implementa l'interfaccia
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
@Service
public class CityServiceImpl implements CityService {
	
	private Stats s;
	
	private JSONObject obj = new JSONObject();
	
	/**
	 * Variabile della classe DownloadCity
	 */
	private DownloadCity d;
	
	private JSONArray array;
	
	private ArrayList<Integer> o;
	
	
	/**
	 * Costruttore
	 */
	public CityServiceImpl() throws UrlException, MalformedURLException, IOException, org.json.simple.parser.ParseException,
	ClassNotFoundException {
		this.o = new ArrayList<Integer>();
		this.d = new DownloadCity();
	}
	
	/**
	 * Variabile della classe Stats
	 */
	//private Stats s = new Stats();
	
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
		c.put("from", "Data iniziale del periodo scelto");
		c.put("to", "Data finale del periodo scelto");
		c.put("min", "Contiene il valore minimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("max", "Contiene il valore massimo dell'umidità o della temperatura in base al periodo scelto");
		c.put("avg", "Contiene il valore medio dell'umidità o della temperatura in base al periodo scelto");
		c.put("var", "Contiene il valore della varianza dell'umidità o della temperatura in base al periodo scelto");
		return c;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return c JSONArray contenente le città filtrate per nome e stato
	 
	@Override
	public JSONArray getCityFiltered(String city, String state) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		CityFilter c = new CityFilter (service.getArray());
		return c.filtersCity (c.getCity(), city, state);
	}
	*/
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return JSONArray contenente le statistiche filtrate
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override 
	public HashMap <String, String> getStats (String city, String state, String type, String from, String to ) 
			throws UrlException, ClassNotFoundException,
	 ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		this.d = new DownloadCity (city, state);
		this.obj = d.chiamataAPI();
		d.salvaFile(d.getFile());
		d.ScheduledAtFixedRate();
		this.array = d.caricaFile(d.getFile());
		
		this.s = new Stats(array);
		//this.obj = d1.caricaFile(file);
		//String file = d.getFileName();
		
		//this.array_finale = d1.caricaFile(file);
		
		
		
		return  s.Statistics(s.getArray(), d.getCityName() , d.getStateCode(), type, from, to);
		
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return h JSONArray contenete le umidità filtrate
	 
	@Override
	public JSONArray getHumidityFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		HumidityFilter h = new HumidityFilter (service.getArray());
		return h.filtersCity(h.getArray(), param1, param2);
	}
	*/
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return t JSONArray contenete le temperature filtrate
	 
	@Override
	public JSONArray getTemperatureFiltered (String param1, String param2) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		TemperatureFilter t = new TemperatureFilter(service.getArray());
		return t.filtersCity(t.getTemperature(), param1, param2);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return w JSONArray contenente le città filtrate per tipo di meteo 
	 
	@Override
	public JSONArray getWeatherFiltered (String weather, String city) throws UrlException, ClassNotFoundException {
		CityServiceImpl service = new CityServiceImpl();
		WeatherFilter w = new WeatherFilter(service.getArray());
		return w.filtersCity (w.getWeather(), weather, city);
	}
	*/
}
