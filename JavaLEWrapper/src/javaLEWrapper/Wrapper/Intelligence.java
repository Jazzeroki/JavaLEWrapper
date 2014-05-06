package javaLEWrapper.Wrapper;

import java.io.IOException;

public class Intelligence extends Buildings {
	Intelligence(String buildingName) {
		super(buildingName); //may need to input intelligence
		// TODO Auto-generated constructor stub
	}
	//String url = "intelligence";
	String View(int requestID, String sessionID, String buildingID){
		String i = null;
		StartOfObject(requestID, "view");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	String ViewAllSpies(int requestID, String sessionID, String buildingID){
		String i = null;
		StartOfObject(requestID, "view_all_spies");
		i = SessionAndBuildingIDRequests(sessionID, buildingID);
		return i;
	}
	/*Intelligence Methods
view ( session_id, building_id )
train_spy ( session_id, building_id, [ quantity ])
session_id
building_id
quantity
view_spies ( session_id, building_id, [ page_number ] )
session_id
building_id
page_number
burn_spy ( session_id, building_id, spy_id )
session_id
building_id
spy_id
name_spy ( session_id, building_id, spy_id, name )
session_id
building_id
spy_id
name
assign_spy ( session_id, building_id, spy_id, assignment )
session_id
building_id
spy_id
assignment
	 * 
	 * 
	 * assignment

A string containing the new assignment name. These are the possible assignments:

Idle
Don't do anything.

Counter Espionage.
Passively defend against all attackers.

Security Sweep
Round up attackers.

Intel Training
Train in Intelligence skill

Mayhem Training
Train in Mayhem skill

Politics Training
Train in Politics skill

Theft Training
Train in Theft skill

Political Propaganda
Give happiness generation a boost. Especially effective on unhappy colonies, but hastens an agent toward retirement.

Gather Resource Intelligence
Find out what's up for trade, what ships are available, what ships are being built, where ships are travelling to, etc.

Gather Empire Intelligence
Find out what is built on this planet, the resources of the planet, what other colonies this Empire has, etc.

Gather Operative Intelligence
Find out what spies are on this planet, where they are from, what they are doing, etc.

Hack Network 19
Attempts to besmirch the good name of the empire controlling this planet, and deprive them of a small amount of happiness.

Sabotage Probes
Destroy probes controlled by this empire.

Rescue Comrades
Break spies out of prison.

Sabotage Resources
Destroy ships being built, docked, en route to mining platforms, etc.

Appropriate Resources
Steal empty ships, ships full of resources, ships full of trade goods, etc.

Assassinate Operatives
Kill spies.

Sabotage Infrastructure
Destroy buildings.

Sabotage BHG
Prevent enemy planet from using Black Hole Generator.

Incite Mutiny
Turn spies. If successful they come work for you.

Abduct Operatives
Kidnap a spy and bring him back home.

Appropriate Technology
Steal plans for buildings that this empire has built, or has in inventory.

Incite Rebellion
Obliterate the happiness of a planet. If done long enough, it can shut down a planet.

Incite Insurrection
Steal a planet.
	 */

}
