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
	private static ArrayList <City> list = new ArrayList<City>();
	
	/**
	 * Metodo che popola l'arrayLista di tipo City con i dati che ci servono 
	 * richiama il metodo getInformation della classe City
	 * 
	 * @param c Variabile della classe City
	 * @return lista Ritorna la lista degi dati modellati
	 */
	public static ArrayList <City> Building (City c){
		list.add(c);
		return list;
	}
	
}
