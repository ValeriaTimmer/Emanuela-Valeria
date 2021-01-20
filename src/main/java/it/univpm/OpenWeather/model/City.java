package it.univpm.OpenWeather.model;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.utils.Config;

import org.json.simple.JSONArray;

/**
 * Classe che modella la città
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
public class City {
	
	/**
	 * JSONArray
	 */
	private JSONArray array = new JSONArray();
	
	/**
	 * Nome della città
	 */
	private String cityName;
	
	/**
	 * Umidità della città in percentuale
	 */
	private Double humidity;
	
	/**
	 * Temperatura della città in kelvin
	 */
	private Double temperature;
	
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
	public City(String name, Double humidity, Double temperature, String date) {
		this.cityName = name;
		this.humidity = humidity;
		this.temperature = temperature;
		this.date = date;
	}

	/**
	 * Costruttore di default
	 */
	public City () {}
	
	/**
	 * Costruttore
	 * 
	 * @param name Nome della città
	 */
	public City(String name) {
		this.cityName = name;
	}
	
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
	
	/**
	 * Metodo Getter della data
	 * @return date Valore della data
	 */
	public String getDate() {
		return this.date;
	}
	
	/**
	 * Metodo Setter della data
	 * @param date Data passata dal metodo chiamante
	 */
	public void setData(String date) {
		this.date = date;
	}

	/**
	 * Metodo che ritorna un JSONArray contenente tutti i dati di una determinata città
	 * 
	 * @param city Nome della città
	 * @return this.array JSONArray contenente i dati 
	 */
	public JSONArray getAllInformation(String city) {
		DownloadCity d = new DownloadCity();
		City c = new City(city);
		this.array.add(d.getValues(Config.getName()));
		return this.array;
	}
	
}
