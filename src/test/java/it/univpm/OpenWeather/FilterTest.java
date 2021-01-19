package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.json.simple.JSONArray;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.exception.*;

import org.junit.jupiter.api.Test;

/**
 * Classe che testa le eccezioni personalizzate
 * 
 * @author Valeria Timmer
 * @author Emanuela Saleggia
 *
 */
class FilterTest {
    
	private CityFilter c;
	private ForecastFilter f;
	private TypeFilter t;
	private DateUtils dateUtils;
	private JSONArray array;
	private Object city;
	private Object date;
	private Object type;
	private String from;
	private String to;
	
	/**
	 * Inizializzazione dei componenti necessari per effettuare i test
	 * @throws Exception 
	 */
	@BeforeEach
	void setUp() throws Exception {
		array = new JSONArray();
		c = new CityFilter(array);
		f = new ForecastFilter();
		t = new TypeFilter(array);
		dateUtils = new DateUtils();
		city = null;
		date = null;
		type = null;
		from = "2021-01-25";
		to = "2021-01-21";
	}
	
	/**
	 * Annullamento dei valori inizializzati nel metodo setUp
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		
	}
	
	/**
	 * Test che verifica l'eccezione personalizzata della classe 
	 * @it.univpm.OpenWeather.exception.FilterException
	 */
	@Test
	@DisplayName ("Test eccezione FilterException nella classe CityFilter")
	void testCityFilter() {
		FilterException exception = assertThrows(FilterException.class,() -> { c.filtersCity(city);});
		assertEquals(exception.getMessage(),"Nome della città nullo!");
	}
	
	/**
	 * Test che verifica l'eccezione personalizzata della classe
	 * @it.univpm.OpenWeather.exception.FilterException
	 */
	@Test
	@DisplayName ("Test eccezione FilterException nella classe HumidityFilter")
	void testHumidityFilter() {
		FilterException exception = assertThrows (FilterException.class, () -> {t.filtersCity(type);});
		assertEquals(exception.getMessage(), "Parametro nullo!");
	}
	/**
	 * Test che verifica l'eccezione personalizzata della classe
	 * @it.univpm.OpenWeather.exception.FilterException
	 */
	@Test
	@DisplayName ("Test eccezione FilterException nella classe ForecastFilter")
	void testForecastFilter() {
		FilterException exception = assertThrows(FilterException.class,() -> { f.filtersCity(date,city,array);});
		assertEquals(exception.getMessage(),"Parametri di filtraggio nulli!");
	}
	
	/**
	 * Test che verifica l'eccezione personalizzata della classe
	 * @it.univpm.OpenWeather.exception.DataFormatException
	 */
	@Test
	@DisplayName ("Test eccezione DataFormatException nella classe DateUtils")
	void testDate() {
		DataFormatException exception = assertThrows (DataFormatException.class, () -> {dateUtils.getPeriod(from, to);});
		assertEquals(exception.getMessage(), "La data finale precede quella iniziale!");
	}

}
