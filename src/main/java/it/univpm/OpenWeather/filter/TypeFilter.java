package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.FilterUtils;
import it.univpm.OpenWeather.exception.FilterException;

import org.json.simple.JSONArray;


/**
 * Classe che implementa l'interfaccia Filter
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class TypeFilter implements Filter <Object> {
	
	/**
	 * Array da filtrare
	 */
	private JSONArray arrayHumidity;
	
	/**
	 * Variabile che richiama il metodo della classe FilterUtils
	 */
	private FilterUtils utils;
	
	/**
	 * Costruttore 
	 * @param array Array da filtrare
	 */
	public TypeFilter(JSONArray array) {
		this.arrayHumidity = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array 
	 * @return arrayCity Ritorna l'array
	 */
	public JSONArray getArray() {
		return arrayHumidity;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e richiama il 
	 * metodo della classe FilterUtils
	 * @param type Parametro di cui si vogliono ottenere i dati
	 * @return Ritorna l'array filtrato 
	 * @throws FilterException Eccezione personalizzata
	 */
	@Override
	public JSONArray filtersCity (Object type) throws FilterException{
		
		if (type == null) throw new FilterException ("Parametro nullo!");
		
		return (JSONArray) utils.getTypeFiltered (type.toString());
		
	}

}
