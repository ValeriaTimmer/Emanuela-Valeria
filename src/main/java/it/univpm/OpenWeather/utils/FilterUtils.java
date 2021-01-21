package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.statistics.*;

import org.json.simple.JSONObject;

import com.sun.el.parser.ParseException;

import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
	 * Variabile della classe DownloadCity
	 */
	private DownloadCity d = new DownloadCity();
	
	/**
	 * Costruttore
	 * @param file nome del file
	 */
	public FilterUtils (String file) {
		this.jsonArray = d.Parsing(file);
	}
	
	/**
	 * Metodo che filtra le citta in base al nome 

	 * @param city Nome della citta
	 * @return filtered Ritorna l'array filtrato con tutte le informazioni di una determinata citta
	 */
	public JSONArray getCityFiltered (String city,String file) {
		
		JSONArray filtered = new JSONArray();

		jsonArray = d.Parsing(file);
		
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
	 * Metodo che serve per ritornare le statistiche giornaliere
	 * di un determinato periodo
     * @param city nome della citta
	 * @param type Tipo di valore che si vuole ottenere (umidita/temperatura)
	 * @param from data di inizio del periodo
	 * @param to data di fine del periodo
	 * @return filtered Ritorna l'array filtrato
	 * @throws org.json.simple.parser.ParseException errore di Parsing
	 * @throws UrlException eccezione personalizzata
	 * @throws IOException errore di I/O
	 * @throws StatsException eccezione personalizzata
	 * @throws DataFormatException eccezione personalizzata
	 */
	 
	public JSONArray getTypeFiltered(String city, String type, String from, String to) throws UrlException, 
	IOException, StatsException, DataFormatException, org.json.simple.parser.ParseException {
		
	         JSONArray array = new JSONArray();
	         
	         ArrayList<String> allDates = new ArrayList<String>();
	         
	         HashMap<String,String> dailystats = new HashMap<String,String>();
	         
	         DataBase dB = new DataBase();
	         
	         allDates = DateUtils.date(from, to);
	         
	         for(Object o : allDates) {
	        	 if(o instanceof String) {
	        		 String data = (String) o;
	        		 Stats s = new Stats(dB.getAllDataStorico(city));
	        		 dailystats = s.Statistics(s.getArray(), city,type,data,data);
	        		 array.add(data);
	        		 array.add(dailystats);
	        	 }
	         }
	         return array;
		}
		
		
	
	
	/**
	 * metodo che filtra le citta in base al nome e alla data
	 * @param date data
	 * @param city nome della citta
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
