package it.univpm.OpenWeather.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Date;
import java.text.DateFormat;
import java.util.*;

import it.univpm.OpenWeather.statistics.StatisticsCalculator;

public class StatsUtils {
	/**
	 * Array che contiene le statistiche riguardanti la temperatura
	 */
	private static JSONArray stats = new JSONArray ();
	
	/**
	 * Metodo che effettua le statistiche 
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param type Tipo di dato sul quale si vogliono effettuare le statistiche
	 * (temperature / humidity)
	 * @param from Data dal quale si vogliono effettuare le statistiche
	 * @param to Data fino al quale si vogliono effettuare le statistiche
	 * @return stats Array contenente le statistiche 
	 */
	public JSONArray getStats(JSONArray array, Object type, Object from, Object to) {
		
		JSONObject objectStats = new JSONObject();
		
		StatisticsCalculator calc = new StatisticsCalculator();
		
		Date data1 = (Date) from;
		
		Date data2 = (Date) to;
		
		/*
		 * Metodo che permette di ottenere la data formattata con lo stile di formattazione specificato 
		 * per le impostazioni internazionali predefinite
		 * - DateFormat.SHORT indica che la data è completamente numerica
		 * - Locale.ITALY indica che la data sarà nella forma dd/m/yyyy
		 */
		DateFormat formatoData = DateFormat.getDateInstance (DateFormat.SHORT, Locale.ITALY);
        
		String datainiziale = formatoData.format(data1);
        
		String datafinale = formatoData.format(data2);
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = new JSONObject();
				
				try {
					
					String tipo = (String) type;
					
					if (tipo.equals("temperature"))
						type = (Double) o1.get("temperature");
					
					else if (tipo.equals("humidity"))
						type = (Double) o1.get("humidity");
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		try {
			
			calc.addCounter((Double)type);
		
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
