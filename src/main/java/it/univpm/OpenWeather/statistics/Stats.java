package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;
import java.text.ParseException;
import it.univpm.OpenWeather.utils.StatsUtils;
import it.univpm.OpenWeather.exception.*;
import java.util.HashMap;
import java.io.*;
import java.net.MalformedURLException;


/**
 * Classe per le statistiche che estende la classe astratta
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class Stats extends AbstractStats{
     
     /**
 	 * Variabile utilizzata per richiamare il metodo della classe StatsUtils
 	 */
 	private StatsUtils utils;
    
    /**
     * Costruttore
     * @param arrayCity
     */
     public Stats(JSONArray array){
    	super(array);
    	this.utils = new StatsUtils();
     }
     
     
 	/**
	* Metodo che ritorna l'array contenente i valori delle statistiche 
	* @param array Array contenente le città
	* @param city nome della città
	* @param state sigla dello stato della città
	* @param parameter Parametro(umidità/temperatura) sul quale si effettua la statistica
	* @param from Data d'inizio del periodo sul quale si effettuano le statistiche
	* @param to Data di fine del periodo sul quale si effettuano le statistiche
	* @return ritorna l'array contenente le statistiche effettuate
	* @throws ParseException Errore di parsing
 	 * @throws org.json.simple.parser.ParseException 
 	 * @throws IOException 
 	 * @throws MalformedURLException 
	*/
	public HashMap <String, String> Statistics (JSONArray array, String city, String state, String parameter, String from, String to) 
			throws ParseException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException {
		return utils.getStats(getArray(), city, state, parameter, from, to);
	}
		
}
