package javaLEWrapper.Wrapper;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
//import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
//import java.io.Console;
import java.net.URL;
import java.awt.Desktop;
import java.awt.List;

import javaLEWrapper.Wrapper.Response.Messages;
import javaLEWrapper.Wrapper.Response.Result;
import javaLEWrapper.Wrapper.Response.Spies;
import javaLEWrapper.Wrapper.Response.Spies.PossibleAssignments;


public class JavaLEWrapper {
	/*class Names implements Comparable<Names> {
		String id, name;
		@Override
		public int compareTo(Names n){
			return name.compareTo(n.name);
		}
	} */
	static String version = "Alpha 0.01";
	static String sessionID = null;
	static String gameServer = null;
	static Gson gson = new Gson();
	static Server server = new Server();	
	static int newMessages;
	static boolean captchaValid = false;
	static HashMap <String, String> planetList = new HashMap <String, String>() ;
	static HashMap <String, Response.Result> stations = new HashMap<>();
	static HashMap <String, Response.Result> planets = new HashMap<>();
	
	static ArrayList <String> planetNames = new ArrayList<String>(); //For storing a sorted collection of planet names
	static ArrayList <String> stationNames = new ArrayList<String>(); //For storing a sorted collection of station names
    
	public static void main(String[] args) {
		GetSession();
		MainMenu();
    }
    
    //Main Controls
    static void GetSession(){
    	AccountManager a = new AccountManager();
    	//System.out.println("Calling Get account");
	    if(!new File("Account.Jazz").isFile()){ //if an account file doesn't exist one is created
	    	a.CreateAccount();
	    }
    	String i = a.GetAccount();
    	//Need to deserialize string
    	AccountInfo account = new AccountInfo();
    	account = gson.fromJson(i, AccountInfo.class);
    	gameServer = account.Server;
    	//System.out.println("account deserialized");
    	
    	//Getting SessionID
    	Empire e = new Empire();
    	i = e.Login(account.userName, account.Password, 1);
    	i = server.ServerRequest(gameServer, e.url, i);
    	System.out.println(i);
    	Response r = gson.fromJson(i, Response.class);
    	
    	//setting values for empire details
    	sessionID = r.result.session_id;
    	newMessages = r.result.status.empire.has_new_messages;
    	planetList = r.result.status.empire.planets;
    		
    	
    }
    static void PrintMainMenu(){
    	System.out.println("Jazz Java LE Command Console, version: "+version);
    	System.out.println("Enter a number for your selection");
    	System.out.println("1, Planetary Controls");
    	System.out.println("2, Station Controls");
    	System.out.println("3, Messages: "+newMessages);
    	System.out.println("4, Account Management");
    	System.out.println("5, Logout");
    	System.out.println("6, Experiments");
    }
    static void MainMenu(){
    	//Captcha();
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintMainMenu();
    		try{
    			//System.out.println("starting try block");
    			control = input.nextInt();
    			//System.out.println(control);
    			switch(control){
    			case 1:
    				PlanetControlsMenu();
    				break;
    			case 2:
    				SSControlsMenu();
    				break;
    			case 3:
    				MessageBoxMenu();
    				break;
    			case 4:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 5:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 6:
    				ExperimentalMethodsMenu();
    				break;
    			default:
    				System.out.println("Invalid Selection");
    				PrintMainMenu();
    			}	
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");   			
    		}		
    	}while(i==0);
    	input.close();
    }
    static void PrintExperimentalMenu(){
    	System.out.println("1: Captcha test");
    	System.out.println("0: to return to main menu");
    }
    static void ExperimentalMethodsMenu(){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintExperimentalMenu();
    		try{
    			control = input.nextInt();
    			switch(control){
    			case 1:
    				Captcha();
    				break;
    			case 0:
    				PlanetControlsMenu();
    				break;
    			default:
    				System.out.println("Invalid Selection");
    				PrintMainMenu();
    			}	
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");   			
    		}		
    	}while(i==0);
    	input.close();
    }
    //experimental methods
    static void Captcha(){
    	Captcha c = new Captcha();
    	String request = c.Fetch(sessionID);
		String reply = server.ServerRequest(gameServer, c.url, request);
		System.out.println(reply);
		Response r = gson.fromJson(reply, Response.class);
		System.out.println("Please wait a moment while the captcha is loaded in a browser window");
		System.out.println("After the captcha has appeared enter the answer and push enter");
		//Console console = System.console();
		
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	        	URL url = new URL(r.result.url);
	            desktop.browse(url.toURI());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		
	    //for getting the answer
	    //Console console = System.console();
	    //System.out.println("test");
	    Scanner input = new Scanner(System.in);
	    String solution = input.next();
	    //String solution = console.readLine("Enter Answer ");
	    Captcha d = new Captcha();
	    request = d.Solve(sessionID, r.result.guid, solution);
	    //System.out.println(request);
	    reply = server.ServerRequest(gameServer, d.url, request);
	    System.out.println("note what the reply here looks like to see the indicator that the captcha worked so the if statment can be written");
	    System.out.println(reply);
	    
	    captchaValid = true;
	    //r = gson.fromJson(reply, Response.class);
	    //if(r.result.)
	    //System.out.println(reply);
	    //if r.result is equal to 1 the captcha worked
	    
    }
    static ArrayList<Integer> FindAllBuildingIDs(String buildingName, HashMap<Integer, Response.Building> buildings){
    	ArrayList<Integer> i = new ArrayList();
    	//String name ="";
    	Set<Integer> buildingkeys = buildings.keySet();
    	for(int j: buildingkeys){
    		if(buildings.get(j).name.equals(buildingName))
    			i.add(j);
    	} 	
    	return i;
    }
    static int FindBuildingID(String buildingName, HashMap<Integer, Response.Building> buildings){
    	int i = 0;
    	//String name ="";
    	Set<Integer> buildingkeys = buildings.keySet();
    	for(int j: buildingkeys){
    		if(buildings.get(j).name.equals(buildingName))
    			return j;
    	} 	
    	return i;
    }
    
    static void SeperateSSandPlanets(){
    	Set<String> buildingkeys = planetList.keySet();
    	int endloop = 0;
    	if (planets.isEmpty()) //stops the method from running if the planet list is full
    	{
    	for(String j: buildingkeys){
    		Body body = new Body();
    		String request = body.GetBuildings(1, sessionID, j);
    		String reply = server.ServerRequest(gameServer, body.url, request);
    		Response r = gson.fromJson(reply, Response.class);
    		if (r.result.body.surface_image.equals("surface-station")) { 
    			stations.put(j, r.result);
    			stationNames.add(r.result.status.body.name);
    			System.out.println("Station "+r.result.status.body.name);
    		}
    		else{
    			planets.put(j, r.result);
    			planetNames.add(r.result.status.body.name);
    			System.out.println("Planet "+r.result.status.body.name);
    		}
    		
    		//System.out.println(j);
    		endloop++;  //remove this when done testing
    		if (endloop == 3)
    			break;
    	}
    	}
    	Collections.sort(planetNames);
    	Collections.sort(stationNames);
    }//seperates planets from stations and retrieves their building info
    static void PlanetStatusCheck(){
    	//for(String r : planets)
    	for(Entry<String, Result> e : planets.entrySet()){
    		System.out.println(e.getValue().status.body.name);
    		System.out.println(e.getKey());
    		if(Double.parseDouble(e.getValue().status.body.num_incoming_enemy) > 0)
    			System.out.println("Enemy Incoming: "+e.getValue().status.body.num_incoming_enemy);
    		if(Double.parseDouble(e.getValue().status.body.plots_available) < 0)
    			System.out.println("Negative plots likely bleeders present or planet out of orbit: "+ e.getValue().status.body.plots_available);
    		
    		if(e.getValue().status.body.water_stored < 50000)
    			System.out.println("Low Water: "+ e.getValue().status.body.water_stored);
    		if(e.getValue().status.body.water_hour < 50000)
    			System.out.println("Low Water Production: "+ e.getValue().status.body.water_hour);
    		
    		if(e.getValue().status.body.energy_stored < 50000)
    			System.out.println("Low Energy: "+ e.getValue().status.body.energy_stored);
    		if(e.getValue().status.body.energy_hour < 50000)
    			System.out.println("Low Energy production: "+ e.getValue().status.body.energy_hour);
    		
    		if(e.getValue().status.body.food_stored < 50000)
    			System.out.println("Low food: "+ e.getValue().status.body.food_stored);
    		if(e.getValue().status.body.food_hour < 50000)
    			System.out.println("Low food production: "+ e.getValue().status.body.food_hour);
    		
    		if(e.getValue().status.body.ore_stored < 50000)
    			System.out.println("Low ore: "+ e.getValue().status.body.ore_stored);
    		if(e.getValue().status.body.ore_hour < 50000)
    			System.out.println("Low ore production: "+ e.getValue().status.body.ore_hour);
    				
    		if(e.getValue().status.body.happiness < 50000)
    			System.out.println("Low happiness: "+ e.getValue().status.body.happiness);
    		if(e.getValue().status.body.happiness_hour < 50000)
    			System.out.println("Low happiness: "+ e.getValue().status.body.happiness_hour);
    	}
    }
    static void StationStatusCheck(){
    	//for(String r : planets)
    	for(Entry<String, Result> e : planets.entrySet()){
    		System.out.println(e.getValue().status.body.name);
    		System.out.println(e.getKey());
    		if(Double.parseDouble(e.getValue().status.body.num_incoming_enemy) > 0)
    			System.out.println("Enemy Incoming: "+e.getValue().status.body.num_incoming_enemy);
    		if(Double.parseDouble(e.getValue().status.body.plots_available) < 0)
    			System.out.println("Negative plots likely bleeders present or planet out of orbit: "+ e.getValue().status.body.plots_available);
    		
    		if(e.getValue().status.body.water_stored < 50000)
    			System.out.println("Low Water: "+ e.getValue().status.body.water_stored);
    		if(e.getValue().status.body.water_hour < 50000)
    			System.out.println("Low Water Production: "+ e.getValue().status.body.water_hour);
    		
    		if(e.getValue().status.body.energy_stored < 50000)
    			System.out.println("Low Energy: "+ e.getValue().status.body.energy_stored);
    		if(e.getValue().status.body.energy_hour < 50000)
    			System.out.println("Low Energy production: "+ e.getValue().status.body.energy_hour);
    		
    		if(e.getValue().status.body.food_stored < 50000)
    			System.out.println("Low food: "+ e.getValue().status.body.food_stored);
    		if(e.getValue().status.body.food_hour < 50000)
    			System.out.println("Low food production: "+ e.getValue().status.body.food_hour);
    		
    		if(e.getValue().status.body.ore_stored < 50000)
    			System.out.println("Low ore: "+ e.getValue().status.body.ore_stored);
    		if(e.getValue().status.body.ore_hour < 50000)
    			System.out.println("Low ore production: "+ e.getValue().status.body.ore_hour);
    		
    	}
    }
    //Planetary Controls
    static void PrintPlanetControlsMenu(){
    	System.out.println("1: Perform Status Check.  This will sort bodies from SS if it's the first time it's run");
    	System.out.println("2: Individual Planet Menu");
    	System.out.println("3: Empire Wide Options");
    	System.out.println("0: To return to the main menu");
    }
    static void PlanetControlsMenu(){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	
    	do{
    		//PrintPlanetControlsMenu();
    		if(planets.isEmpty()){
    			System.out.println("Getting planets and Stations, this may take a few minutes");
    			SeperateSSandPlanets();
    		}
    		PrintPlanetControlsMenu();
    		try{
    			control = input.nextInt();
    			switch(control){
    			case 1:
    				//SeperateSSandPlanets();
    				PlanetStatusCheck();
    				break;
    			case 2:
    				IndividualPlanetsMenu();
    				break;
    			case 0:
    				MainMenu();
    				break;
    			default:
    				System.out.println("Invalid Selection");
    				PrintPlanetControlsMenu();
    			}
    				
    			
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");
    		}
    		
    	}while(i==0);
    	input.close();
    }
    static void PrintPlanetsListMenu(){
    	//Collections.sort(planetList);
    	Set<String> id = planetList.keySet();
    	int i = 0;
    	System.out.println("Select the number of the planet");
    	for(String name: planetNames){
    		System.out.println(++i +": "+name);
    	}
    	System.out.println("Select 0 to return to the main menu");
    }
    static void IndividualPlanetsMenu(){
    	int control;
    	int i = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		try{
    		PrintPlanetsListMenu();
    		control = input.nextInt();
    		if(control == 0)
    			i = 1;
    		if(control > planetNames.size())
    			System.out.println("Invalid Selection.");
    		else{
    			IndividualPlanetOptionsMenu(GetPlanetID(planetNames.get(control -1)));
    		//String planetID = GetPlanetID(planetNames.get(control -1));
    		
    		//System.out.println("control = "+control);
    		//System.out.println(planetID);
    		}
    		}catch(InputMismatchException e){
    			System.out.println("Invalid selection.");
    		}
    	}while (i == 0);
    	input.close();
    }
    static String GetPlanetID(String name){
    	String id = "";
    	Set<String> ids = planetList.keySet();
    	for (String i: ids){
    		if(planetList.get(i).equals(name)){
    			System.out.println(planetList.get(i) );
    			return id = i;
    		}
    	}
    	return id = "0";
    }
    static void PrintIndividualPlanetOptionsMenu(){
    	System.out.println("1: Repair All Buildings");
    	System.out.println("2: Fill all shipyards with ships of a selected type");
    	System.out.println("3: Spaceport");
    	System.out.println("4: Spies");
    	System.out.println("6: Print all buildings on planet");
    	System.out.println("0: Return to Planets Menu");
    }
    static void IndividualPlanetOptionsMenu(String planetID){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintIndividualPlanetOptionsMenu();
    		try{
    			control = input.nextInt();
    			switch(control){
    			case 1:
    				RepairAllPlanetBuildings(planetID);
    				break;
    			case 2:
    				MenuFillShipyardsWith(planetID);
    				break;
    			case 3:
    				//Spaceports
    				ViewShips(planetID);
    				break;
    			case 4:
    				//Intelministry
    				ArrayList <Spies> spies =  GetSpies(planetID);
    				AssignAllSpies(spies, planetID, "Counter Espionage");
    				break;
    			case 5:
    				//Trade
    			case 6:
    				PrintAllBuildingsOnPlanet(planetID);
    				break;
    			case 0:
    				i = 1;
    				break;
    			default:
    				System.out.println("Invalid Selection");
    			}
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");    			
    		}catch(NoSuchElementException e) {
    			System.out.println("No Such Element Exception, contro ="+control);
    		}
    	}while(i==0);
    	input.close();	
    }
//Spaceport methods
    static void ViewShips(String planetID){
    	int bID = FindBuildingID("Space Port", planets.get(planetID).buildings);
    	Spaceport spaceport = new Spaceport("spaceport");
    	String request = spaceport.View(sessionID, String.valueOf(bID));//spaceport.ViewAllShips(sessionID, String.valueOf(bID));
    	System.out.println(request);
    	System.out.println(spaceport.url);
    	String reply = server.ServerRequest(gameServer, spaceport.url, request);
    	System.out.println(reply);
    }
//Shipyard methods
    static void MenuFillShipyardsWith(String planetID){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		System.out.println("Fill shipyards with:");
        	System.out.println("1: Snark3");
        	System.out.println("2: Sweeper");
        	System.out.println("3: Fighter");
        	System.out.println("4: Scow");
        	System.out.println("5: Scow Fast");
        	System.out.println("6: Scow Large");
        	System.out.println("7: Scow Mega, Warning these take a long time to build");
        	System.out.println("8: Fissure Sealer");
        	System.out.println("9: Excavator");
        	System.out.println("10: Hulk Fast");
        	System.out.println("11: Hulk Huge");
        	System.out.println("12: detonator");
        	System.out.println("13: Smuggler Ship");
        	System.out.println("14: Placebo 6");
        	System.out.println("15: Bleeder");
        	System.out.println("16: Thud");
        	System.out.println("17: Sec. Min. Seeker");
        	System.out.println("18: Supply Pod 4");
        	System.out.println("0: Return to previous menu");
        	
    		try{
    			control = input.nextInt();
    			switch(control){
    			case 1:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "snark3");
    				break;
    			case 2:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "sweeper");
    				break;
    			case 3:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "fighter");
    				break;
    			case 4:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "scow");
    				break;
    			case 5:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "scow_fast");
    				break;
    			case 6:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "scow_large");
    				break;
    			case 7:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "scow_mega");
    				break;
    			case 8:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "fissure_sealer");
    				break;
    			case 9:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "excavator");
    				break;
    			case 10:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "hulk_fast");
    				break;
    			case 11:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "hulk_huge");
    				break;
    			case 12:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "detonator");
    				break;
    			case 13:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "smuggler_ship");
    				break;
    			case 14:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "placebo6");
    				break;
    			case 15:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "bleeder");
    				break;
    			case 16:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "thud");
    				break;
    			case 17:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "security_ministry_seeker");
    				break;
    			case 18:
    				FillAllShipyardsWithShipTypeOnePlanet(planetID, "supply_pod4");
    				break;
    			case 0:
    				i = 1;
    				break;
    			default:
    				System.out.println("Invalid Selection");
    			}
	
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");    			
    		}    		
    	}while(i==0);
    	input.close();
    	
    }
    static void FillAllShipyardsWithShipTypeOnePlanet(String planetID, String shipType){
    	ArrayList<Integer> shipyards = FindAllBuildingIDs("shipyard", planets.get(planetID).buildings);
    	String buildingLevel, request, reply;
    	int shipCount = 0;
    		for(int bID: shipyards){
    			Shipyard shipyard = new Shipyard();
    			buildingLevel = planets.get(planetID).buildings.get(bID).level;
    			request = shipyard.BuildShip(sessionID, String.valueOf(bID), shipType, Integer.valueOf(buildingLevel));
    			reply = server.ServerRequest(gameServer, shipyard.url, request);
    			shipCount += Integer.valueOf(buildingLevel);
    		}
    	System.out.println(shipCount +" "+ shipType +" now under construction on "+ planetList.get(planetID));
    }
    static void PrintAllBuildingsOnPlanet(String planetIDNumber){
    	HashMap<Integer, Response.Building> buildings = planets.get(planetIDNumber).buildings;
    	Set<Integer> buildingkeys = buildings.keySet();
    	Response.Building b;
    	String bnumber;
    	//int buildingsRepaired = 0;
    	for(Integer j: buildingkeys){
    		bnumber = String.valueOf(j);
    		b = buildings.get(j);
    		System.out.println(b.name);
    	}
    }
    static void RepairBuilding(String buildingID, String buildingName){
    	buildingName.toLowerCase();
    	buildingName.replace(" ", "");
    	//String y = buildingName.toLowerCase();
    	//y = y.replace(" ", "");
    	//System.out.println(buildingName);
		Buildings b = new Buildings(buildingName);
		String request = b.Repair(sessionID, buildingID);
		String reply = server.ServerRequest(gameServer, b.url, request);
		Response r = gson.fromJson(reply, Response.class);
    }
    static void RepairAllPlanetBuildings(String planetIDNumber){
    	//dumbly iterates through all buildings on a planet attempting repairs if efficiency is less than 0
    	//Will save lost city for last as it's repairs are the most expensive
    	HashMap<Integer, Response.Building> buildings = planets.get(planetIDNumber).buildings;
    	Set<Integer> buildingkeys = buildings.keySet();
    	Response.Building b;
    	String bnumber;
    	int buildingsRepaired = 0;
    	for(Integer j: buildingkeys){
    		bnumber = String.valueOf(j);
    		b = buildings.get(j);
    		if(Integer.parseInt(b.efficiency)<100){
    			System.out.println("Repairing Building: "+b.name);
    			RepairBuilding(bnumber,b.name );
    			buildingsRepaired++;
    		}
    	}
    	System.out.println(buildingsRepaired + " buildings repaired");
    }
    //static void RepairAllBuildings(){}
    //static void RepairGlyphBuildings(){}
 
    //Intelligence Methods
    static ArrayList<Spies> GetSpies(String planetID){
    	int bID = FindBuildingID("Intelligence Ministry", planets.get(planetID).buildings);
    	Intelligence intel = new Intelligence("intelligence");
    	String request = intel.ViewAllSpies(sessionID, String.valueOf(bID));
    	String reply = server.ServerRequest(gameServer, intel.url, request);
    	Response r = gson.fromJson(reply, Response.class);
    	return r.result.spies;
    }
    static void AssignAllSpies(ArrayList <Spies> spies, String planetID, String assignment){
    	if(captchaValid==false)
    		Captcha();
    	int bID = FindBuildingID("Intelligence Ministry", planets.get(planetID).buildings);
    	String request, reply;
    	for(Spies s: spies){
    		Intelligence intel = new Intelligence("intelligence");
    		request = intel.AssignSpy(sessionID, String.valueOf(bID), s.id, assignment);
    		reply = server.ServerRequest(gameServer, intel.url, request);
    		
    	}
    }
    static void SendSpies(){
    	
    }
    static void SpyRun(){
    	
    }
    static void FetchSpies(){
    	
    }
    static void TrainNewSpies(){
    	
    }
    static void SetSpiesMinistryTraining(){
    	
    }
    
    //Station Controls
    static void PrintSSControlsMenu(){
    	System.out.println("1: Perform Status Check.  This will sort bodies from SS if it's the first time it's run");
    	System.out.println("0: To return to the main menu");
    }
    static void SSControlsMenu(){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintPlanetControlsMenu();
    		try{
    			control = input.nextInt();
    			switch(control){
    			case 1:
    				SeperateSSandPlanets();
    				StationStatusCheck();
    				break;
    			case 0:
    				MainMenu();
    				break;
    			default:
    				System.out.println("Invalid Selection");
    				MessageBoxMenu();
    			}					
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");			
    		}		
    	}while(i==0);
    	input.close();
    }
    static void PrintSSList(){}
    
    //Message Controls
    static void PrintMessageBoxMenu(){
    	System.out.println("Mail Box: You have "+newMessages +" new messages");
    	System.out.println("Enter a number for your selection");
    	System.out.println("1, Correspondence");
    	System.out.println("2, Intelligence");
    	System.out.println("3, Spies");
    	System.out.println("4, Trade");
    	System.out.println("5, Complaint");
    	System.out.println("6, Excavator");
    	System.out.println("7, Parliament");
    	System.out.println("8, Alert");
    	System.out.println("9, Attack");
    	System.out.println("0, Return to Main Menu");
    }
    static void MessageBoxMenu(){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintMessageBoxMenu();
    		try{
    			//System.out.println("starting try block");
    			control = input.nextInt();
    			//System.out.println(control);
    			switch(control){
    			case 1:
    				MessageBoxReader("Correspondence");
    				break;
    			case 2:
    				MessageBoxReader("Intelligence");
    				break;
    			case 3:
    				MessageBoxReader("Spies");
    				break;
    			case 4:
    				MessageBoxReader("Trade");
    				break;
    			case 5:
    				MessageBoxReader("Complaint");
    				break;
    			case 6:
    				MessageBoxReader("Excavator");
    				break;
    			case 7:
    				MessageBoxReader("Parliament");
    				break;
    			case 8:
    				MessageBoxReader("Alert");
    				break;
    			case 0:
    				MessageBoxReader("Attack");
    				break;
    			default:
    				System.out.println("Invalid Selection");
    				MessageBoxMenu();
    			}
    				
    			
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");
    			
    		}
    		
    	}while(i==0);
    	input.close();
    }
    static void MessageBoxReader(String type){
    	System.out.println(type+" Messages");
    	Inbox in = new Inbox();
    	String i = in.ViewInbox(sessionID, type);
    	
    	//sends out request
    	i = server.ServerRequest(gameServer, in.url, i);
    	Response r = gson.fromJson(i, Response.class);
    	
    	int o = 0; //for menu control
    	for(Messages message : r.result.messages){ //iterates through received messages and prints out a selection menu
    		System.out.println("\nPress "+(o+1)+" to read message");
    		System.out.println(message.from);
    		System.out.println(message.subject);
    		System.out.println(message.body_preview);
    		o++;
    	}
    	System.out.println("\nEnter 0 to return to Message Main Menu");
    	
    	int c = 1;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		System.out.println("Select a message to read or 0 to return");
    		try{
    			control = input.nextInt();
    			if(control == 0){
    				c=0;
    				MessageBoxMenu();}
    			else{
    				control--;
    				i = in.ReadMessage(1, sessionID, Integer.toString(r.result.messages[(o-1)].id ));
    				i = server.ServerRequest(gameServer, in.url, i);
    				r = gson.fromJson(i, Response.class);
    				System.out.println(r.result.message.body);
    	    	}
    			
    		}catch(InputMismatchException e){
    			System.out.println("Not a valid selection.");
    			
    		}
    		
    	}while(c==0);
    	input.close();
    	//System.out.println(r.result.messages[1].subject);
    }
}

