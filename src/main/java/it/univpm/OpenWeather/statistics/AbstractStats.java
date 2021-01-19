package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.exception.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.HashMap;
import org.json.simple.JSONArray;

/**
 * Classe astratta che serve per calcolare le statistiche 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public abstract class AbstractStats {

	/**
	 * JSONArray contenente i dati
	 */
	protected JSONArray arr;
	
	/**
	 * Costruttore
	 * @param array JSONArray
	 */
	public AbstractStats (JSONArray array) {
		this.arr = array;
	}
	
	/**
	 * Metodo getter dell'array con i dati
	 * @return arr Array contenente i dati
	 */
	public JSONArray getArray() {
		return this.arr;
	}
	
	/**
	 * Metodo astratto che viene poi implementato nella sottoclasse
	 * @param array array contenente le città
	 * @param city nome della città
	 * @param parameter Parametro(umidità/temperatura) sul quale si effettua la statistica
	 * @param from Data di inizio del periodo su cui si effettuano le statistiche
	 * @param to Data di fine del periodo su cui si effettuano le statistiche
	 * @return ritorna HashMap contenente le statistiche effettuate
	 * @throws IOException Errore di I/O
	 * @throws org.json.simple.parser.ParseException Errore di Parsing
	 * @throws MalformedURLException Errore nel formato dell'URL
	 * @throws UrlException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 */
	public abstract HashMap<String,String> Statistics(JSONArray list, String city, String parameter, String from, String to ) 
			throws ParseException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException, StatsException;
}
