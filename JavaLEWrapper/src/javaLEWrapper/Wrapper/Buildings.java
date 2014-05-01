package javaLEWrapper.Wrapper;

//import LeWrapperSuperClass;

public class Buildings extends LESuperClass {
	//String for URL will be the same as the building name
	void Build(String bodyID, String x, String y){
		
	}
	
	String Upgrade(String sessionID, String buildingID){
		String i = null;
		StartOfObject(1, "upgrade");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	String Demolish(String sessionID, String buildingID){
		String i = null;
		StartOfObject(1, "demolish");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	String Downgrade(String sessionID, String buildingID){
		String i = null;
		StartOfObject(1, "downgrade");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	String Repair(String sessionID, String buildingID){
		String i = null;
		StartOfObject(1, "repair");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	 /* Building Methods

view ( session_id, building_id )
session_id
building_id
get_stats_for_level ( session_id, building_id, level )
session_id
building_id
level

	 * 
	 */

}
