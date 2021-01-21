package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;
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
     * Costruttore
     * @param array JSONArray
     */
     public Stats(JSONArray array){
    	super(array);
     }

 	/**
	* Metodo che ritorna l'array contenente i valori delle statistiche 
	* @param array Array contenente tutti i dati 
	* @param city nome della citta
	* @param parameter Parametro(umidita/temperatura) sul quale si effettua la statistica
	* @param from Data d'inizio del periodo sul quale si effettuano le statistiche
	* @param to Data di fine del periodo sul quale si effettuano le statistiche
	* @return ritorna HashMap contenente le statistiche effettuate
	* @throws DataFormatException eccezione personalizzata
	* @throws UrlException Eccezione personalizzata
 	* @throws org.json.simple.parser.ParseException Errore di Parsing 
 	* @throws IOException Errore di I/O
 	* @throws StatsException eccezione personalizzata
  
	*/
	public HashMap <String, String> Statistics (JSONArray array, String city, String parameter, String from, String to) 
			throws DataFormatException, UrlException, org.json.simple.parser.ParseException, IOException,
			StatsException {
		return utils.getStats(getArray(), city, parameter, from, to);
	}
		
}
