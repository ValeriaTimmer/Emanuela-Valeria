package it.univpm.OpenWeather.exception;

public class UrlException extends OpenWeatherException {
	
	/**
	 * Costruttore
	 */
	
	public UrlException() {
		super();
		System.out.println ("Connesione al sito effettuata in modo errato");
	}

}
