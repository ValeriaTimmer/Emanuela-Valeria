package it.univpm.OpenWeather;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import it.univpm.OpenWeather.utils.*;
import it.univpm.OpenWeather.filter.*;
import it.univpm.OpenWeather.exception.*;

import org.junit.jupiter.api.Test;

class FilterTest {
    
	private CityFilter c;
	
	private ForecastFilter f;
	
	private JSONArray array;
	
	private Object city;
	
	private Object date;
	
	@BeforeEach
	void setUp() {
		array = new JSONArray();
		c = new CityFilter(array);
		f = new ForecastFilter();
		city = null;
		date = null;
	}
	
	
	@AfterEach
	void tearDown() {}
	
	
	@Test
	void testCityFilter() {
		FilterException exception = assertThrows(FilterException.class,() -> { c.filtersCity(city);});
		assertEquals(exception.getMessage(),"Nome della città nullo!");
	}
	
	@Test
	void testForecastFilter() {
		FilterException exception = assertThrows(FilterException.class,() -> { f.filtersCity(date,city,array);});
		assertEquals(exception.getMessage(),"parametri di filtraggio nulli!");
	}

}
