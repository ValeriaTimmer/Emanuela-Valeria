package it.univpm.OpenWeather.statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import it.univpm.OpenWeather.exception.DataFormatException;
import it.univpm.OpenWeather.exception.UrlException;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.filter.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.HashMap;
import java.time.LocalDate;

/**
 * Classe che gestisce le previsioni azzeccate
 * 
 * @author Emanuela Saleggia 
 * @author Valeria Timmer
 *
 */
public class Forecasts {

	//private ForecastFilter filter = new ForecastFilter();
	
	private DownloadCity d = new DownloadCity();
	
	private JSONArray jsonArray = new JSONArray();
	
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
	 * @throws org.json.simple.parser.ParseException 
	 * @throws UrlException Eccezione personalizzata
	 * @throws MalformedURLException Errore del formato dell'URL
	 * @throws ParseException Errore di Parsing
	 * @throws IOException Errore di I/O
	 
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
	*/
 	/**
 	 * metodo per creare il vettore contenente i valori dell'umidità
 	 * delle previsioni salvate sul file
 	 * @param date data
 	 * @param city nome della città
 	 * @return valori ArrayList<Double> contenente i valori dell'umidità
 	 * @throws UrlException eccezione personalizzata
 	 * @throws MalformedURLException  eccezione che viene lanciata nel caso in cui l'URL non è corretto
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException errore di I/O
 	 */
	public ArrayList<Double> arrayPrevisioni1(String date, String city) throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException {
		
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
	 * metodo per andare a parsare dal sito di OpenWeather le previsioni attuali
	 * delle previsioni attuali
	 * @param city nome della città 
	 * @return valori ArrayList<Double> contenente i valori dell'umidità
	 * @throws UrlException eccezione personalizzata
	 * @throws MalformedURLException  eccezione che viene lanciata nel caso in cui l'URL non è corretto
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException errore di I/O
	 */
	public JSONArray previsioniAttuali(String city) throws UrlException, MalformedURLException, IOException {
		
        JSONParser parser = new JSONParser();
		
		try {
			URLConnection openConnection = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" +city+ "&appid=" + Config.getApiKey()).openConnection();
			InputStream in = openConnection.getInputStream();
			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader (in);
				BufferedReader buf = new BufferedReader (inR);
				while ((line = buf.readLine()) != null) data +=line;
				
			} finally  {
				in.close();
			}
			JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
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

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
			
	}
	
	/**
	 * metodo per creare il vettore contenente i valori dell'umidità
	 * delle previsioni attuali
	 * @param date data
	 * @param city nome della città 
	 * @return valori ArrayList<Double> contenente i valori dell'umidità
	 * @throws UrlException eccezione personalizzata
	 * @throws MalformedURLException  eccezione che viene lanciata nel caso in cui l'URL non è corretto
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException errore di I/O
	 */
	public ArrayList<Double> arrayPrevisioni2(String date,String city) throws UrlException, MalformedURLException, IOException {
		
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
	 * metodo per confrontare i valori dell'umidità. Nel caso in cui questi siano uguali,
	 * viene incrementato un contatore, che sta ad indicare il numero di previsioni azzeccate
	 * @param date data
	 * @param city nome della città
	 * @return obj JSONObject contente il valore del contatore
	 * @throws UrlException eccezione personalizzata
	 * @throws MalformedURLException  eccezione che viene lanciata nel caso in cui l'URL non è corretto
 	 * @throws org.json.simple.parser.ParseException errore di Parsing
 	 * @throws IOException errore di I/O
	 */
	public JSONObject confrontaValori(String date, String city) throws UrlException, MalformedURLException, org.json.simple.parser.ParseException, IOException {
		
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

