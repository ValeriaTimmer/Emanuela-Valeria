package it.univpm.OpenWeather.service;

import java.util.TimerTask;
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
			// Creo due città 
			City c1 = new City();
			c1.getInformation("Ancona", "IT");
			// Creo due città
			City c2 = new City();
			c2.getInformation("Londra", "GB");
			
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
