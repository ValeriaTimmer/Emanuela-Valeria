package it.univpm.OpenWeather.service;

import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;


/**
 * Classe che si occupa di leggere i dati dall'API e di salvarli in un file 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
@Component
public class Parser {
	
	/**
	 * JSONArray
	 */
	private JSONArray download = new JSONArray();
	
	/**
	 * JSONArray
	 */
	private JSONArray ja;

	/**
	 * JSONObject
	 */
	private JSONObject obj = new JSONObject();

	/*
	 * Nome della città
	 */
	private String cityName;
	
	/*
	 * Nome del paese/stato della città
	 */
	private String stateCode;
	
	/**
	 * Umidità della città in percentuale
	 */
	private double humidity;
	
	/**
	 * Temperatura della città in Celsius
	 */
	private double temperature;
	
	/**
	 * Data
	 */
	private String date;
	
	/**
	 * Metodo Getter del nome della città
	 * @return cityName Nome della città
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * Metodo Getter del valore dell'umidità
	 * @return humidity Valore dell'umidità
	 */
	public double getHumidity () {
		return this.humidity;
	}
	
	/**
	 * Metodo Getter del valore della temperatura
	 * @return temperature Valore della Temperatura
	 */
	public Double getTemperature () {
		return this.temperature;
	}
	
	/**
	 * Metodo Gettere della data
	 * @return date Data (stringa)
	 */
	public String getDate() {
		return this.date;
	}
	
	/**
	 * mMtodo Setter del nome della città
	 * @param name nome della città
	 */
	public void setCityName(String name) {
		this.cityName = name;
	}
	
	/**
	 * Variabile della classe City
	 */
	private City c;
	
	/**
	 * Costruttore
	 */
	public Parser () {
		this.ja = new JSONArray();
		this.c = new City();
	}
	
	/**
	 * Metodo per convertire la temperatura da Kelvin in Celsius
	 */
	public int getTemperaturaInCelsius(double temperatura) {
		return (int)(temperatura - 273.15);
	}
	
	
    /**
     * Metodo che effettua il collegamento con il sito dell'api di OpenWeather
     * @param cityName città di cui si vogliono ottenere le informazioni
     * @return JSONObject contente i valori desiderati 
     */
	public JSONObject chiamataAPI (String cityName) {
		
		JSONParser parser = new JSONParser();
		
		ArrayList<City> list = new ArrayList<City>();
			
		
		try {
			URLConnection openConnection = new URL ("https://api.openweathermap.org/data/2.5/forecast?q=" +cityName+ "&appid=" + Config.getApiKey()).openConnection();
			openConnection.addRequestProperty ( " User-Agent " , " Mozilla / 5.0 (Windows NT 6.1; WOW64; rv: 25.0) Gecko / 20100101 Firefox / 25.0 " );
			BufferedReader in = new BufferedReader (new InputStreamReader (openConnection.getInputStream()));
			String inputLine = "";
			String data = "";
			
			while ((inputLine = in.readLine()) != null) {
				  data+=inputLine;
			}
							
						JSONObject o = (JSONObject) parser.parse(data);
						JSONObject citta = (JSONObject) o.get("city");
						this.cityName = (String) citta.get("name");
						
						JSONArray lista = (JSONArray) o.get("list");
						
						for (int i = 0; i < lista.size(); i++) {
					
						//for (Object ob : lista) {
							
							//if (ob instanceof JSONObject) {
								
								JSONObject ob2 = (JSONObject) lista.get(i);
										
								JSONObject main = (JSONObject) ob2.get("main");
								this.humidity = Double.parseDouble(main.get("humidity").toString());
								Double temp = Double.parseDouble(main.get("temp").toString());
								this.temperature = this.getTemperaturaInCelsius(temp);
										
								this.date = (String) ob2.get("dt_txt");
											
								obj.put("city", this.cityName);
								obj.put("humidity", this.humidity);
								obj.put("temperature", this.temperature);
								obj.put("date", this.date);
								
								
							}


		} catch (MalformedURLException e ) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return this.obj;
		
	}
	
	/**
	 * Metodo che permette di inserire un JSONObject in un JSONArray
	 * @param jo JSONObject da aggiungere
	 * @return JSONArray contenete il JSONObject
	 */
	public JSONArray insertObject (String city) {
		this.download.add(chiamataAPI(city).toJSONString());
		return this.download;
	}
	
	
    /**
     * Metodo per salvare i dati presi dalla chiamata al sito in un file
     * @param nome_file nome del file su cui si vanno a salvare i dati
     */
	public void salvaFile (String nome_file, String cityName) {
		
		List <String> val = new ArrayList<>();
		Gson gson = new Gson();
		
		try {
			
			FileWriter file_output = new FileWriter (nome_file, true);
			val.add(insertObject(cityName).toJSONString());
			String jsonString = gson.toJson(val);
			file_output.write (jsonString);
			file_output.close();
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Metodo per leggere i dati contenuti nel file
     * @param nome_file nome del file contenente i dati da leggere
     * @return jsonArray JSONArray contenente i dati letti
     */
	public JSONArray caricaFile (String nome_file) {
		/**
		JSONArray jsonArray = new JSONArray();
		
		JSONObject jo = new JSONObject();
		
		JSONParser parser = new JSONParser();
		
		
		try {
	
			Scanner file_input = new Scanner (new BufferedReader (new FileReader(nome_file)));
			String str = file_input.nextLine();
				
				jo = (JSONObject) parser.parse(str);
			
			file_input.close();
		}catch (IOException | ParseException e) {
				e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		jsonArray.add(jo);
		return jsonArray;
	}*/
	
		
		JSONArray jsonArray = new JSONArray();
		
		ArrayList <String> values = new ArrayList<String>();
		
		String data = "";
		
		try {
			
			BufferedReader file_input = new BufferedReader (new FileReader (nome_file));
			String line = file_input.readLine();
			StringBuilder builder = new StringBuilder();
			while (line != null) {
				builder.append(line);
				line = file_input.readLine();
			}
		
			data = builder.toString();
			file_input.close();
		
		} catch (IOException  e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		values.add(data);
		
		
		try {
		
			jsonArray = (JSONArray) JSONValue.parseWithException (ParsingJSON.ParsingToJSONString(values));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return jsonArray;

	}
	


}
