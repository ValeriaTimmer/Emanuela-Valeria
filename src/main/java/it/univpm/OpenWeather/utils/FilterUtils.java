package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.service.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
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
	 */
	 
	public JSONArray getHumidityFiltered (String type) {
		
		StatisticsCalculator calc = new StatisticsCalculator();
		
		JSONArray filtered = new JSONArray ();
		
		jsonArray = d.Parsing();
		
		for (int i = 0; i<jsonArray.size(); i++) {
			
			JSONObject o = (JSONObject) jsonArray.get(i);
			
			if ((type.toString()).equals("humidity")) {
				
				JSONObject o1 = (JSONObject) o;
				
					Double hum = (Double) o1.get("humidity");
						
						filtered.add(o1);
					}
					
				
			}
		
		if (filtered == null || filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			filtered.add(o2);
		}
		return filtered;
	}
	
}
