package it.univpm.OpenWeather.utils;

import it.univpm.OpenWeather.model.*;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParsingJSON {
	
	public static String ParsingToJSON (ArrayList<City> array) {
		Gson out = new GsonBuilder().setPrettyPrinting().create();
		String outFinal = out.toJson(array);
		return outFinal;
	}
}
