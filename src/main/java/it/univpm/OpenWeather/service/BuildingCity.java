package it.univpm.OpenWeather.service;

import java.util.ArrayList;

import it.univpm.OpenWeather.model.City;

/**
 * Classe per la creazione degli arraylist di tipo City 
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 */
public class BuildingCity {

	/**
	 * ArrayList che contiene tutti i dati delle città modellati
	 */
	private static ArrayList <Object> list = new ArrayList<Object>();
	
	/**
	 * Metodo che popola l'arrayLista di tipo City con i dati che ci servono 
	 * richiama il metodo getInformation della classe City
	 * 
	 * @param city Nome della città
	 * @param hum Parametro di Umidità
	 * @param temperature Parametro di Temperatura
	 * @param date Data
	 * @return lista Ritorna la lista degi dati modellati
	 */
	public static ArrayList <Object> Building (String city, double hum, double temperature, String date){
		//City c = new City (city, hum, temperature, date);
		list.add(0, city);
		list.add(1, hum);
		list.add(2, temperature);
		list.add(3, date);
		return list;
	}
	
}
