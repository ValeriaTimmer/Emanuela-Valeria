package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.exception.*;

import java.util.HashMap;
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
	public abstract HashMap <String, String> getData (String city, String type)  throws IllegalArgumentException, UrlException, 
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
	
	//public abstract HashMap <String, String> getForecasts (String city, String state);
	 
	
}
