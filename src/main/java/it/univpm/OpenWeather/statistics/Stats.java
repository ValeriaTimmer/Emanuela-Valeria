package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;
import java.text.ParseException;
import it.univpm.OpenWeather.utils.StatsUtils;
import it.univpm.OpenWeather.exception.*;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;


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
     
     private static Date dateFrom;
     
     private static Date today;
     
     /**
 	 *  DateFormat per il parsing della data in formato: "dd-MM-yyyy"
 	 */
 	 private static DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
 	 
 	 String datainiziale = formatoData.format(dateFrom);
 	 
 	 String data_di_oggi = formatoData.format(today);
     
     /**
 	 * Variabile utilizzata per richiamare il metodo della classe StatsUtilsTemperature
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
 	 * 
 	 * @param from
 	 * @param to
 	 * @return
 	 * @throws DataFormatException
 	 * @throws ParseException
 	 */
 	public static long getPeriod (String from, String to) throws DataFormatException, ParseException {
		if (today.before(dateFrom)) 
			throw new DataFormatException();
		return ((today.getTime()- dateFrom.getTime()) / (24*60*60*1000));
	}
 	
 	/**
	 * Metodo che inserisce in un JSONArray le date comprese tra 
	 * la data di inizio e la data di fine
	 * @param from Data iniziale
	 * @param to Data finale
	 * @return d Vettore contenente le date
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public static JSONArray date (String from, String to) throws DataFormatException, ParseException{
		JSONArray d = new JSONArray();
		Long period = getPeriod(from, to);
		for (int i=0; i<=period; i++) {
			d.add(formatoData.format(dateFrom));
			dateFrom.setTime(dateFrom.getTime()+(24*60*60*1000));
		}
		return d;
	}
 	
 	/**
 	 * metodo che verifica le previsioni
 	 * @return
 	 */
 	public boolean verificaPrevisioni( Double humidity, Double temperature, String from, String to)throws DataFormatException, ParseException {
 		JSONArray d1 = date(datainiziale, data_di_oggi);
 		for(Object ob: d1) {
 			if(ob instanceof Object)
 			 if(humidity.equals(today)&& temperature.equals(today))
 				return true;}
 		      return false;
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
	public JSONArray Statistics (JSONArray arrayCity, String parameter, String from, String to) throws DataFormatException, 
	ParseException {
		return(JSONArray) utils.getStats(arrayCity, parameter, from, to);
	}
		
}
