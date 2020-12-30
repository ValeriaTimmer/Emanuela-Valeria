package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.model.City;
import it.univpm.OpenWeather.statistics.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StatsUtilsHumidity {
	
	/**
	 * Array che contiene le statistiche riguardanti l'umidità
	 */
	private static JSONArray stats = new JSONArray ();
	
	/**
	 * Metodo che effettua le statistiche 
	 * @param array Array sul quale vengono effettuate le statistiche 
	 * @param humidity Umidità
	 * @param period Periodo
	 * @return stats Array contenente le statistiche 
	 */
	public JSONArray getStats(JSONArray array, Object humidity, Object from, Object to) {
		
		JSONObject objectStats = new JSONObject();
		
		StatisticsCalculator calc = new StatisticsCalculator();
		
		Double umidita = (Double) humidity;
		
		Date data1 = (Date) from;
		
		Date data2 = (Date)to;
		
		/**DateFormat formatoData = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        String datainiziale= formatoData.format(data1);
        String datafinale= formatoData.format(data2);
        */
		
		for (Object o : array) {
			
			
			if (o instanceof Object) {
				
				JSONObject o1 = new JSONObject();
				
				try {
					
					umidita = (Double) o1.get("humidity");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		try {
			calc.addCounter(umidita);
		
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
