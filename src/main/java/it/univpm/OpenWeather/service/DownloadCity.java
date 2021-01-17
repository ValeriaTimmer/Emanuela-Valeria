package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.model.*;
import it.univpm.OpenWeather.exception.*;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.nio.file.Files;

import org.json.simple.JSONValue;
//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.lang.reflect.Array;
import java.lang.Number;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.StandardOpenOption;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
/**
 * Classe che serve per parsare il JSON ricevuto dall'API e popola i campi
 * Humidity, Temperature
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
 

@Component
public class DownloadCity {
	
	/**
	 * Variabile della classe Parser
	 */
	private Parser p = new Parser();
	
	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();
	
	private JSONObject jsonOb = new JSONObject();
	
	private JSONArray array = new JSONArray();
	
	/**
	 * JSONArray che contiene tutti i valori prelevati da OpenWeather
	 */
	private JSONArray download = new JSONArray();
	
	/**
	 * JSONArray che contiene solo i valori desiderati 
	 */
	private JSONArray value = new JSONArray();
	
	/*
	 * Nome della città
	 */
	private String cityName;
	
	/**
	 * Umidità della città in percentuale
	 */
	private double humidity;
	
	/**
	 * Temperatura della città in kelvin
	 */
	private double temperature;
	
	/**
	 * Data
	 */
	private String date;
	
	
	/**
	 * Costruttore che prende in ingresso il nome della città e il suo stato/paese
	 * 
	 * @param Name Nome della città
	 * @param Code Sigla dello Stato/Paese della città
	 */
	public DownloadCity (String Name) {
		this.cityName = Name;
		this.obj = new JSONObject();
		this.p = new Parser();
	}
	
	/**
	 * Costruttore senza parametri
	 */
	public DownloadCity() {
	}
	
	/**
	 * Metodo Gettere del nome della città
	 * @return cityName Nome della città
	 */
	public String getCityName () {
		return cityName;
	}
	
	/**
	 * Metodo Getter del valore dell'umidità
	 * @return humidity Valore dell'umidità
	 */
	public double getHumidity () {
		return humidity;
	}
	
	/**
	 * Metodo Setter del valore dell'umidità
	 * @param humidity Valore dell'umidità
	 */
	public void setHumidity(double humidity){
		this.humidity = humidity;
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
	 */
	public int getTemperaturaInCelsius(double temperatura) {
		return (int)(temperatura - 273.15);
	}
	
	/**
	 * Metodo che effettua il collegamento con il sito di OpenWeather
	 * @param cityName Nome della città di cui si vogliono ottenere i valori
	 * @return obj JSONObject contenente tutti i valori della città
	 */
	public JSONObject toOpenWeather (String cityName) {
		
		JSONParser parser = new JSONParser();
		
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
	 * @param cityName Nome della città
	 * @return download JSONArray contenente i JSONObject
	 */
	public JSONArray insertObject(String cityName) {
		this.download.add(toOpenWeather(cityName));
		return this.download;
	}
	
	/**
	 * Metodo che permette di salvare i valori scaricati da OpenWeather in un file
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
	 * @param nome_file Nome del file da cui si vogliono leggere i dati
	 * @return download JSONArray contenente tutti i dati letti
	 */
	public JSONArray getValues(String nome_file) {
			
			String data = "";
			String line = "";
			
			try {
				Scanner file_input = new Scanner (new BufferedReader (new FileReader (nome_file)));
				String str = file_input.nextLine();
				
				this.download = (JSONArray) JSONValue.parseWithException(str);
				
				file_input.close();
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return this.download;
		}
	
	/**
	 * Metodo che permette di selezionare i dati desiderati da un JSONArray
	 * @return values JSONArray contenete i dati desiderati 
	 */
	public ArrayList<String> Parsing () {
		
		JSONArray jsonArray = new JSONArray();
		
		ArrayList <String> list = new ArrayList<String>();
		
		JSONArray arr = this.getValues(Config.getName());
		
		for (int i = 0; i<arr.size(); i++) {
		
			JSONObject ob = (JSONObject) arr.get(i);
				
			JSONObject citta = (JSONObject) ob.get("city");
				
				this.cityName = (String) citta.get("name");

			JSONArray lista = (JSONArray) ob.get("list");
				
			for (int n = 0; n < arr.size(); n++) {
						
				JSONObject ob2 = (JSONObject) lista.get(n);
						
				JSONObject main = (JSONObject) ob2.get("main");
							
				this.humidity = Double.parseDouble(main.get("humidity").toString());
				Double temp = Double.parseDouble(main.get("temp").toString());
				this.temperature = this.getTemperaturaInCelsius(temp);
								
				this.date = (String) ob2.get("dt_txt");
				
				obj.put("city", this.cityName);
				obj.put("humidity", this.humidity);
				obj.put("temperature", this.temperature);
				obj.put("date", this.date);
				
				list.add(obj.toString());
				
				/**
				try {
					
				jsonArray = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSONString(list));
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}	
*/
			
			}
		}
		//this.value.add (jsonArray);

		return list;
		
	
	}
}
	

	/**
	 * Metodo che effettua il Parsing dei dati desiderati dal sito di OpenWeather
	 * 
	 * @return JSONObject contenete i dati parsati
	 * @throws UrlException Eccezione personalizzata
	 * @throws IOException Errore di I/O
	 * @throws MalformedURLException 
	 
	public ArrayList<Object> Parsing (String nome_file) throws UrlException, ParseException, MalformedURLException, IOException {
		
		JSONParser parser = new JSONParser();
		
		ArrayList<Object> list = new ArrayList<Object>();
			
		this.download = p.caricaFile(Config.getName());
		
		for (Object o : this.download) {
			
					if(o instanceof JSONObject) {
			
				       JSONObject obj = (JSONObject) o;
					
				       this.cityName = (String) obj.get("city");
					   this.date = (String) obj.get("date");
					   this.humidity = Double.parseDouble(obj.get("humidity").toString());
					   this.temperature = Double.parseDouble(obj.get("temperature").toString());
					}
				}
			
		list = BuildingCity.Building(this.cityName, this.humidity, this.temperature, this.date);
		//this.download = (JSONArray) JSONValue.parseWithException(ParsingJSON.ParsingToJSONObject(list));
		//return this.download;
		return list;
	}
	*/
