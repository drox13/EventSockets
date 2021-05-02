package util;


import java.util.ArrayList;
import java.util.Arrays;
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
	private static final String TICKET = "ticket";
	
	public static Concert stringtoJson(String stringJson) {
		Concert concert = gson.fromJson(stringJson, Concert.class);
		return concert;
	}
	
	public static String convertConcertToStringJson(Concert concert) {
		return gson.toJson(concert);
	}
	
	public static String convertArrayListToStringJson(MyQueue<Concert> concertList) {
//		JsonArray array = new JsonArray();
		Iterator<Concert> iterator = concertList.iterator();
		String concertsJson = "[";
		while(iterator.hasNext()){
			concertsJson += gson.toJson(iterator.next())+",";
		}
		String newS= concertsJson.substring(0, concertsJson.length()-1);
		newS+="]";
		
		
		
//		for (int i = 0; i < concertList.size(); i++) {
//			JsonObject obj = new JsonObject();
//			obj.addProperty("concert", gson.toJson(concertList.get(i)));
//			array.add(obj);
//		}
		
//		String concertsJson = "[";
//		for (Concert concert : concertList) {
//			concertsJson+= gson.toJson(concert)+",";
//		}
//		String newS= concertsJson.substring(0, concertsJson.length()-1);
//		newS+="]";
		System.out.println(newS);
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
	
	private static boolean[] convertStringJsontoVector(String stringJson) {
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

	public static ArrayList<String> convertStringtoArray(String convert){
		System.out.println("se resivio");
		ArrayList<String> aux = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(convert).getAsJsonArray();
		for (JsonElement jsonElement : array) {
			String primitive = jsonElement.getAsJsonPrimitive().getAsString();
			aux.add(primitive);
		}
		System.out.println("salio asi:" + aux.toString());
		return aux;
	}
}