package it.univpm.OpenWeather.statistics;

import it.univpm.OpenWeather.utils.StatsUtilsHumidity;

import org.json.simple.JSONArray;
/**
 * Classe che richiama la classe astratta per le statistiche riguardanti l'umidità
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 * @param <T> parametro generico
 */
public class StatsHumidity extends Stats <Object, Object> {
	
	/**
	 * Variabile utilizzata per richiamare il metodo della classe StatsUtils
	 */
	private StatsUtilsHumidity utils;
	
	/**
	 * Costruttore
	 * @param array della città
	 */
	public StatsHumidity(JSONArray array) {
		super(array);
		this.utils = new StatsUtilsHumidity();
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
	public JSONArray Statistics(JSONArray arrayCity, Object humidity, Object period) {
		return (JSONArray) utils.getStats(this.getHumidity(), humidity, period);
	}
}
