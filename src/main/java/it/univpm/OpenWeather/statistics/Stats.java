package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;
import java.text.ParseException;
import it.univpm.OpenWeather.utils.StatsUtils;
import it.univpm.OpenWeather.exception.*;
import java.util.HashMap;


/**
 * Classe per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class Stats {
	
	/**
	 * Array contenente i dati 
	 */
	private JSONArray arr;
     
     /**
 	 * Variabile utilizzata per richiamare il metodo della classe StatsUtils
 	 */
 	private StatsUtils utils;
    
    /**
     * Costruttore
     * @param arrayCity
     */
     public Stats(JSONArray array){
    	this.arr = array;
    	this.utils = new StatsUtils();
     }
     
     /**
      * Metodo Getter dell'array con i dati 
      * @return arr Array contenete i dati
      */
     public JSONArray getArray () {
    	 return this.arr;
     }
     
 	/**
	* Metodo che ritorna l'array contenente i valori delle satistiche 
	* @param arrayCity Array contenente le città
	* @param parameter Parametro(umidità/temperatura) sul quale si effettua la statistica
	* @param from Data d'inizio del periodo sul quale si effettuano le statistiche
	* @param to Data di fine del periodo sul quale si effettuano le statistiche
	* @return ritorna l'array contenente le statistiche effettuate
	* @throws DataFormatException Eccezione personalizzata
	* @throws ParseException Errore di parsing
	*/
	public HashMap <String, String> Statistics (JSONArray array, String city, String state, String parameter, String from, String to) 
			throws DataFormatException, ParseException, UrlException {
		return utils.getStats(this.getArray(), city, state, parameter, from, to);
	}
		
}
