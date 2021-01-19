package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
	 * Metodo che ci permette di ritornare le statistiche effettutate 
	 * su tutte le città in base ai valori presenti nello storico
	 * 
	 * @return HashMap<String,String> con i valori desiderati delle città 
	 * @throws IllegalArgumentException Errore di argomento errato
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 * @throws StatsException Eccezione personalizzata
	 */
	public abstract HashMap<String,String> getData (String city, String type)  throws IllegalArgumentException, UrlException, 
	 		org.json.simple.parser.ParseException, IOException, ParseException, StatsException;
	
	/**
	 * Metodo che permette di filtrare le statistiche
	 * 
	 * @param city Nome della città
	 * @param type Tipologia di statistica che si vuole ottenere (temperatura/umidità)
	 * @param from Data di inizio statistiche
	 * @param to Data di fine statistcihe 
	 * @return HashMap<String,String> delle statistiche effettute 
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
	 * Metodo che ritorna le previsioni attuali e le salva in un file,
	 * sostituendole a quelle già presenti
	 * 
	 * @param city Nome della città
	 * @return JSONArray contenete i dati filtrati
	 * @throws IllegalArgumentException Errore di argomento errato
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 */
	public abstract JSONArray getSalvaData(String city) throws IllegalArgumentException, UrlException, 
			org.json.simple.parser.ParseException, IOException, ParseException;
	
	/**
	 * Metodo che permette di determinare le previsioni azzeccate
	 * 
	 * @param date Data
	 * @param city Nome della città
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 */
	public abstract JSONObject getForecasts (String date, String city)throws UrlException, 
			org.json.simple.parser.ParseException, IOException;
	 
	
}
