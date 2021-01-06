package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;
import java.text.ParseException;
import it.univpm.OpenWeather.utils.StatsUtils;
import it.univpm.OpenWeather.exception.*;
import java.util.HashMap;

import java.util.Date;
import java.util.Locale;
import java.time.LocalDate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Classe per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class Stats {
	/**
	 * Array contenente le città
	 */
     private JSONArray arrayCity;
     
     /**
 	 * Variabile utilizzata per richiamare il metodo della classe StatsUtils
 	 */
 	private StatsUtils utils;
    
    /**
     * Costruttore
     * @param arrayCity
     */
     public Stats(JSONArray arrayCity) {
    	this.arrayCity = arrayCity;
    	this.utils = new StatsUtils();
     }
     
     /**
 	 * Metodo Getter degli array delle città
 	 * @return ArrayCity ritorna l'array delle città
 	 */
 	 public JSONArray getArray() {
 		return arrayCity;
 	}

 	/**
	* Metodo astratto che viene implementato a seconda dell'esigenza
	* @param arrayCity Array contenente le città
	* @param parameter Parametro(umidità/temperatura) sul quale si effettua la statistica
	* @param from Data d'inizio del periodo sul quale si effettuano le statistiche
	* @param to Data di fine del periodo sul quale si effettuano le statistiche
	* @return ritorna l'array contenente le statistiche effettuate
	* @throws DataFormatException Eccezione personalizzata
	* @throws ParseException Errore di parsing
	*/
	public JSONArray Statistics (JSONArray arrayCity, String city, String state, String parameter, String from, String to) throws DataFormatException, 
	ParseException {
		return(JSONArray) utils.getStats(arrayCity, city, state, parameter, from, to);
	}
		
}
