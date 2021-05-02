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
import view.WindowClient;

public class JsonUtil {
	private static final Gson gson = new Gson();
	private static final String MESSAGE_ERROR = "No hay conciertos registrados";
	private static final String TICKETS = "ticket";

	public static Concert convertStringToConcert(String stringJson) {
		return gson.fromJson(stringJson, Concert.class);
	}

	public static List<Concert> convertArrayJsonToArratConcert(String arrayJson) {
		ArrayList<Concert> concerts = new ArrayList<>();
		try {
			Type typeListConcert = new TypeToken<List<Concert>>(){}.getType();
			concerts = gson.fromJson(arrayJson, typeListConcert);
		} catch (Exception e) {
			WindowClient.showMessage(MESSAGE_ERROR);
		}
		return concerts;
	}

	public static String convertStringToStrigJson(String convert) {
		JsonPrimitive primitive = new JsonPrimitive(convert);
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

	private static String convertVectorToStringJson (boolean[] tickest) {
		JsonObject objBig  = new JsonObject();
		JsonArray arrayJson = new JsonArray();
		for (int i = 0; i < tickest.length; i++) {
			JsonObject objJson = new JsonObject();
			objJson.addProperty("ticket", tickest[i]);
			arrayJson.add(objJson);
		}
		objBig.add("tickets", arrayJson);
		return objBig.toString();
	}
	
	public static String convertStringJsonToString(String stringJSON) {
		String string = gson.fromJson(stringJSON, String.class);
		return string;
	}

	public static String convertArraytoStringJson(ArrayList<String> ticketsSelect) {
		JsonArray array = new JsonArray();
		for (String string : ticketsSelect) {
			JsonPrimitive data = new JsonPrimitive(string);
			array.add(data);
		}
		System.out.println("asi se envio el array: " + array.toString());
		return array.toString();
	}
}