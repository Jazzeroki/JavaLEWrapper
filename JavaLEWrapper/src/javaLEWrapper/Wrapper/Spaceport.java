package javaLEWrapper.Wrapper;

public class Spaceport extends Buildings {

	Spaceport(String buildingName) {
		super("spaceport");
		// TODO Auto-generated constructor stub
	}
	String ViewAllShips(String sessionID, String buildingID){
		String i = BasicRequest("view_all_ships", sessionID, buildingID);
		return i;
	}
	String RecallAll(String sessionID, String buildingID){
		String i = BasicRequest("recall_all", sessionID, buildingID);
		return i;
	}
	String ViewForeignShips(String sessionID, String buildingID, String pageNumber){
		String i = ThreePartRequest("view_foreign_ships", sessionID, buildingID, pageNumber);
		return i;
	}
	String GetFleetFor(String sessionID, String buildingID, String target){
		String i = ThreePartRequest("get_fleet_for", sessionID, buildingID, target);
		return i;
	}
	String GetShipsFor(String sessionID, String buildingID, String target){
		String i = ThreePartRequest("get_ships_for", sessionID, buildingID, target);
		return i;
	}
	String SendShip(String sessionID, String buildingID, String target){
		String i = ThreePartRequest("send_ship", sessionID, buildingID, target);
		return i;
	}
	String RecallShip(String sessionID, String buildingID, String shipID){
		String i = ThreePartRequest("recall_ship", sessionID, buildingID, shipID);
		return i;
	}
	String PrepareSendSpies(String sessionID, String onBodyID, String toBodyID){
		String i = ThreePartRequest("prepare_send_spies", sessionID, onBodyID, toBodyID);
		return i;
	}
	String PrepareFetchSpies(String sessionID, String onBodyID, String toBodyID){
		String i = ThreePartRequest("prepare_fetch_spies", sessionID, onBodyID, toBodyID);
		return i;
	}
	String ViewBattleLogs(String sessionID, String buildingID, String pageNumber){
		String i = ThreePartRequest("prepare_fetch_spies", sessionID, buildingID, pageNumber);
		return i;
	}
}
/*

view_all_ships ( session_id, building_id, [ paging, filter, sort ] )
send_ship_types ( session_id, from_body_id, target, types, arrival )
send_fleet ( session_id, ship_ids, target, [ set_speed ] )
name_ship ( session_id, building_id, ship_id, name )
scuttle_ship ( session_id, building_id, ship_id )
mass_scuttle_ship ( session_id, building_id, ship_ids )
view_ships_travelling ( session_id, building_id, [ page_number ])
view_ships_orbiting ( session_id, building_id, [ page_number ])
send_spies ( session_id, on_body_id, to_body_id, ship_id, spy_ids )
fetch_spies ( session_id, on_body_id, to_body_id, ship_id, spy_ids )


*/