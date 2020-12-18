package it.univpm.OpenWeather.service;

import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Date;
import it.univpm.OpenWeather.model.*;

public class Schedule {
	
	/**
	 * Metodo che pianifica l'attivita specificata per l'esecuzione ripetuta a velocità fissa
	 * a partire dall'ora specificata
	 * 
	 * @param task Attività da pianificare
	 * @param firstTime Prima volta in cui l'attività deve essere eseguita
	 * @param period Tempo in millisecondi tra le successive esecuzioni di attività
	 */
	public void scheduleAtFixedRate (TimerTask task, Date firstTime, long period) {
		try {
			
			ArrayList <City> downloadCity = new ArrayList <City>(); 
			
			// Creo alcune città 
			City c1 = new City();
			c1.getInformation("Ancona", "IT");
			downloadCity = BuildingCity.Building(c1);

			
			City c2 = new City();
			c2.getInformation("Londra", "GB");
			downloadCity = BuildingCity.Building(c2);
			
			City c3 = new City();
			c3.getInformation("Berlino", "DE");
			downloadCity = BuildingCity.Building(c3);
			
			
		} catch (IllegalArgumentException e) {
			// Viene lanciata se firstTime.getTime() < 0 o period <= 0
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// Viene lanciata se l'attività era già pianificata o annullata,
			// il timer è stato annullato o il thread del timer è stato terminato
			e.printStackTrace();
		} catch (NullPointerException e) {
			// Viene lanciata se task o fistTime sono null
			e.printStackTrace();
		}
	}
	
}
