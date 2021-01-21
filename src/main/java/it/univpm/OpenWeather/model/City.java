package it.univpm.OpenWeather.model;

import it.univpm.OpenWeather.service.*;
import it.univpm.OpenWeather.utils.Config;

import org.json.simple.JSONArray;

/**
 * Classe che modella la citta
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
public class City {
	
	/**
	 * JSONArray
	 */
	private JSONArray array = new JSONArray();
	
	/**
	 * Nome della citta
	 */
	private String cityName;
	
	/**
	 * Umidità della citta in percentuale
	 */
	private Double humidity;
	
	/**
	 * Temperatura della citta in kelvin
	 */
	private Double temperature;
	
	/**
	 * Data 
	 */
	private String date;
	
	/**
	 * Costruttore 
	 * 
	 * @param name Nome della citta
	 * @param humidity Umidità della citta
	 * @param temperature Temperatura della citta
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
	 * @param name Nome della citta
	 */
	public City(String name) {
		this.cityName = name;
	}
	
	/**
	 * Metodo Getter del nome della citta
	 * @return cityName Ritorna il nome della citta
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Metodo Setter del nome della citta
	 * @param cityName Nome passato dal metodo chiamante
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Metodo Getter dell'umidita della citta
	 * @return humidity Ritorna l'umidita della citta in percentuale
	 */
	
	public double getHumidity() {
		return humidity;
	}

	/**
	 * Metodo Setter dell'umidita della citta
	 * @param humidity Umidita passata dal metodo chiamante
	 */
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	/**
	 * Metodo Getter della temperatura della citta
	 * @return temperature Ritorna la temperatura della citta in kelvin
	 */
	
	public double getTemperature() {
		return temperature;
	}
    
	/**
	 * Metodo Setter della temperatura della citta
	 * @param temperature Temperatura della citta passata dal metodo chiamante in kelvin
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
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Metodo che ritorna un JSONArray contenente tutti i dati di una determinata citta
	 * 
	 * @param city Nome della citta
	 * @return this.array JSONArray contenente i dati 
	 */
	public JSONArray getAllInformation(String city) {
		DownloadCity d = new DownloadCity();
		City c = new City(city);
		this.array.add(d.getValues(Config.getName()));
		return this.array;
	}
	
}
