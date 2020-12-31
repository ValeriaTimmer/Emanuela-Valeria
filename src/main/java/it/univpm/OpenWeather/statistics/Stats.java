package it.univpm.OpenWeather.statistics;

import org.json.simple.JSONArray;

import it.univpm.OpenWeather.utils.StatsUtils;

/**
 * Interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public class Stats <T, P, Q> {
	/**
	 * Array contenente le città
	 */
     private JSONArray arrayCity;
     
     /**
 	 * Variabile utilizzata per richiamare il metodo della classe StatsUtilsTemperature
 	 */
 	private StatsUtils utils;
    
    /**
     * costruttore
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
	* @param parameter parametro(umidità/temperatura) sul quale si effettua la statistica
	* @param from data d'inizio del periodo sul quale si effettuano le statistiche
	* @param to data di fine del periodo sul quale si effettuano le statistiche
	* @return ritorna l'array contenente le statistiche effettuate
	*/
		
	public JSONArray Statistics (JSONArray arrayCity, T parameter, P from, Q to) {
		return(JSONArray) utils.getStats(arrayCity, parameter, from, to);
	}
		
}
