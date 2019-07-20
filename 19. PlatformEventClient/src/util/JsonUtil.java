package util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;

import models.entity.Concert;

public class JsonUtil {
	private static final Gson gson = new Gson();

	public static Concert convertStringToConcert(String stringJson) {
		return gson.fromJson(stringJson, Concert.class);
	}

	public static List<Concert> convertArrayJsonToArratConcert(String arrayJson) {
		Type typeListConcert = new TypeToken<List<Concert>>(){}.getType();
		List<Concert> concerts = gson.fromJson(arrayJson, typeListConcert);
		return concerts;
	}
	
	public static String convertStringToStrigJson(String convert) {
		JsonPrimitive primitive = new JsonPrimitive(convert);
		System.out.println("formato enviado por el cliente: " + primitive.toString());
		return primitive.toString();
	}
}