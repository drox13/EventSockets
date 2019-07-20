package util;


import java.util.ArrayList;

import com.google.gson.Gson;

import models.entity.Concert;

public class Json {
	private static final Gson gson = new Gson();
	
	public static Concert stringtoJson(String stringJson) {
		System.out.println("asi llega el formato al server: "+ stringJson);
		Concert concert = gson.fromJson(stringJson, Concert.class);
		System.out.println("asi sale del metodo server: " + concert);
		return concert;
	}
	
	public static String convertConcertToStringJson(Concert concert) {
		return gson.toJson(concert);
	}
	
	public static String convertArrayListToStringJson(ArrayList<Concert> concerts) {
		String concertsJson = "[";
		for (Concert concert : concerts) {
			concertsJson+= gson.toJson(concert)+",";
		}
		String newS= concertsJson.substring(0, concertsJson.length()-1);
		newS+="]";
		return newS	;
	}
	
	public static String convertStringJsonToString(String stringJSON) {
		String s = gson.fromJson(stringJSON, String.class);
		System.out.println("formato que llego en el servidor: " + s);
		return s;
	}
}