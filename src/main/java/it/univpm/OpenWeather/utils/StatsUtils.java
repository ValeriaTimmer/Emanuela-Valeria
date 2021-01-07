package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.service.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe che gestisce le statistiche
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class StatsUtils {
	
	 /**
	  * Oggetto della classe DownloadCity
	  */
	 private DownloadCity d;
	 
	 /**
	  * ArrayList sul quale vengono i dati scelti su cui si effettuano le statistiche
	  */
	 private static ArrayList <Double> arr = new ArrayList<Double>();
	
	/**
	 * Array che contiene le statistiche 
	 */
	//private JSONArray stats = new JSONArray ();
	
	/**
	 * Calcolatore di statistiche
	 */
	StatisticsCalculator calc = new StatisticsCalculator();
	
	/**
	  * Costruttore
	  *
	 public StatsUtils() {
		 this.arrayCity = new JSONArray();
	 }
	
	 /**
	  * Metodo Getter dell'array parsato
	  * @return arraCity Ritorna l'array con i dati parsati
	  *
	public JSONArray getArray() throws UrlException {
 		this.arrayCity = d.Parser();
 		return arrayCity;
 	}
	 */
	
	/**
	 * Metodo che preleva i dati desiderati e li salva in un ArrayList
	 * @param array Array contenente i dati da analizzare
	 * @param type Tipo di dato da analizzare (humidity/temperature)
	 * @param from Data di inizio
	 * @param to Data di fine
	 * @return arr ArrayList che contiene i dati sui quali vengono effettuate le statistiche
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di Parsing
	 */
	public ArrayList<Double> getValues (JSONArray array, String type, String from, String to)
	throws DataFormatException, ParseException {
		
		ArrayList <String> allDates = DateUtils.date(from, to);
		
		try {
	        
        	for (String d : allDates) {
		
        		for (Object o : array) {
			
        			if (o instanceof JSONArray) {
				
        				JSONObject o1 = new JSONObject();
				
        					if (type.equals ("temperature")) {
							
								Double value1 = (Double) o1.get("temperature");
								arr.add(value1);
														
        					}
					
        					if (type.equals("humidity")){ 
						
								Double value2 = (Double) o1.get("humidity");
								arr.add(value2);
        					}
        			}
				
        		}
        
        	}
        
        } catch(Exception e) {
			e.printStackTrace();
		}
		
		return arr;
		
	}
	
	/**
	 * Metodo inserisce le statistiche su un' HashMap
	 * @param array Array sul quale vengono effettuate le statistiche
	 * @param city Nome della città
	 * @param state Nome dello stato 
	 * @param type Tipo di dato sul quale si vogliono effettuare le statistiche
	 * (temperature / humidity)
	 * @param from Data dal quale si vogliono effettuare le statistiche
	 * @param to Data fino al quale si vogliono effettuare le statistiche
	 * @return stats HashMap che contiene i dati delle statistiche 
	 * @throws DataFormatException Eccezione personalizzata
	 * @throws ParseException Errore di parsing
	 */
	public HashMap<String, String> getStats (JSONArray array, String city, String state, String type, String from, String to) 
			throws DataFormatException, ParseException{
		
		HashMap <String, String> stats = new HashMap <String, String>();
		
		try {
			
			stats.put("city", city);
			stats.put("state", state);
			stats.put("type", type);
			stats.put ("min", calc.getMin(this.getValues(array, type, from, to)).toString());
			stats.put ("max", calc.getMax(this.getValues(array, type, from, to)).toString());
			stats.put ("avg", calc.getAverage(this.getValues(array, type, from, to)).toString());
			stats.put ("var", calc.getVariance(this.getValues(array, type, from, to)).toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
}