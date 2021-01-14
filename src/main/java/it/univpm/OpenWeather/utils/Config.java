package it.univpm.OpenWeather.utils;

public class Config {
	
	private static String fileName = "allValues.txt";
	
	public static String getName() {
		return fileName;
	}
	
	private static void setName (String name) {
		Config.fileName = name;
	}
	
	private static String apiKey = "bed1a816d94554cecab782b0804bec47" ;
	
	public static String getApiKey () {
		return apiKey;
	}
	
	public static void setApiKey (String api) {
		Config.apiKey = api;
	}
	
	private static Boolean call = true;
	
	public static Boolean getCall() {
		return call;
	}
	
	public static void setCall(Boolean value) {
		Config.call = value;
	}
	
	
}
