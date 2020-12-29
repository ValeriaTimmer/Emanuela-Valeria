package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.utils.*;
import org.json.simple.JSONArray;
/**
 * classe che richiama la classe astratta per le statistiche riguardanti la temperatura
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */


public class StatsTemperature extends Stats<Object,Object> {
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
	 * @param period periodo
	 * @return l'array delle statistiche sulle città in base alla temperatura
	 */
	@Override
	public JSONArray Statistics(JSONArray arrayCity, Object temperature, Object period) {
		return(JSONArray) utils.getStats(arrayCity, temperature, period);
	}

	
}
