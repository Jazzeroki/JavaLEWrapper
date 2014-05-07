package javaLEWrapper.Wrapper;

public class Spaceport extends Buildings {

	Spaceport(String buildingName) {
		super("spaceport");
		// TODO Auto-generated constructor stub
	}
	String ViewAllShips(String sessionID, String buildingID){
		StartOfObject(1, "view_all_ships");
		String i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}

}
/*

view_all_ships ( session_id, building_id, [ paging, filter, sort ] )

view_foreign_ships ( session_id, building_id, page_number )

get_fleet_for ( session_id, from_body_id, target )

get_ships_for ( session_id, from_body_id, target )

send_ship ( session_id, ship_id, target )

send_ship_types ( session_id, from_body_id, target, types, arrival )

send_fleet ( session_id, ship_ids, target, [ set_speed ] )

recall_ship ( session_id, building_id, ship_id )

recall_all ( session_id, building_id )

name_ship ( session_id, building_id, ship_id, name )

scuttle_ship ( session_id, building_id, ship_id )

mass_scuttle_ship ( session_id, building_id, ship_ids )

view_ships_travelling ( session_id, building_id, [ page_number ])

view_ships_orbiting ( session_id, building_id, [ page_number ])

prepare_send_spies ( session_id, on_body_id, to_body_id )

send_spies ( session_id, on_body_id, to_body_id, ship_id, spy_ids )

prepare_fetch_spies ( session_id, on_body_id, to_body_id )

fetch_spies ( session_id, on_body_id, to_body_id, ship_id, spy_ids )

view_battle_logs ( session_id, building_id, [ page_number ] )
session_id
building_id
page_number
*/