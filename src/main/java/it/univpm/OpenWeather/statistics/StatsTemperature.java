package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.utils.*;
import org.json.simple.JSONArray;
/**
 * classe che richiama la classe astratta per le statistiche riguardanti la temperatura
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */


public class StatsTemperature extends Stats<Object, Object, Object> {
	/**
	 * Variabile utilizzata per richiamare il metodo della classe StatsUtilsTemperature
	 */
	private StatsUtilsTemperature utils;
	
	/**
	 * costruttore
	 * @param arrayCity array delle città
	 */
	public StatsTemperature(JSONArray arrayCity) {
		super(arrayCity);
		this.utils = new StatsUtilsTemperature();
	}
	
	/**
	 * metodo getter degli array delle città
	 * @return arrayCity array delle città
	 */
	public JSONArray getTemperature() {
		return arrayCity;
	}
	
	/**
	 * metodo che effettua l'override del metodo delle classe astratta
	 * @param arrayCity array delle città
	 * @param temperature temperatura
	 * @param from data d'inizio del periodo su cui si effettuano le statistiche
	 * @param to data di fine del periodo su cui si effettuano le statistiche
	 * @return l'array delle statistiche sulle città in base alla temperatura
	 */
	@Override
	public JSONArray Statistics(JSONArray arrayCity, Object temperature, Object from, Object to) {
		return(JSONArray) utils.getStats(arrayCity, temperature, from, to);
	}

	
}
