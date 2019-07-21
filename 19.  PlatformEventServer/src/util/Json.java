package util;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import models.entity.Concert;

public class Json {
	private static final Gson gson = new Gson();
	
	public static Concert stringtoJson(String stringJson) {
		Concert concert = gson.fromJson(stringJson, Concert.class);
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
		return s;
	}
	
	public static String convertVectorToStringJson (boolean[] tickest) {
		JsonArray arrayJson = new JsonArray();
		for (int i = 0; i < tickest.length; i++) {
			JsonObject objJson = new JsonObject();
			objJson.addProperty("ticket", tickest[i]);
			arrayJson.add(objJson);
		}
		return arrayJson.toString();
	}
}