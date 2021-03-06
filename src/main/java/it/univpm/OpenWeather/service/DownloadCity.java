package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.utils.*;

import java.util.Date;

import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;



/**
 * Classe che effettua il collegamento con il sito 
 * e salva i dati in un file.json 
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
 

@Component
public class DownloadCity {
		
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	/**
	 * JSONArray che contiene tutti i valori prelevati da OpenWeather
	 */
	private JSONArray download = new JSONArray();
	
	/**
	 * Nome della citta
	 */
	private String cityName;
	
	/**
	 * Umidità della citta in percentuale
	 */
	private double humidity;
	
	/**
	 * Temperatura della citta in kelvin
	 */
	private double temperature;
	
	/**
	 * Data
	 */
	private String date;
	
	/**
	 * Costruttore che prende in ingresso il nome della citta e il suo stato/paese
	 * 
	 * @param Name Nome della citta
	 */
	public DownloadCity (String Name) {
		this.cityName = Name;
		this.obj = new JSONObject();
	}
	
	/**
	 * Costruttore senza parametri
	 */
	public DownloadCity() {
	}
	
	/**
	 * Metodo Gettere del nome della citta
	 * @return cityName Nome della citta
	 */
	public String getCityName () {
		return cityName;
	}
	
	/**
	 * Metodo Getter del valore dell'umidita
	 * @return humidity Valore dell'umidita
	 */
	public double getHumidity () {
		return humidity;
	}
	
	/**
	 * Metodo Getter del valore della temperatura
	 * @return temperature Valore della temperatura
	 */
	public double getTemperature () {
		return this.temperature;
	}
	
	/**
	 * Metodo Getter del JSONObject
	 * @return obj JSONObject
	 */
	public JSONObject getObject() {
		return this.obj;
	}
	
	/**
	 * Metodo Setter del JSONObject
	 * @param obj JSONObject
	 */
	public void setObject(JSONObject obj) {
		this.obj = obj;
	}

	/**
	 * Metodo per convertire la temperatura da Kelvin in Celsius
	 * @param temperatura valore in kelvin
	 * @return valore in celsius
	 */
	public int getTemperaturaInCelsius(double temperatura) {
		return (int)(temperatura - 273.15);
	}
	
	/**
	 * Metodo che effettua il collegamento con il sito di OpenWeather
	 * 
	 * @param cityName Nome della città di cui si vogliono ottenere i valori
	 * @return obj JSONObject contenente tutti i valori della citta
	 */
	public JSONObject toOpenWeather (String cityName) {
		
		
		try {
			URLConnection openConnection = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" +cityName+ "&appid=" + Config.getApiKey()).openConnection();
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
			this.obj = (JSONObject) JSONValue.parseWithException(data);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this.obj;
	}
	
	/**
	 * Metodo che permette di aggiungere un JSONObject in un JSONArray
	 * 
	 * @param cityName Nome della citta
	 * @return download JSONArray contenente i JSONObject
	 */
	public JSONArray insertObject(String cityName) {
		this.download.add(toOpenWeather(cityName));
		return this.download;
	}
	
	/**
	 * Metodo che permette di salvare i valori scaricati da OpenWeather in un file
	 * 
	 * @param nome_file Nome del file in cui vengono salvati i dati 
	 * @param cityName Nome della città di cui si vogliono salvare i dati 
	 */
	public void saveValues(String nome_file, String cityName) {
		
		try {
			
			PrintWriter file_output = new PrintWriter (new BufferedWriter (new FileWriter (nome_file)));
			file_output.println(insertObject(cityName));
			file_output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Metodo che permette di leggere i dati da un file
	 * 
	 * @param nome_file Nome del file da cui si vogliono leggere i dati
	 * @return download JSONArray contenente tutti i dati letti
	 */
	public JSONArray getValues(String nome_file) {
			
			String data = "";
			String line = "";
			JSONArray download = new JSONArray();
			
			try {
				String str = "";
				Scanner file_input = new Scanner (new BufferedReader (new FileReader (nome_file)));
				while(file_input.hasNext()) {
				    str = file_input.nextLine();
				}
				download = (JSONArray) JSONValue.parseWithException(str);
				
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return download;
		}
	
	/**
	 * Metodo che permette di selezionare i dati desiderati da un JSONArray
	 * @param nome_file nome del file
	 * @return values JSONArray contenete i dati desiderati 
	 */
	public JSONArray Parsing (String nome_file) {
		
		JSONArray jsonArray = new JSONArray();
		
		JSONArray arr = this.getValues(nome_file);
		
		for (int i = 0; i<arr.size(); i++) {
		
			JSONObject ob = (JSONObject) arr.get(i);
				
			JSONObject citta = (JSONObject) ob.get("city");
				
				this.cityName = (String) citta.get("name");

			JSONArray lista = (JSONArray) ob.get("list");
				
			for (int n = 0; n < lista.size(); n++) {
						
				JSONObject ob2 = (JSONObject) lista.get(n);
						
				JSONObject main = (JSONObject) ob2.get("main");
							
				this.humidity = Double.parseDouble(main.get("humidity").toString());
					Double temp = Double.parseDouble(main.get("temp").toString());
				this.temperature = this.getTemperaturaInCelsius(temp);
							
				long unixSeconds = Long.parseLong(ob2.get("dt").toString());
				
					Date d = new Date (unixSeconds*1000L);
				
					Config.formatoData.setTimeZone(java.util.TimeZone.getTimeZone("Europe/Rome"));
				
				this.date = Config.formatoData.format(d);

				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("city", this.cityName);
				jsonObject.put("humidity", this.humidity);
				jsonObject.put("temperature", this.temperature);
				jsonObject.put("date", this.date);
				
				jsonArray.add(jsonObject);
				
			}
		}
		
		return jsonArray;
		
	}
}
	
