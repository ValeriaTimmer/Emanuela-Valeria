package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.service.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.ParseException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.net.MalformedURLException;

/**
 * Classe che gestisce le statistiche
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class StatsUtils{
	
	private HashMap<String,String> stats;
	 
	 /**
	  * ArrayList sul quale vengono i dati scelti su cui si effettuano le statistiche
	  */
	 private ArrayList <Integer> arr;
	
	/** 
	 * Array che contiene le statistiche 
	 */
	//private JSONArray stats = new JSONArray ();
	
	/**
	 * Calcolatore di statistiche
	 */
	private StatisticsCalculator calc;
	
	/**
	  * Costruttore
	  */
	 public StatsUtils() {
		 this.arr = new ArrayList<Integer>();
		 this.calc = new StatisticsCalculator();
		 this.stats = new HashMap<String,String>();
	 }
	 
	 
	
	 /**
	  * Metodo Getter dell'array parsato
	  * @return arraCity Ritorna l'array con i dati parsati
	  
	public JSONArray getArray() throws UrlException {
 		this.arrayCity = d.Parser();
 		return arrayCity;
 	}
	*/
	 
	 public ArrayList<Integer> getArray(){
		 return arr;
	 }
	 
	 public void setArray(ArrayList<Integer> array) {
		 this.arr = array;
	 }
	
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
	public void getValues (JSONArray array, String type, String from, String to)
	throws DataFormatException, ParseException {
		
		ArrayList <String> allDates = DateUtils.date(from, to);
	        
        	for (int i=0; i< allDates.size(); i++) {
        		 
        		 for(Object o: array) {
     				if(o instanceof JSONObject) {
     					JSONObject o1 = (JSONObject) o;
     					if(type.equals("humidity")) {
     						Integer value2 = (Integer) o1.get("humidity");
     						this.arr.add(value2);
     						this.setArray(arr);
     					}
     					
     				}
     			}
						
        	}
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
	 * @throws ParseException Errore di parsing
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public HashMap<String, String> getStats(JSONArray array, String city, String state, String type, String from, String to) 
			throws ParseException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException{
		
		//HashMap <String, String> stats = new HashMap <String, String>();
		this.getValues(array, type, from, to);
		
		try {
			
			stats.put("city", city);
			stats.put("state", state);
			stats.put("type", type);
			stats.put ("min", calc.getMin(getArray()).toString());
			stats.put ("max", calc.getMax(getArray()).toString());
			stats.put ("avg", calc.getAverage(getArray()).toString());
			stats.put ("var", calc.getVariance(getArray()).toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
}