package it.univpm.OpenWeather.statistics;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.service.*;

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
	 * Contatore
	 */
	private int counter = 0;
	
	/**
	 * Variabile della classe DataBase
	 */
	private DataBase dB;
	
	/**
	 * Metodo che permette di calcolare le previsioni azzeccate di una città
	 * 
	 * @param city Nome della città
	 * @param period Periodo di tempo per il quale si vogliono ottenere i risultati
	 * @return counter Numero di previsioni azzeccate
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException Errore del formato dell'URL
	 * @throws ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 */
	public int correctForecast (String city, long period) throws UrlException, MalformedURLException, ParseException, IOException {
		
		ArrayList <String> d1 = new ArrayList<String>();
	 	
 		LocalDate to = LocalDate.parse (DateUtils.today());
 	 		
 		LocalDate from = to.minusDays(period);
 		
		
		try {
			
			JSONArray jsonArray = dB.getAllData(city);
			
			for (int i = 0; i<jsonArray.size(); i++) {
				
				JSONObject o = (JSONObject) jsonArray.get(i);
				
				String data = (String) o.get("date");
				
				if (data.equals(to.toString())) {
					
					
					
				}
				
				
			}
			
		} catch (org.json.simple.parser.ParseException | IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return this.counter;
	}
 	
 	/**
 	 * Metodo che verifica le previsioni
 	 * @return
 	 */
 	 /**
 	public boolean verificaPrevisioni (JSONArray array,String city, long period) 
 			throws DataFormatException, ParseException {
 		
 		Parser p = new Parser();
 		
 		ArrayList <String> d1 = new ArrayList<String>();
 	
 		LocalDate to = LocalDate.parse (DateUtils.today());
 	 		
 		LocalDate from = to.minusDays(period);
 		
 		d1 = DateUtils.date(from.toString(), to.toString());
 		
 		FilterUtils utils = new FilterUtils();
 		
 		JSONArray ja = (JSONArray) utils.getCityFiltered(p.caricaFile(Config.getName()), city);
 		
 		for (Object o: array) {
 			
 			if (o instanceof Object) {
 				
 				JSONObject obj1 = (JSONObject) o;
 				
 				String data = (String) obj1.get("date");
 				
 				if(data.compareTo(from.toString())>=0) {
						
 					if(data.compareTo(to.toString())<=0) {

 						Double hum = (Double) obj1.get("humidity");
 						Double temp = (Double) obj1.get("temperature");
 						
 						//if (hum.equals(obj) & temp.equals(obj) & descr.equals(anObject))
 							
 							return true;
						
 					}
 				}
 			}
 		      
 		
 	}
 		return false;
 	}
 	*/
}

