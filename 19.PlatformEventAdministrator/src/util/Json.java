package util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import models.entity.Concert;

public class Json {
	private static Gson gson = new Gson();
	private static final String TICKETS = "ticket";

	public static String convertObjectJson(Concert concert){
		return  gson.toJson(concert);
	}
	
	public static String convertStringToStringJson(String string) {
		JsonPrimitive primitive = new JsonPrimitive(string);
		return primitive.toString();
	}
	
	public static boolean[] convertStringJsontoVector(String stringJson) {
		JsonParser parser = new JsonParser();
		JsonArray vectorTickets = (JsonArray) parser.parse(stringJson);
		boolean[] vectorTickestConcert = new boolean[vectorTickets.size()];
		for(int i = 0; i < vectorTickets.size(); i++) {
			JsonElement job = vectorTickets.get(i);
			JsonObject obj = job.getAsJsonObject();
			boolean statusTicket = obj.get(TICKETS).getAsBoolean();
			if(statusTicket) {
				vectorTickestConcert[i] = statusTicket;
			}
		}
		return vectorTickestConcert;
	}
	
	public static List<Concert> convertArrayJsonToArratConcert(String arrayJson) {
		ArrayList<Concert> concerts = new ArrayList<>();
		try {
			Type typeListConcert = new TypeToken<List<Concert>>(){}.getType();
			concerts = gson.fromJson(arrayJson, typeListConcert);
		} catch (Exception e) {}
		return concerts;
	}
}