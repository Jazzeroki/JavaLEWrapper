package javaLEWrapper.Wrapper;

//import LeWrapperSuperClass;

public class Buildings extends LESuperClass {
	//String for URL will be the same as the building name
	String url;
	Buildings(String buildingName){
		url = buildingName.toLowerCase();
		System.out.println(url);
    	url = url.replace(" ", "");
    	if (url.equals("archaeologyministry"))
    		url = "entertainment";
    	if (url.contains("beach"))
    		url = "beach";
    	if (url.contains("herder"))
    		url = "beeldeban";
    	if (url.contains("bean"))
    		url = "bean";
    	if (url.contains("nest"))
    		url = "beeldebannest";
    	if (url.contains("beach"))
    		url = "beach";
    	if (url.contains("bread"))
    		url = "bread";
    	if (url.contains("burger"))
    		url = "burger";
    	if (url.contains("capitol"))
    		url = "capitol";
    	if (url.contains("cheese"))
    		url = "cheese";
    	if (url.contains("chip"))
    		url = "chip";
    	if (url.contains("cider"))
    		url = "cider";
    	if (url.contains("cornplantation"))
    		url = "corn";
    	if (url.contains("cornmeal"))
    		url = "meal";
    	if (url.contains("dairy"))
    		url = "dairy";
    	if (url.contains("dentonroot"))
    		url = "denton";
    	if (url.contains("development"))
    		url = "development";
    	if (url.contains("espionageministry"))
    		url = "espionage";
    	if (url.contains("fission"))
    		url = "fission";
    	if (url.contains("fusion"))
    		url = "fusion";
    	if (url.contains("geoenergy"))
    		url = "geo";
    	if (url.contains("grove"))
    		url = "grove";
    	if (url.contains("hydrocarbon"))
    		url = "hydrocarbon";
    	if (url.contains("lapisorchard"))
    		url = "lapis";
    	if (url.contains("lostcity"))
    		url = "lostcityoftyleon";
    	if (url.contains("malcudfungus"))
    		url = "malcud";
    	if (url.contains("oversight"))
    		url = "oversight";
    	if (url.contains("pancake"))
    		url = "pancake";
    	if (url.contains("pie"))
    		url = "pie";
    	if (url.contains("pilottraining"))
    		url = "pilottraining";
    	if (url.contains("planetarycommand"))
    		url = "planetarycommand";
    	if (url.contains("potatoe"))
    		url = "potatoe";
    	if (url.contains("propulsion"))
    		url = "propulsion";
    	if (url.contains("shieldagainst"))
    		url = "saw";
    	if (url.contains("security"))
    		url = "security";
    	if (url.contains("shake"))
    		url = "shake";
    	if (url.contains("shipyard"))
    		url = "shipyard";
    	if (url.contains("singularity"))
    		url = "singularity";
    	if (url.contains("syrup"))
    		url = "syrup";
    	if (url.contains("trade"))
    		url = "trade";
    	if (url.contains("transporter"))
    		url = "transporter";
    	if (url.contains("wasteenergy"))
    		url = "wasteenergy";
    	if (url.equals("entertainmentdistrict"))
    		url = "entertainment";
    	if (url.contains("wasterecycling"))
    		url = "wasterecycling";
    	if (url.contains("wastesequestration"))
    		url = "wastesequestration";
    	if (url.equals("intelligenceministry"))
    		url="intelligence";
    	/*
    	WasteTreatment
    	WaterProduction
    	WaterPurification
    	WaterReclamation
    	WaterStorage
    	Wheat
    	*/
	}
	String Build(String sessionID, String bodyID, String x, String y){
		return Request("build",sessionID, bodyID, x, y);
	}
	String View(String sessionID, String buildingID){
		StartOfObject(1, "view");
		String i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	String Upgrade(String sessionID, String buildingID){
		StartOfObject(1, "upgrade");
		String i = SessionAndBuildingIDRequests(sessionID, buildingID);
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
		String i = BasicRequest("repair", sessionID, buildingID);
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
