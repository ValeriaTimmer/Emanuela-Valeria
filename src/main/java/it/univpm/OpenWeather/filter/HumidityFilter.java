package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.FilterUtils;
import it.univpm.OpenWeather.service.DownloadCity;

import org.json.simple.JSONArray;


/**
 * Classe che implementa l'interfaccia Filter
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class HumidityFilter implements Filter <Object, Object> {
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
	public HumidityFilter(JSONArray array) {
		this.arrayHumidity = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array 
	 * @return arrayCity Ritorna l'array
	 */
	public JSONArray getHumidity() {
		return arrayHumidity;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e richiama il 
	 * metodo della classe FilterUtils
	 * @return Ritorna l'array filtrato 
	 */
	@Override
	public JSONArray filtersCity (JSONArray arrayHumidity, Object humidity1, Object humidity2) {
		return (JSONArray) utils.getHumidityFiltered (this.getHumidity(), humidity1, humidity2);
	}

}
