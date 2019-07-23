package util;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import models.entity.Concert;

public class Json {
	private static final Gson gson = new Gson();
	private static final String TICKET = "ticket";
	
	public static Concert stringtoJson(String stringJson) {
		Concert concert = gson.fromJson(stringJson, Concert.class);
		return concert;
	}
	
	public static String convertConcertToStringJson(Concert concert) {
		return gson.toJson(concert);
	}
	
	public static String convertArrayListToStringJson(ArrayList<Concert> concerts) {
		JsonArray array = new JsonArray();
		for (int i = 0; i < concerts.size(); i++) {
			JsonObject obj = new JsonObject();
			obj.addProperty("concert", gson.toJson(concerts.get(i)));
			array.add(obj);
		}
		
		String concertsJson = "[";
		for (Concert concert : concerts) {
			concertsJson+= gson.toJson(concert)+",";
		}
		String newS= concertsJson.substring(0, concertsJson.length()-1);
		newS+="]";
		return newS	;
	}
	
	public static String convertStringJsonToString(String stringJSON) {
		String string = gson.fromJson(stringJSON, String.class);
		return string;
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
	
	public static boolean[] convertStringJsontoVector(String stringJson) {
		JsonParser parser = new JsonParser();
		JsonObject objBig = parser.parse(stringJson).getAsJsonObject();
		JsonArray vectorTickets = objBig.get("tickets").getAsJsonArray();
		boolean[] vectorTickestConcert = new boolean[vectorTickets.size()];
		for(int i = 0; i < vectorTickets.size(); i++) {
			JsonElement job = vectorTickets.get(i);
			JsonObject obj = job.getAsJsonObject();
			boolean statusTicket = obj.get(TICKET).getAsBoolean();
			System.out.println("status Tickets: " + statusTicket);
			vectorTickestConcert[i] = statusTicket;
		}
		return vectorTickestConcert;
	}
	
	public static String convertStringToStrigJson(String convert) {
		JsonPrimitive primitive = new JsonPrimitive(convert);
		return primitive.toString();
	}
}