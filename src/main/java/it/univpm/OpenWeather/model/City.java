package it.univpm.OpenWeather.model;

import it.univpm.OpenWeather.service.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Classe che modella la città
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */


public class City {
	
	private JSONArray array = new JSONArray();
	
	private JSONObject jo = new JSONObject();
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
	 * Costruttore 
	 * 
	 * @param name Nome della città
	 * @param humidity Umidità della città
	 * @param temp Temperatura della città
	 * @param date Data
	 */
	public City(String name, double humidity, double temperature, String date) {
		this.cityName = name;
		this.humidity = humidity;
		this.temperature = temperature;
		this.date = date;
	}
	
	/**
	 * Costruttore
	 * 
	 * @param name Nome della città
	 * @param country Paese/Stato della città
	 */
	public City(String name) {
		this.cityName = name;
	}
	
	/**
	 * Costruttore di default
	 */
	public City () {}
	
	/**
	 * Metodo Getter del nome della città
	 * @return cityName Ritorna il nome della città
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Metodo Setter del nome della città
	 * @param cityName Nome passato dal metodo chiamante
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	
	/**
	 * Metodo Getter dell'umidità della città
	 * @return humidity Ritorna l'umidità della città in percentuale
	 */
	
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Metodo Setter dell'umidità della città
	 * @param humidity Umidità passata dal metodo chiamante
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * Metodo Getter della temperatura della città
	 * @return temperature Ritorna la temperatura della città in kelvin
	 */
	
	public double getTemperature() {
		return temperature;
	}
    
	/**
	 * Metodo Setter della temperatura della città
	 * @param temperature Temperatura della città passata dal metodo chiamante in kelvin
	 */
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	
	public JSONArray getAllInformation(String city) {
		Parser p = new Parser();
		City c = new City(city);
		this.cityName = city;
		this.humidity = p.getHumidity();
		this.temperature = p.getTemperature();
		this.date = p.getDate();
		this.jo.put("city", this.cityName);
		this.jo.put("humidity", this.humidity);
		this.jo.put("temperature", this.temperature);
		this.jo.put("date", this.date);
		this.array.add(jo);
		return this.array;
	}
	
}
