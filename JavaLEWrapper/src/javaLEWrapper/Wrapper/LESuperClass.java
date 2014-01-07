package javaLEWrapper.Wrapper;

import java.io.IOException;
import java.io.StringWriter;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class LESuperClass {
	//A few objects that are needed by all LE Wrapper classes for creating json
	protected Gson gson = new Gson();
	protected StringWriter w = new StringWriter();
	protected JsonWriter writer = new JsonWriter(w);
	
	//A method to be used by all other LE Wrapper classes to create the first portion of requests
	protected void StartOfObject(int requestID, String Method){
		//JsonWriter writer = new JsonWriter();
		try{
			//writer = new JsonWriter(null);
			writer.beginObject();
			writer.name("jsonrpc").value(2);
			writer.name("id").value(requestID);
			writer.name("method").value(Method);
			writer.name("params").beginArray();
			//String u = gson.toJson(writer);
			//System.out.println(u);
		}catch(IOException e){
			e.printStackTrace();
		}
			
	}
	// This method is used for building the common methods that require only a session ID, and a building ID in params[]
	protected String SessionAndBuildingIDRequests(String sessionID, String buildingID){
		String b = "nothing";
		try {
			writer.value(sessionID);
			writer.value(buildingID);
			writer.endArray();
			writer.endObject();
			writer.close();
			b = gson.toJson(writer);
			b = CleanJsonObject(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	//This method is for cleaning out the json string before sending to the server.  Gson leaves behind a lot of garbage after serializing
	protected String CleanJsonObject(String i){
		int start = i.indexOf("{\\");
		int end = i.indexOf("]}");
		i = i.substring(start, end+2);
		return i = i.replace("\\", "");		
	}

}
