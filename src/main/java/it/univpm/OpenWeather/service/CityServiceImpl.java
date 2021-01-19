package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;

import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;

import java.net.MalformedURLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

import java.time.LocalDate;
import java.text.ParseException;
import java.io.*;
import java.awt.*;
import javax.swing.*;

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
	
	/**
	 * Variabile della classe @it.univpm.OpenWeather.statistics.Stats
	 */
	private Stats s;
	
	/**
	 * Variabile della classe @it.univpm.OpenWeather.statistics.Forecast
	 */
	private Forecasts f;
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * Variabile della classe @it.univpm.OpenWeather.model.City
	 */
	private City c;
	
	/**
	 * JSONArray
	 */
	private JSONArray array;
	
	/**
	 * Variabile della classe @it.univpm.OpenWeather.utils.DataBase
	 */
	private DataBase dB = new DataBase();
	
	
	
	/**
	 * Costruttore
	 */
	public CityServiceImpl() throws UrlException, IOException, org.json.simple.parser.ParseException,
	ClassNotFoundException {
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
		c.put("temp", "Contiene il valore della temperatura in Celsius");
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
	 * @return data HashMap contenete i dati relativi ad una città
	 * @throws IllegalArgumentException Errore di argomento
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedUrlException Errore del formato dell'Url
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws StatsException eccezione personalizzata
	 */
	@Override 
	public HashMap<String,String> getData(String city, String type) throws IllegalArgumentException, UrlException, 
	 org.json.simple.parser.ParseException, IOException, ParseException, StatsException { 
		
		JSONArray data = new JSONArray();
		
		LocalDate from = LocalDate.parse (DateUtils.today());
		
		c = new City (city);
		
		data = dB.getAllData(city);
		
		
		this.s = new Stats(data);
		
		for (Object o : data) {
			
			if (o instanceof JSONObject) {
				
				JSONObject o1 = (JSONObject) o;
					
					String date = (String) o1.get("date");
					
					while (date.compareTo(from.toString()) <= 0) {
						
						from = from.minusDays(1);
					}
			}
		}
		
		return s.Statistics(s.getArray(), c.getCityName(), type, from.toString(), DateUtils.today());
        
	}
	
	/**
	 * metodo che effettua l'override del metodo dell'interfaccia
	 * @param city nome della città
	 */
	@Override
	public JSONArray getSalvaData(String city)throws IllegalArgumentException, UrlException, 
	 org.json.simple.parser.ParseException, IOException, ParseException{
        JSONArray data = new JSONArray();
		
		LocalDate from = LocalDate.parse (DateUtils.today());
		
		c = new City (city);
		
		data = dB.getAllData(city);
		
		dB.salvaData(Config.getName2(), city);
		
		return data;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * @return HashMap<String,Sting> contenente le statistiche filtrate per periodo
	 * @throws UrlExceptin Eccezione personalizzata
	 * @throws ClassNotFoundException Errore di classe non trovata
	 * @throws ParseException Errore di Parsing
	 * @throws FileNotFoundException Errore di file non trovato
	 * @throws IOException Errore di I/O
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws StatsException eccezione personalizzata
	 */
	@Override 
	public HashMap <String, String> getStats (String city, String type, String from, String to ) 
			throws UrlException, ClassNotFoundException,
	 ParseException, FileNotFoundException, IOException, org.json.simple.parser.ParseException, StatsException {
		
		this.c = new City(city);
		
		DataBase dB = new DataBase ();
	
		this.s = new Stats(dB.getAllData(c.getCityName()));

		return s.Statistics(s.getArray(), c.getCityName(), type, from, to);
		
	}
    
	/**
	 * metodo che effettua l'override del metodo dell'interfaccia
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException errore nel formato dell'url
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 * 
	 */
	@Override
	public JSONObject getForecasts (String date, String city) throws UrlException,  org.json.simple.parser.ParseException, IOException{
		Forecasts previsioni = new Forecasts();
		return previsioni.confrontaValori(date, city);
	}
	
}
