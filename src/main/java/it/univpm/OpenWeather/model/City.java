package it.univpm.OpenWeather.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import it.univpm.OpenWeather.utils.*;

/**
 * Classe che modella la città
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */

public class City {
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
	private int humidity;
	/**
	 * Temperatura della città in kelvin
	 */
	private double temperature;
	/**
	 * Descrizione del meteo della città
	 */
	private String weather;
	
	/**
	 * Costruttore della classe City che inizializza una città
	 * 
	 * @param name Nome della città
	 * @param country Paese/Stato della città
	 * @param humidity Umidità della città
	 * @param temp Temperatura della città
	 * @param weather Descrizione del meteo della città
	 */
	public City(String name, String country, int humidity, double temp, String weather) {
		this.cityName = name;
		this.stateCode = country;
		this.humidity = humidity;
		this.temperature = temp;
		this.weather = weather;
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
	 * Metodo Getter del nome del Paese/Stato della città
	 * @return stateCode Ritorna il codice (sigla) del Paese/Stato della città
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * Metodo Setter del nome del Paese/Stato della città
	 * @param stateCode Codice (sigla) del Paese/Stato della città
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	
	/**
	 * Metodo Getter dell'umidità della città
	 * @return humidity Ritorna l'umidità della città in percentuale
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Metodo Setter dell'umidità della città
	 * @param humidity Umidità passata dal metodo chiamante
	 */
	public void setHumidity(int humidity) {
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
	 * Metodo Getter della descrizione meteo della città
	 * @return weather Ritorna un descrizione del meteo della città
	 */
	public String getWeather() {
		return weather;
	}
	/**
	 * Metodo Setter della descrizione meteo della città
	 * @param weather Descrizione meteo della città passata dal metodo chiamante
	 */

	public void setWeather(String weather) {
		this.weather = weather;
	}
	/**
	 * Metodo che prende informazioni dal JSON Parsato dalla classe del package utils
	 * e completa i campi con le varie informazioni
	 * 
	 * @param cityName Nome della città 
	 * @param stateCode nome dello Stato/Paese della città
	 */
	public void getInformation (String cityName, String stateCode) {
		// Bisogna gestire le eccezioni
		JsonParser OpenWeather = new JsonParser(cityName, stateCode);
		OpenWeather.Parser();
		this.cityName = OpenWeather.getCityName();
		this.stateCode = OpenWeather.getStateCode();
		this.humidity = OpenWeather.getHumidity();
		this.temperature = OpenWeather.getTemperature();
		this.weather = OpenWeather.getWeather();
	}
}
