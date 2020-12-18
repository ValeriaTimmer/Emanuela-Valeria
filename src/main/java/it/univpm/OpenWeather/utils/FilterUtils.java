package it.univpm.OpenWeather.utils;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class FilterUtils {
	
	/**
	 * Array che contiene le città filtrate
	 */
	private static JSONArray cityFiltered = new JSONArray();
	
	/**
	 * Metodo che filtra le città in base al nome e allo stato
	 * @param array Array di Città
	 * @param city Nome della città
	 * @param state Nome dello stato 
	 * @return
	 */
	public JSONArray getCityFiltered(JSONArray array, Object city, Object state) {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					JSONObject citta = (JSONObject) o1.get("name");
					if (o1.get("country").equals((String)state) & citta.get("name").equals((String)city)) {
						cityFiltered.add(o1);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (cityFiltered.isEmpty()) {
			JSONObject o2 = new JSONObject();
			o2.put("Filtraggio abortito", "");
			cityFiltered.add(o2);
		}
		
		return cityFiltered;
	}
	

}
