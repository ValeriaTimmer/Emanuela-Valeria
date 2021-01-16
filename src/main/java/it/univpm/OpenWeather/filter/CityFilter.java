package it.univpm.OpenWeather.filter;

import it.univpm.OpenWeather.utils.*;

import org.json.simple.JSONArray;
import java.util.ArrayList;

/**
 * Classe che implementa l'interfaccia Filter
 * Filtra la città e lo stato
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class CityFilter implements Filter <Object> {
	
	/**
	 * Array contenente le città
	 */
	private ArrayList<String> arrayCity;
	
	/**
	 * Variabile usata per richiamare il metodo della classe FilterUtils
	 */
	private FilterUtils utils;
	
	/**
	 * Costruttore 
	 */
	public CityFilter(ArrayList<String> array) {
		this.arrayCity = array;
		this.utils = new FilterUtils();
	}
	
	/**
	 * Metodo Getter dell'array di città
	 * @return arrayCity Ritorna l'array di città
	 */
	public ArrayList<String> getCity() {
		return arrayCity;
	}
	
	/**
	 * Metodo che effettua l'override del metodo dell'interfaccia e 
	 * richiama il metodo della classe FiltereUtils
	 */
	@Override
	public JSONArray filtersCity (ArrayList<String> array, Object city1) {
		return (JSONArray) utils.getCityFiltered (this.getCity(), city1);
	}
	

}
