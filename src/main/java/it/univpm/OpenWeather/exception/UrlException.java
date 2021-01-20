package it.univpm.OpenWeather.exception;

import java.net.MalformedURLException;

/**
 * Classe di eccezione personalizzata per l'url
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
public class UrlException extends MalformedURLException {
	
	/**
	 * Numero che viene utilizzato durante la deserializzazione per verificare che il mittente 
	 * e il destinatario di un oggetto serializzato abbiano caricato classi per quell'oggetto 
	 * compatibili rispetto alla serializzazione. Se il destinatario ha caricato una classe 
	 * per l'oggetto che ha una diversa serialVersionUIDda quella della corrispondente classe 
	 * del mittente, la deserializzazione si tradurra in un InvalidClassException 
	 */
	protected static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore
	 */
	
	public UrlException() {
		super();
		System.out.println ("Connesione al sito effettuata in modo errato");
	}

}
