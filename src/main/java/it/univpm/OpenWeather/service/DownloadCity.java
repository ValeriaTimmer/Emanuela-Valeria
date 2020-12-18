package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.service.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe che serve per parsare il JSON ricevuto dall'API e popola i campi
 * Humidity, Temperature, Weather
 * I campi cityName e stateCode vengono passati come argomento
 * 
 * @author ValeriaTimmer
 * @author Emanuela Saleggia
 *
 */
public class DownloadCity {
	/*
	 * Nome della città
	 */
	protected String cityName;
	/*
	 * Nome del paese/stato della città
	 */
	protected String stateCode;
	/**
	 * Umidità della città in percentuale
	 */
	protected int humidity;
	/**
	 * Temperatura della città in kelvin
	 */
	 protected double temperature;
	/**
	 * Descrizione del meteo della città
	 */
	protected String weather;
	/**
	 * API privata 
	 */
	private String apiKey;
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	
	public DownloadCity (String Name, String Code, String apiKey) {
		this.cityName = Name;
		this.stateCode = Code;
		this.apiKey = apiKey;
	}
	
	/**
	 * Metodo Getter del nome della città
	 * @return cityName Ritorna il nome della città
	 */
	public String getCityName() {
		return cityName;
	}
	
	/**
	 * Metodo Getter dello Stato/Paese della città
	 * @return stateCode Ritorna la sigla dello stato/paese della città
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * Metodo Getter dell'umidità della città
	 * @return humidity Ritorna l'umidità della città
	 */
	public int getHumidity() {
		return humidity;
	}
	/**
	 * Metodo Getter della temperatura della città in kelvin
	 * @return temperature Ritorna la temperatura della città
	 */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	 * Metodo Getter della descrizione meteo della città
	 * @return weather Ritorna la descrizione del meteo della città
	 */
	public String getWeather() {
		return weather;
	}
	
	/**
	 * Metodo che permette di Parsare i campi desiderati del JSON 
	 */
	public void Parser() {
		
		JSONParser parser = new JSONParser();
		
		try {
			
			// URL di OpenWeather da Parsare
			URL OW = new URL ("api.openweathermap.org/data/2.5/forecast?q=" + this.cityName + 
					"," + this.stateCode + "&appid=" + this.apiKey);
			HttpsURLConnection yc = (HttpsURLConnection) OW.openConnection();
			yc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
			
			BufferedReader in = new BufferedReader (new InputStreamReader (yc.getInputStream()));
			
			String inputLine;
			
			// Creo un ciclo while che controlla che il mio contenuto di input non sia vuoto
			while ((inputLine = in.readLine()) != null) {
				
				// Analizzo l'oggetto city del JSON: Parse del contenuto dell'oggetto city
				JSONObject city = (JSONObject) parser.parse("city");
				
				// Analizzo l'oggetto list del JSON: Parse del contenuto dell'oggetto list
				JSONObject list = (JSONObject) parser.parse("list");
				
				// Analizzo l'array list del JSON: Parse del contenuto dell'array list
				JSONArray arr = (JSONArray) list.get("list");
				
				// Analizzo l'array weather (oggetto dell'array
				// list) del JSON: Parse del contenuto dell'array weather
				JSONArray arr2 = (JSONArray) list.get("weather");
				
				// Loop per ogni item dell'array list
				for (Object obj1 : arr) {
					JSONObject op = (JSONObject) obj1;
					try {
						this.humidity = Integer.parseInt(op.get ("humidity").toString());
						this.temperature = Double.parseDouble(op.get("temp").toString());
							
						for (Object obj2 : arr2) {
								JSONObject op2 = (JSONObject) obj2;
								this.weather = (String) op2.get("description");
							}
					} catch (Exception e) {
						// Errore nel prelevamento dei parametri 
						e.printStackTrace();
					}
				}
				
				this.cityName = (String) city.get("name");
				this.stateCode = (String) city.get ("country");
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
