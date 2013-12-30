package javaLEWrapper;

import java.io.IOException;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import java.util.*;
import java.io.StringWriter;
import java.util.regex.*;

public class Body {
	private Gson gson = new Gson();
	StringWriter w = new StringWriter();
	JsonWriter writer = new JsonWriter(w);
	
	//String GetStatus(String SessionID, String BodyID)
	String GetBuildings(int requestID, String SessionID, String BodyID){
		StartOfObject(requestID, "get_buildings");
		String i = "0";
		try{
			writer.value(SessionID);
			writer.value(BodyID);
			writer.endArray();
			writer.endObject();
			writer.close();
			//writer.flush();
			//writer.flush();
			//gson.
			i = gson.toJson(writer);
			i = CleanJsonObject(i);
		}catch(IOException e){
			System.out.println("ioexception");
		}catch(NullPointerException e){
			System.out.println("null pointer exception");
		}finally{
			//i = gson.toJson(writer);
		}
		//i = gson.toJson(writer);
		//writer = null;
		return i;
	}
	private String CleanJsonObject(String i){
		int start = i.indexOf("{\\");
		int end = i.indexOf("]}");
		i = i.substring(start, end+2);
		return i = i.replace("\\", "");		
	}
	//String RepairList(String SessionID, String BodyID, String[] BuildingIDs)
	//String RearrangeBuildings(String SessionID, String BodyID, arrangement)
	//String GetBuildable(String SessionID, String BodyID, int x, int y, String tag)
	//String Rename(String SessionID, String BodyID, String NewName)
	//String Abandon(String SessionID, String BodyID)
	private void StartOfObject(int requestID, String Method){
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

}
