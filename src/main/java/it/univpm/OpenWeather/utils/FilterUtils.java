package it.univpm.OpenWeather.utils;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * Classe che implemeta i metodi di filtraggio
 * utilizzati dalle classi del package.filter
 * 
 * @author Emanuela Saleggia
 * @author ValeriaTimmer
 *
 */
public class FilterUtils {
	
	/**
	 * Array che contiene i dati filtrati
	 */
	private static JSONArray Filtered = new JSONArray();
	
	/**
	 * Metodo che filtra le città in base al nome e allo stato
	 * @param array Array di Città
	 * @param city Nome della città
	 * @param state Nome dello stato 
	 * @return Filtered Ritorna l'array filtrato
	 */
	public JSONArray getCityFiltered(JSONArray array, Object city, Object state) {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					JSONObject citta = (JSONObject) o1.get("name");
					if (o1.get("country").equals((String)state) & citta.get("name").equals((String)city)) {
						Filtered.add(o1);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (Filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			Filtered.add(o2);
		}
		
		return Filtered;
	}
	
	/**
	 * Metodo che filtra l'umidità e il tempo 
	 * @param array Array da filtrare
	 * @param humidity Umidità
	 * @param weather Descrizione meteo
	 * @return Filtered Ritorna l'array filtrato
	 */
	public JSONArray getHumidityFiltered (JSONArray array, Object humidity, Object weather) {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					JSONObject umidita = (JSONObject) o1.get("humidity");
					if (o1.get("description").equals((String)weather) & umidita.get("humidity").equals((Double)humidity)) {
						Filtered.add(o1);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (Filtered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			Filtered.add(o2);
		}
		return Filtered;
	}

}
