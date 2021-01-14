package it.univpm.OpenWeather.model;

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
	/**
	 * Umidità della città in percentuale
	 */
	private double humidity;
	/**
	 * Temperatura della città in kelvin
	 */
	private double temperature;
	/**
	 * Descrizione del meteo della città
	 */
	private String weather;
	/**
	 * API privata
	 */
	private String apiKey;
	/**
	 * Costruttore 
	 * 
	 * @param name Nome della città
	 * @param humidity Umidità della città
	 * @param temp Temperatura della città
	 * @param weather Descrizione del meteo della città
	 */
	public City(String name, double humidity, double temperature, String weather) {
		this.cityName = name;
		this.humidity = humidity;
		this.temperature = temperature;
		this.weather = weather;
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
	
}
