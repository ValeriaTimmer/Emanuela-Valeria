package it.univpm.OpenWeather.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.OpenWeather.statistics.StatisticsCalculator;

public class StatsUtilsTemperature {
	/**
	 * Array che contiene le statistiche riguardanti la temperatura
	 */
	private static JSONArray stats = new JSONArray ();
	
	/**
	 * Metodo che effettua le statistiche 
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param temperature temperatura
	 * @param period Periodo
	 * @return stats Array contenente le statistiche 
	 */
	public JSONArray getStats(JSONArray array, Object temperature, Object period) {
		
		JSONObject objectStats = new JSONObject();
		
		StatisticsCalculator calc = new StatisticsCalculator();
		
		Double temperatura = (Double) temperature;
		
		Integer periodo = (Integer) period;
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = new JSONObject();
				
				try {
					
					temperatura = (Double) o1.get("temperature");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		try {
			calc.addCounter(temperatura);
		
			objectStats.put ("min", calc.getMin());
			objectStats.put ("max", calc.getMax());
			objectStats.put ("avg", calc.getAverage());
			objectStats.put ("var", calc.getVariance());
		
			stats.add(objectStats);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
}
