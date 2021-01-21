package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;

import it.univpm.OpenWeather.statistics.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.filter.TypeFilter;
import it.univpm.OpenWeather.utils.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

import java.text.ParseException;
import java.io.*;

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
	 * Variabile della classe Stats
	 */
	private Stats s;
	
	/**
	 * Variabile della classe Forecast
	 */
	private Forecasts f;
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * Variabile della classe City
	 */
	private City c;
	
	/**
	 * JSONArray
	 */
	private JSONArray array;
	
	/**
	 * Variabile della classe DataBase
	 */
	private DataBase dB = new DataBase();
	
	/**
	 * Costruttore
	 * @throws UrlException eccezione personalizzata
	 * @throws IOException errore di I/O
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws ClassNotFoundException errore di classe non trovata
	 */
	public CityServiceImpl() 
			throws UrlException, IOException, org.json.simple.parser.ParseException, ClassNotFoundException {
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * 
	 * @return c HashMap contenente i metadata
	 */
	@Override
	public HashMap <String, String > getMetadata(){
		HashMap <String, String> c = new HashMap <String,String>();
		c.put("city", "Contiene il nome della città");
		c.put("humidity", "Contiene il valore dell'umidità in percentuale");
		c.put("temperature", "Contiene il valore della temperatura in Celsius");
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
	 * 
	 * @param city nome della citta
	 * @return JSONArray contenente le previsioni attuali e dei successivi 5 giorni
	 * @throws UrlException eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 * 
	 */
	@Override
	public JSONArray getData(String city) throws UrlException, org.json.simple.parser.ParseException, IOException {
		
		return dB.getAllData(city);
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * 
	 * @param city nome della citta
	 * @param type umidita/temperatura
	 * @param from data di inizio del periodo
	 * @param to data di fine del periodo
	 * @return JSONArray con i valori desiderati delle città 
	 * @throws IllegalArgumentException Errore di argomento errato
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws StatsException Eccezione personalizzata
	 * @throws com.sun.el.parser.ParseException 
	 */
	
	@Override 
	public JSONArray getDailyStats(String city, String type, String from, String to)
			throws IllegalArgumentException, UrlException, 
		org.json.simple.parser.ParseException, IOException, ParseException,
		StatsException, com.sun.el.parser.ParseException { 

		TypeFilter t = new TypeFilter(Config.getNameStorico());
		return t.filtersCity(city,type,from,to);
        
	}
	
	
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * 
	 * @return HashMap contenente le statistiche filtrate per periodo
	 * @throws UrlException Eccezione personalizzata
	 * @throws ClassNotFoundException Errore di classe non trovata
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws FileNotFoundException Errore file non trovato
	 * @throws IOException Errore di I/O
	 * @throws org.json.simple.parser.ParseException Errore di Parsing 
	 * @throws StatsException Eccezione personalizzata
	 */
	@Override 
	public HashMap <String, String> getStats (String city, String type, String from, String to ) 
			throws UrlException, ClassNotFoundException, DataFormatException, FileNotFoundException, IOException,
			org.json.simple.parser.ParseException, StatsException {
		
		this.c = new City(city);
		
		DataBase dB = new DataBase ();
	
		this.s = new Stats(dB.getAllDataStorico(city));

		return s.Statistics(s.getArray(), city, type, from, to);
		
		
	}
    
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia
	 * 
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * 
	 */
	@Override
	public JSONObject getForecasts (String date, String city) throws UrlException,  
		org.json.simple.parser.ParseException, IOException{
		
		Forecasts previsioni = new Forecasts();
		
		return previsioni.confrontaValori(date, city);
	}
	
}
