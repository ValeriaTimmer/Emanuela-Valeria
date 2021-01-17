package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;



/**
 * Classe che gestisce le statistiche
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class StatsUtils{
	
	/**
	 * HashMap che contiene i valori richiesti
	 */
	private HashMap<String,String> stats;
	 
	 /**
	  * ArrayList sul quale vengono i dati scelti su cui si effettuano le statistiche
	  */
	 private ArrayList <Double> arr;
	
	/**
	 * Calcolatore di statistiche
	 */
	private StatisticsCalculator calc;
	
	/**
	  * Costruttore
	  */
	 public StatsUtils() {
		 this.arr = new ArrayList<Double>();
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
	 
	 public ArrayList<Double> getArray(){
		 return arr;
	 }
	 
	 public void setArray(ArrayList<Double> array) {
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
	public ArrayList<Double> getValues (JSONArray list, String type, String from, String to)
	throws DataFormatException, ParseException {
		
		ArrayList <String> allDates = DateUtils.date(from, to);
	        
        	for (int i = 0; i< allDates.size(); i++) {
        		 
        		 for(Object o: list) {
        			 
     				if(o instanceof JSONObject) {
     					
     					JSONObject o1 = (JSONObject) o;
     					String data = (String) o1.get("date");
     					
     					if(data.compareTo(from)>=0) {
     						if(data.compareTo(to)<=0) {
                         
     					   if(type.equals("humidity")) {
     						  Double value2 = (Double) o1.get("humidity");
     						  this.arr.add(value2);
     						  this.setArray(arr);
     					    }
     					
     					    if(type.equals("temperature")) {
     						    Double value = (Double) o1.get("temperature");
     						    this.arr.add(value);
     						    this.setArray(arr);
     					}
     				 }
     			  }
     			}
						
        	 }
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
	 * @throws ParseException Errore di parsing
	 * @throws org.json.simple.parser.ParseException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public HashMap<String, String> getStats(JSONArray list, String city, String type, String from, String to) 
			throws ParseException, UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException{
		
		
		try {
			
			stats.put("city", city);
			stats.put("type", type);
			stats.put ("min", calc.getMin(this.getValues(list, type, from, to)).toString());
			stats.put ("max", calc.getMax(this.getValues(list, type, from, to)).toString());
			stats.put ("avg", calc.getAverage(this.getValues(list, type, from, to)).toString());
			stats.put ("var", calc.getVariance(this.getValues(list, type, from, to)).toString());
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return stats;
	}
}