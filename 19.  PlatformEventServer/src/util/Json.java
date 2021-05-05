package util;


import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import models.entity.Concert;
import queue_Stack_SimpleList.MyQueue;

public class Json {
	private static final Gson gson = new Gson();
	
	public static Concert stringtoJson(String stringJson) {
		Concert concert = gson.fromJson(stringJson, Concert.class);
		return concert;
	}
	
	public static String convertConcertToStringJson(Concert concert) {
		return gson.toJson(concert);
	}
	
	public static String convertArrayListToStringJson(MyQueue<Concert> concertList) {
		Iterator<Concert> iterator = concertList.iterator();
		String concertsJson = "[";
		while(iterator.hasNext()){
			concertsJson += gson.toJson(iterator.next())+",";
		}
		String newS= concertsJson.substring(0, concertsJson.length()-1);
		newS+="]";
		return newS;
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
	
	public static String convertStringToStrigJson(String convert) {
		JsonPrimitive primitive = new JsonPrimitive(convert);
		return primitive.toString();
	}

	public static ArrayList<String> convertStringtoArray(String convert){
		ArrayList<String> aux = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(convert).getAsJsonArray();
		for (JsonElement jsonElement : array) {
			String primitive = jsonElement.getAsJsonPrimitive().getAsString();
			aux.add(primitive);
		}
		return aux;
	}
}