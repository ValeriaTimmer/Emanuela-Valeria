package it.univpm.OpenWeather.exception;

import java.io.IOException;

public class OpenWeatherException extends IOException {
	
	/**
	 * Numero che viene utilizzato durante la deserializzazione per verificare che il mittente 
	 * e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto 
	 * compatibili rispetto alla serializzazione. Se il destinatario ha caricato una classe 
	 * per l'oggetto che ha una diversa serialVersionUIDda quella della corrispondente classe 
	 * del mittente, la deserializzazione si tradurrà in un InvalidClassException 
	 */
	protected static final long serialVersionUID = 1L;
	
	public OpenWeatherException () {
		super();
		System.out.println ("Errore!");
	}
	

}
