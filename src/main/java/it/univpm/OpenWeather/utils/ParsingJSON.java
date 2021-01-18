package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.model.*;


import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// Classe da eliminare!

/**
 * Classe che permette di ottenere un JSONArray da un ArrayList
 * 
 * @author Emanuela Saleggia
 * @author Valeria Timmer
 *
 */
public class ParsingJSON {
	
	/**
	 * Metodo che permette di ottenere un JSONArray da un ArrayList<City>
	 * @param array ArrayList<City>
	 * @return JSONArray 
	 */
	public static String ParsingToJSONCity (ArrayList<City> array) {
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(array);
		return outFinal;
	}
	
	/**
	 * Metodo che permette di ottenere un JSONArray da un ArrayList<String>
	 * @param array ArrayList<String>
	 * @return JSONArray
	 */
	public static String ParsingToJSONString (ArrayList<String> array) {
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(array);
		return outFinal;
	}
}
