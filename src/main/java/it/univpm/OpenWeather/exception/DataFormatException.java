package it.univpm.OpenWeather.exception;

import java.text.ParseException;

/**
 * Classe che gestisce l'eccezione che segnala che si è
 * verificato un errore di formato dati
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 * 
 */
public class DataFormatException extends ParseException {
	
	/**
	 * Numero che viene utilizzato durante la deserializzazione per verificare che il mittente 
	 * e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto 
	 * compatibili rispetto alla serializzazione. Se il destinatario ha caricato una classe 
	 * per l'oggetto che ha una diversa serialVersionUIDda quella della corrispondente classe 
	 * del mittente, la deserializzazione si tradurrà in un InvalidClassException 
	 */
	protected static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore
	 */
	public DataFormatException(String message, int errorOffset ) {
		super(message, errorOffset );
	}
	
}
