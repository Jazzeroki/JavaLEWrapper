package javaLEWrapper.Wrapper;

import java.io.IOException;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
//import java.util.*;
import java.io.StringWriter;
//import java.util.regex.*;

public class Body extends LESuperClass {
	String GetBuildings(int requestID, String SessionID, String BodyID){
		StartOfObject(requestID, "get_buildings");
		String i = "0";
		try{
			writer.value(SessionID);
			writer.value(BodyID);
			writer.endArray();
			writer.endObject();
			writer.close();
			i = gson.toJson(writer);
			i = CleanJsonObject(i);
		}catch(IOException e){
			System.out.println("ioexception");
		}catch(NullPointerException e){
			System.out.println("null pointer exception");
		}finally{
		}
		return i;
	}
	void RepairList(int requestID, String SessionID, String BodyID, String ...buildings){}
	void RearrangeBuildings(int requestID, String SessionID, String BodyID, ArrangementForRearrangBuildings ...buildings){}
	void GetBuildable(int requestID, String SessionID, String BodyID, int x, int y){}
	void Rename(int requestID, String SessionID, String BodyID){}
	void Abandon(int requestID, String SessionID, String BodyID){}
	class ArrangementForRearrangBuildings{
		String id, x, y;
	}

}

/*
 * Body Methods
get_status ( session_id, body_id )
session_id
body_id
get_buildings ( session_id, body_id )
session_id
body_id
repair_list ( session_id, body_id, building_ids)
session_id
body_id
building_ids
rearrange_buildings ( session_id, body_id, arrangement)
session_id
body_id
arrangement
get_buildable ( session_id, body_id, x, y, tag )
session_id
body_id
x
y
tag
rename ( session_id, body_id, name )
session_id
body_id
name
abandon ( session_id, body_id )
session_id
body_id
 */

