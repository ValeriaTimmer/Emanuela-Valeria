package it.univpm.OpenWeather.service;

import java.util.ArrayList;

import it.univpm.OpenWeather.model.*;

/**
 * Classe per la creazione degli arraylist di tipo City 
 * 
 * @author ValeriaTimmer
 * @author Emanuela Saleggia
 */
public class BuildingCity {
	
	/**
	 * ArrayList che contiene tutti i dati delle città modellati
	 */
	private static ArrayList <City> lista = new ArrayList<City>();
	
	/**
	 * Metodo che popola l'arrayLista di tipo City con i dati che ci servono 
	 * richiama il metodo getInformation della classe DownloadCity
	 *  
	 * @return lista Ritorna la lista degi dati modellati
	 */
	public static ArrayList <City> Building (City c){
		lista.add(c);
		return lista;
	}
	
}
