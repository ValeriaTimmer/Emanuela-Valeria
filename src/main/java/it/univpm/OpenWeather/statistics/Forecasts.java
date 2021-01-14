package it.univpm.OpenWeather.statistics;

import java.text.ParseException;

import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Classe che gestisce le previsioni azzeccate
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class Forecasts {
	
	/**
	 * Variabile della classe dateUtils
	 */
	private DateUtils dateUtils;
 	
	/**
	 * Metodo che preleva i dati desiderati dal costruttore 
	 * della classe City e li inserisce in un JSONArray
	 * @param c City 
	 * @return JSONArray che contiene i dati desiderati
	 */
	public void returnValues (City c) {
		
		Double hum = c.getHumidity();
		Double temp = c.getTemperature();
		String description = c.getWeather();
				
	}
 	
 	/**
 	 * Metodo che verifica le previsioni
 	 * @return
 	 */
 	 
 	public boolean verificaPrevisioni (JSONArray array, String city, long period) 
 			throws DataFormatException, ParseException {
 		
 		ArrayList <String> d1 = new ArrayList<String>();
 	
 		LocalDate to = LocalDate.parse (dateUtils.today());
 	 		
 		LocalDate from = to.minusDays(period);
 		
 		d1 = dateUtils.date(from.toString(), to.toString());
 		
 		City c = new City (city);
 		
 		for (Object o: array) {
 			
 			if (o instanceof Object) {
 				
 				JSONObject obj1 = new JSONObject();
 				
 				try {
 				
 				Double hum = (Double) obj1.get("humidity");
 				Double temp = (Double) obj1.get("temperature");
 				String descr = (String) obj1.get("weather");
 			
 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 					
 			}
 		      
 		
 	}
 		return false;
 	}
}

