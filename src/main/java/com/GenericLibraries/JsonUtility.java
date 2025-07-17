package com.GenericLibraries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {

	public String ReadDataFromJson(String key) throws IOException, ParseException {

		FileReader fr = new FileReader("D:\\EclipseWorkspace\\OrangeHRMProject\\src\\main\\resources\\commonData.json");
		JSONParser json = new JSONParser();// Provides forward, read-only access to JSON data in a streaming way
		Object javaObj = json.parse(fr);// Usually the parse() method receives some string as input, "extracts" the
										// necessary information from it and converts it into an object of the calling
										// class
		HashMap hash = (HashMap) javaObj;
		String value = hash.get(key).toString();
		return value;
	}
}
