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
	 * @return HashMap 
	 */
	public abstract HashMap <String, String> getMetadata();
	
	/**
	 * Metodo che ci permette di ritornare le statistiche effettutate 
	 * su una determinata città in base a tutti i valori presenti nello storico
	 * 
	 * @param city Nome della città
	 * @param type Tipologia di statistica che si vuole ottenere (temperatura/umidità)
	 * @return HashMap<String,String> con i valori desiderati della città 
	 * @throws IllegalArgumentException Errore di argomento errato
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException Errore del formato dell'Url
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 * @throws ParseException Errore di Parsing
	 */
	public abstract HashMap<String,String> getData (String city, String type)  throws IllegalArgumentException, UrlException, 
	MalformedURLException, org.json.simple.parser.ParseException, IOException, ParseException;
	
	/**
	 * Metodo che permette di selezionare le statistiche 
	 * 
	 * @param city Nome della città
	 * @param type Tipologia di statistica che si vuole ottenere (temperatura/umidità)
	 * @param from Data di inizio statistiche
	 * @param to Data di fine statistcihe 
	 * @return HashMap<String,String> delle statistiche effettute 
	 * @throws UrlException Eccezione personalizzata
	 * @throws ClassNotFoundException Errore di classe non trovata
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 * @throws FileNotFoundException Errore file non trovato
	 * @throws IOException Errore di I/O
	 * @throws org.json.simple.parser.ParseException Errore di Parsing 
	 */
	public abstract HashMap <String, String> getStats(String city, String type, String from, String to) 
			throws UrlException, ClassNotFoundException, DataFormatException, ParseException, FileNotFoundException, 
			IOException, org.json.simple.parser.ParseException;
	
	/**
	 * metodo che ritorna le previsioni attuali e le salva in un file,
	 * sostituendole a quelle che vi sono già
	 * @param city nome della città
	 * @return JSONArray
	 * @throws IllegalArgumentException
	 * @throws UrlException
	 * @throws MalformedURLException
	 * @throws org.json.simple.parser.ParseException
	 * @throws IOException
	 * @throws ParseException
	 */
	public abstract JSONArray getSalvaData(String city) throws IllegalArgumentException, UrlException, 
	MalformedURLException, org.json.simple.parser.ParseException, IOException, ParseException;
	
	/**
	 * metodo che permette di determinare le previsioni azzeccate
	 * @param date data
	 * @param city nome della città
	 * @return JSONObject contenente il valore del contatore
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException errore nel formato dell'url
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws IOException errore di I/O
	 */
	public abstract JSONObject getForecasts (String date, String city)throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException;
	 
	
}
