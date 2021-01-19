package it.univpm.OpenWeather;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.exception.*;

/**
 * Classe che testa i metodi utilizzati per il calcolo
 * delle statistiche e la rispettiva eccezione personalizzata
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
class StatsTest {

	private StatisticsCalculator s1 = null;
	private StatisticsCalculator s2 = null;
	private ArrayList<Double> a1 = null;
	private ArrayList<Double> a2 = null;

	/**
	 * Inizializzazione dei componenti necessari per effettuare i test
	 * @throws Exception 
	 */
	@BeforeEach
	void setUp() throws Exception {
		s1 = new StatisticsCalculator();
		s2 = new StatisticsCalculator();
		a1 = new ArrayList<Double>();
		a2 = new ArrayList<Double>();
		a1.add(6.0);
		a1.add(4.0);
		a1.add(2.0);
		a2.add(-Double.MAX_VALUE);
	}
	
	/**
	 * Annullamento dei valori inizializzati nel metodo setUp
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	/**
	 * Test che verifica i calcoli dei metodi delle statistiche 
	 * implementati dalla classe @it.univpm.OpenWeather.utils.StatisticsCalculator
	 * @throws StatsException 
	 */
	@Test
	@DisplayName ("Test per verificare i calcoli del calcolatore di statistiche")
	void test1() throws StatsException {
		assertEquals(6.0, s1.getMax(a1));
		assertEquals(2.0, s1.getMin(a1));
		assertEquals(4.0, s1.getAverage(a1));
		assertEquals(18.67, s1.getVariance(a1) );
	}
	
	/**
	 * Test che verifica l'eccezione personalizzata della classe
	 * @it.univpm.OpenWeather.exception.StatsException
	 */
	@Test
	@DisplayName("Test eccezione StatsException")
	void testException() {
		StatsException exception = assertThrows(StatsException.class,() -> { s2.getMax(a2);});
		assertEquals(exception.getMessage(),"Non sono stati prelevati dati!");
	}
	

}
