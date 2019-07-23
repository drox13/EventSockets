package util;

import com.google.gson.Gson;

import models.entity.Concert;

public class Json {
	private static Gson gson = new Gson();

	public static String convertObjectJson(Concert concert){
		return  gson.toJson(concert);
	}
}