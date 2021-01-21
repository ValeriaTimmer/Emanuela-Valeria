package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Interfaccia che gestisce i metodi che vengono richiamati dal controller
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */

public interface CityService {
	
	/**
	 * Metodo che crea la HashMap con i metadata
	 * 
	 * @return HashMap 
	 */
	public abstract HashMap <String, String> getMetadata();
	
	/**
	 * Metodo che permette di visualizzare le previsioni attuali e quelle 
	 * dei successivi 5 giorni ogni 3 ore 
	 * @param city nome della citta
	 * @return JSONArray contenente le previsioni
	 * @throws UrlException eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 */
	public abstract JSONArray getData(String city)
			throws UrlException, org.json.simple.parser.ParseException, IOException ;
	
	/**
	 * Metodo che ci permette di ritornare le statistiche giornalierie di un 
	 * dato periodo di una delle citta monitorate
	 * 
	 * @param city nome della citta
	 * @param type umidita/temperatura
	 * @param from data di inizio del periodo 
	 * @param to data di fine del periodo
	 * @return JSONArray con i valori desiderati delle citta 
	 * @throws IllegalArgumentException Errore di argomento errato
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws StatsException Eccezione personalizzata
	 * @throws com.sun.el.parser.ParseException
	 */
	 public abstract JSONArray getDailyStats(String city, String type, String from, String to)
			 throws IllegalArgumentException, UrlException, 
		org.json.simple.parser.ParseException, IOException, ParseException,
		StatsException, com.sun.el.parser.ParseException;
	
	/**
	 * Metodo che permette di filtrare le statistiche
	 * 
	 * @param city Nome della citta
	 * @param type Tipologia di statistica che si vuole ottenere (temperatura/umidità)
	 * @param from Data di inizio statistiche
	 * @param to Data di fine statistcihe 
	 * @return HashMap delle statistiche effettute 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ClassNotFoundException Errore di classe non trovata
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws FileNotFoundException Errore file non trovato
	 * @throws IOException Errore di I/O
	 * @throws org.json.simple.parser.ParseException Errore di Parsing 
	 * @throws StatsException Eccezione personalizzata
	 */
	public abstract HashMap <String, String> getStats(String city, String type, String from, String to) 
			throws UrlException, ClassNotFoundException, DataFormatException, FileNotFoundException, 
			IOException, org.json.simple.parser.ParseException, StatsException;
	
	
	
	/**
	 * Metodo che permette di determinare le previsioni azzeccate
	 * 
	 * @param date Data
	 * @param city Nome della citta
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 */
	public abstract JSONObject getForecasts (String date, String city)throws UrlException, 
			org.json.simple.parser.ParseException, IOException;
	 
	
}
