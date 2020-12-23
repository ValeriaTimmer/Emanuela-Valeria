package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.utils.StatsUtils;

import org.json.simple.JSONArray;
/**
 * Classe che implementa l'interfaccia per le statistiche
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public class StatsHumidity implements Stats <Object, Object> {
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
	 * @param array della città
	 */
	public StatsHumidity(JSONArray array) {
		this.arrayCity = array;
		this.utils = new StatsUtils();
	}
	
	/**
	 * Metodo Getter degli array delle città
	 * @return ArrayCity ritorna l'array delle città
	 */
	public JSONArray getHumidity() {
		return arrayCity;
	}
	
	/**
	 * Metodo che effettua l'override dell'interfaccia
	 * @param ArrayCity array delle città
	 * @param Humidity umidità
	 * @return l'array delle statistiche effettuate sulle città in base all'umidità
	 */
	@Override
	public JSONArray StatisticsHumidity (JSONArray arrayCity, Object humidity, Object period) {
		return (JSONArray) utils.getStats(this.getHumidity(), humidity, period);
	}
}
