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
public class StatsHumidity extends Stats <Object, Object, Object> {
	
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
	 * @param from data d'inizio del periodo su cui si effettuano le statistiche
	 * @param to data di fine del periodo su cui si effettuano le statistiche
	 * @return l'array delle statistiche effettuate sulle città in base all'umidità
	 */
	@Override
	public JSONArray Statistics(JSONArray arrayCity, Object humidity, Object from,Object to) {
		return (JSONArray) utils.getStats(this.getHumidity(), humidity, from, to);
	}
}
