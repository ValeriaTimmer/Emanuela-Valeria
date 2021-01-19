package it.univpm.OpenWeather.statistics;

import java.io.IOException;

import java.net.MalformedURLException;
import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.filter.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.util.ArrayList;
import java.util.Date;


/**
 * Classe che gestisce le previsioni azzeccate
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class Forecasts {
	
	/**
	 * Variabile della classe DownloadCity
	 */
	private DownloadCity d = new DownloadCity();
	
	/**
	 * JSONArray
	 */
	private JSONArray jsonArray = new JSONArray();
	
 	/**
 	 * Metodo per creare il vettore contenente i valori dell'umidità
 	 * delle previsioni salvate sul file
 	 * 
 	 * @param date Data
 	 * @param city Nome della città
 	 * @return valori ArrayList<Double> contenente i valori dell'umidità
 	 * @throws UrlException Eccezione personalizzata
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException Errore di I/O
 	 */
	public ArrayList<Double> arrayPrevisioni1(String date, String city) throws UrlException, 
		org.json.simple.parser.ParseException, IOException {
		
		ArrayList<Double> valori = new ArrayList<Double>();
		
		ForecastFilter previsioni = new ForecastFilter();
		
		JSONArray previsioniFile= new JSONArray();
		
		JSONArray previsioniPassate = new JSONArray();
		
		previsioniFile = d.getValues(Config.getName2());
		
		previsioniPassate = previsioni.filtersCity(date, city, previsioniFile);
		
		for(int i=0; i<previsioniPassate.size(); i++) {
			
			JSONObject obj = (JSONObject) previsioniPassate.get(i);
			
			Double hum1 = Double.parseDouble(obj.get("humidity").toString());
			
			valori.add(hum1);
			
		}
		
		return valori;
	}
	
	/**
	 * Metodo per andare a parsare dal sito di OpenWeather le previsioni attuali
	 * delle previsioni attuali
	 * 
	 * @param city Nome della città 
	 * @return valori ArrayList<Double> contenente i valori dell'umidità
	 * @throws UrlException eccezione personalizzata
 	 * @throws IOException errore di I/O
	 */
	public JSONArray previsioniAttuali(String city) throws UrlException, IOException {
	
		JSONObject obj = this.d.toOpenWeather(city);
		JSONObject c = (JSONObject) obj.get("city");
			
		String citta = (String) c.get("name");

		JSONArray lista = (JSONArray) obj.get("list");
			
		for (int n = 0; n < lista.size(); n++) {
					
			JSONObject ob2 = (JSONObject) lista.get(n);
					
			JSONObject main = (JSONObject) ob2.get("main");
						
			Double humidity = Double.parseDouble(main.get("humidity").toString());
						
			long unixSeconds = Long.parseLong(ob2.get("dt").toString());
			
				Date d = new Date (unixSeconds*1000L);
			
				Config.formatoData.setTimeZone(java.util.TimeZone.getTimeZone("Europe/Rome"));
			
			String dat = Config.formatoData.format(d);

			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("city", citta);
			jsonObject.put("humidity", humidity);
			jsonObject.put("date", dat);
			
			jsonArray.add(jsonObject);
			
		}

		
		return jsonArray;
			
	}
	
	/**
	 * Metodo per creare il vettore contenente i valori dell'umidità
	 * delle previsioni attuali
	 * 
	 * @param date Data
	 * @param city Nome della città 
	 * @return valori ArrayList<Double> contenente i valori dell'umidità
	 * @throws UrlException eccezione personalizzata
 	 * @throws IOException errore di I/O
	 */
	public ArrayList<Double> arrayPrevisioni2(String date,String city) throws UrlException, IOException {
		
        JSONObject Counter = new JSONObject();
		
		ForecastFilter filter = new ForecastFilter();
		
		ArrayList<Double> valori = new ArrayList<Double>();
		
		JSONArray previsioniNuove = new JSONArray();
		
		previsioniNuove = this.previsioniAttuali(city);
		
		JSONArray previsioni = new JSONArray();
		
		previsioni = filter.filtersCity(date, city, previsioniNuove);
		
		for(int i=0; i<previsioni.size(); i++) {
			
			JSONObject obj = (JSONObject) previsioni.get(i);
			
			Double hum2 = Double.parseDouble(obj.get("humidity").toString());
			
			valori.add(hum2);
		}
		return valori;
	}
	
	
	/**
	 * Metodo per confrontare i valori dell'umidità. Nel caso in cui questi siano uguali,
	 * viene incrementato un contatore, che sta ad indicare il numero di previsioni azzeccate
	 * 
	 * @param date Data
	 * @param city Nome della città
	 * @return obj JSONObject contente il valore del contatore
	 * @throws UrlException eccezione personalizzata
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException errore di I/O
	 */
	public JSONObject confrontaValori(String date, String city) throws UrlException, 
		org.json.simple.parser.ParseException, IOException {
		
		JSONObject obj = new JSONObject();
		
		int counter = 0;
		
		ArrayList<Double> array1 = new ArrayList<Double>();
		
		ArrayList<Double> array2 = new ArrayList<Double>();
		
		array1 = this.arrayPrevisioni1(date,city);
		
		array2 = this.arrayPrevisioni2(date, city);
		
		for(int i=0; i<array1.size(); i++) {
			
		  for(int j=0; j<array2.size(); j++) {
			  
			 if(array2.get(j).equals(array1.get(i)))
				 
				 counter++;
			 
			 }
		  
		  }
		
		obj.put("contatore", counter);
		
		return obj;
	}
 	
}

