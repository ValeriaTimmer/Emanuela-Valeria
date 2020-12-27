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
	private static JSONArray filtered = new JSONArray();
	
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
	 * Metodo che filtra l'umidità compresa tra due valori 
	 * @param array Array da filtrare
	 * @param humidity1 Valore minimo dell'intervallo di umidità 
	 * @param humidity2 Valore massimo dell'intervallo di umidità
	 * @return Filtered Ritorna l'array filtrato
	 * @throws RuntimeException se la chiave non viene trovata o se il valore non è 
	 * un oggetto Number che può essere convertito a numero
	 */
	public JSONArray getHumidityFiltered (JSONArray array, Object humidity1, Object humidity2) throws RuntimeException {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					Double umidita = (Double) o1.get("humidity");
					
					if(umidita >= (Double) humidity1 && umidita <= (Double)humidity2) {
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
	 */
	public JSONArray getTemperatureFiltered (JSONArray array, Object temp1, Object temp2) throws RuntimeException {
		
		for (Object o : array) {
			
			if (o instanceof Object) {
				
				JSONObject o1 = (JSONObject) o;
				
				try {
					
					Double umidita = (Double) o1.get("humidity");
					
					if(umidita >= (Double) temp1 && umidita <= (Double) temp2) {
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

}
