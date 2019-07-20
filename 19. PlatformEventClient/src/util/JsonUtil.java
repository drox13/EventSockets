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
			return concerts;
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
		for (JsonElement job : vectorTickets) {
			JsonObject obj = job.getAsJsonObject();
			boolean statusTicket = obj.get(TICKETS).getAsBoolean();
			addVectorVTickestConcert(vectorTickestConcert, statusTicket);
		}
		return vectorTickestConcert;
	}


	private static void addVectorVTickestConcert(boolean[] vectorTickestConcert, boolean data) {
		for (int i = 0; i < vectorTickestConcert.length; i++) {
			vectorTickestConcert[i] = data;
		}
	}
}