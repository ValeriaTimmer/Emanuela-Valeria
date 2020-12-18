package it.univpm.OpenWeather.utils;

import org.json.simple.JSONArray;

public class StatsUtils {
	/**
	 * Array che contiene le statistiche
	 */
	private static JSONArray stats = new JSONArray();
	
	/**
	 * Metodo che effettua le statistiche 
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param humidity Umidità
	 * @return stats Array contenente le statistiche 
	 */
	public JSONArray getStats(JSONArray array, Object humidity) {
		return stats;
	}
}
