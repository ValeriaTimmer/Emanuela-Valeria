package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.model.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Classe che gestisce le statistiche
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class StatsUtils {
	
	/**
	 * Array che contiene le statistiche riguardanti la temperatura
	 */
	private static JSONArray stats = new JSONArray ();
	
	/**
	 * Calcolatore di statistiche
	 */
	StatisticsCalculator calc = new StatisticsCalculator();
	
	/**
	 * Variabile della classe DateUtils
	 */
	private DateUtils d;

	/**
	 * Metodo che effettua le statistiche su umidità o temperatura
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param city Nome della città
	 * @param state Nome dello stato 
	 * @param type Tipo di dato sul quale si vogliono effettuare le statistiche
	 * (temperature / humidity)
	 * @param from Data dal quale si vogliono effettuare le statistiche
	 * @param to Data fino al quale si vogliono effettuare le statistiche
	 * @return stats Array contenente le statistiche 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public JSONArray getStats(JSONArray array, String city, String state, String type, String from, String to) 
			throws DataFormatException, ParseException{
		
		JSONObject objectStats = new JSONObject();
		
        ArrayList<String> allDates = d.date(from, to);
        
        City c = new City(city, state);
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = new JSONObject();
				
				try {
					
					switch (type) {
					
					case "temperature":
						
						for (Object obj1 : allDates) {
							
							if (obj1 instanceof Object) {
							
								Double value = (Double) o1.get("temperature");
								calc.addCounter(value);
							}
						}
						
						break;
					
					case "humidity": 
						
						for (Object obj2 : allDates) {
							
							if (obj2 instanceof Object) {
								
								Double value = (Double) o1.get("humidity");
								calc.addCounter(value);
							}
						}
						break;
					}

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		try {
			
			objectStats.put("city", city);
			objectStats.put("state", state);
			objectStats.put("type", type);
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