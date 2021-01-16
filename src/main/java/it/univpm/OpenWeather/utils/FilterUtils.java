package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.model.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe che implemeta i metodi di filtraggio
 * utilizzati dalle classi del package.filter
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class FilterUtils {
	
	/**
	 * Array che contiene i dati filtrati
	 */
	private static JSONArray filtered = new JSONArray();
	
	/**
	 * Metodo che filtra le città in base al nome 
	 * 
	 * @param array Array da filtrare
	 * @param city Nome della città 
	 * @return Filtered Ritorna l'array filtrato con tutte le informazioni di una determinata città
	 */
	public JSONArray getCityFiltered (JSONArray array, Object city) {
		
		City c = new City(city.toString());
		
		JSONParser parser = new JSONParser();
		
		try {
		
			JSONArray arr = (JSONArray) parser.parse(array.toJSONString());
		
			Iterator <Object> val = arr.iterator();
		
			while (val.hasNext()) {
			
				Object o = val.next();
			
				JSONObject obj = (JSONObject) o;

				String citta = (String) obj.get("name");
			
				if (citta.equals(city))
					filtered.add(c.getAllInformation(city.toString()));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (filtered == null || filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			filtered.add(o2);
		}
		
		return filtered;
	}
	
	/**
	 * Metodo che filtra l'umidità compresa tra due valori 
	 * @param array Array da filtrare
	 * @param city Città su cui si vuole applicare il filtro
	 * @param fromTo Intervallo di filtraggio
	 * @return Filtered Ritorna l'array filtrato
	 * @throws RuntimeException se la chiave non viene trovata o se il valore non è 
	 * un oggetto Number che può essere convertito a numero
	 
	public ArrayList<JSONObject> getHumidityFiltered (JSONArray array, Object city, Object period) throws RuntimeException {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					String cities = (String) o1.get("city");
					
					if (city.equals(cities)) {
						
						Double hum = (Double) o1.get("humidity");
						
							filtered.add(o1);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	 * Metodo che filtra la temperatura compresa tra due valori
	 * @param array Array da filtrare
	 * @param temp1 Valore minimo dell'intervallo di temperatura
	 * @param temp2 Valore massimo dell'intervallo di temperatura
	 * @return filtered Ritorna l'array filtrato 
	 * @throws RuntimeException se la chiave non viene trovata o se il valore non è 
	 * un oggetto Number che può essere convertito a numero
	 *
	public ArrayList<JSONObject> getTemperatureFiltered (JSONArray array, Object temp1, Object temp2) throws RuntimeException {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					Double temperatura = (Double) o1.get("temperature");
					
					if(temperatura >= (Double) temp1 && temperatura <= (Double) temp2) {
						filtered.add(o1);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (filtered == null || filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			filtered.add(o2);
		}
		return filtered;
	}
	
	*/
}
