package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;

import org.json.simple.JSONObject;

import com.sun.el.parser.ParseException;

import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Classe che implemeta i metodi di filtraggio
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class FilterUtils {
	
	/**
	 * JSONArray
	 */
	private JSONArray jsonArray = new JSONArray();
	
	/**
	 * ArrayList<String>
	 */
	private ArrayList <String> arrayList = new ArrayList<String>();
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * Variabile della classe @it.univpm.OpenWeather.service.DownloadCity
	 */
	private DownloadCity d = new DownloadCity();
	
	/**
	 * Costruttore
	 */
	public FilterUtils () {
		this.jsonArray = d.Parsing();
	}
	
	/**
	 * Metodo che filtra le città in base al nome 

	 * @param city Nome della città 
	 * @return filtered Ritorna l'array filtrato con tutte le informazioni di una determinata città
	 */
	public JSONArray getCityFiltered (String city) {
		
		JSONArray filtered = new JSONArray();

		jsonArray = d.Parsing();
		
		for (int i = 0; i< jsonArray.size(); i++) {
	
			JSONObject o = (JSONObject) jsonArray.get(i);
			
			String citta = (String) o.get("city");
			
				if (citta.equals(city)) {
						
						Double hum = Double.parseDouble(o.get("humidity").toString());
						Double temp = Double.parseDouble(o.get("temperature").toString());
						String data = (String) o.get("date");
						
						JSONObject jsonObject = new JSONObject();
					
						jsonObject.put("city", citta);
						jsonObject.put("humidity", hum);
						jsonObject.put("temperature", temp);
						jsonObject.put("date", data);
					
						filtered.add(jsonObject);
				}
		}
						
		if (filtered == null || filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			filtered.add(o2);
		}
		
		return filtered;
	}
	
	/**
	 * Metodo che filtra il valore massimo e il valore minimo di 
	 * umidità/temperatura registrati nel DataBase

	 * @param type Tipo di valore che si vuole ottenere (umidità/temperatura)
	 * @return filtered Ritorna l'array filtrato
	 * @throws org.json.simple.parser.ParseException 
	 */
	 
	public JSONArray getTypeFiltered(String city, String type, String from, String to) throws UrlException, ParseException, 
	IOException, StatsException, DataFormatException, org.json.simple.parser.ParseException {
		
	         
			String date2 = "";
			
			StatsUtils utils = new StatsUtils();
			
			DataBase db = new DataBase();
			
			ArrayList<String> allDates = DateUtils.date(from, to);
			
			ArrayList<Double> array = new ArrayList<Double>();
			
			StatisticsCalculator s = new StatisticsCalculator();
			
			JSONArray jsonArray = new JSONArray();
			
			JSONArray data = new JSONArray();
			
			data = db.getAllData(city);
			
			for(int i=0; i<allDates.size(); i++) {
				
				String date1 = allDates.get(i);
			
				
				for( int j=0; j<data.size() ; j++) {
					JSONObject obj = (JSONObject) data.get(j);
					
					date2 = (String) obj.get("date");
					
					
				     if(date2.equals(date1)) {
						if(type.equals("humidity")) {
							for(Object ol: data) {
								JSONObject json = (JSONObject) ol;
							
							    Double hum = Double.parseDouble(json.get("humidity").toString());
							    array.add(hum);
							}
						}
						if(type.equals("temperature")) {
							for(Object o1:data) {
								JSONObject json1 = (JSONObject) o1;
							
							Double temp = Double.parseDouble(json1.get("temperature").toString());
							array.add(temp);
							}
						}
						
						JSONObject o = new JSONObject();
						o.put("city", city);
						o.put("date", date1);
						o.put("min", s.getMin(array));
						o.put("max", s.getMax(array));
						o.put("avg", s.getAverage(array));
						o.put("var", s.getVariance(array));
						
						jsonArray.add(o);
				     }
				}
			
			     }
			
			return jsonArray;
			
		}
		
		
	
	
	/**
	 * metodo che filtra le città in base al nome e alla data
	 * @param date data
	 * @param city nome della città
	 * @param array JSONArray da filtrare
	 * @return filtered ritorna l'array filtrato
	 */
	public JSONArray getDateFiltered(String date, String city, JSONArray array) {
		JSONArray filtered = new JSONArray();
		
		for(int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				String data = (String) obj.get("date");
				if(data.equals(date)) {
					String citta = (String) obj.get("city");
					if(citta.equals(city)) {
						double humidity = Double.parseDouble(obj.get("humidity").toString());
						JSONObject newJSON = new JSONObject();
						newJSON.put("date", data);
						newJSON.put("city", citta);
						newJSON.put("humidity", humidity);
						
						filtered.add(newJSON);
					}
					}
				}
		
		if(filtered == null || filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			filtered.add(o2);
		}
		return filtered;
	}
	
}
