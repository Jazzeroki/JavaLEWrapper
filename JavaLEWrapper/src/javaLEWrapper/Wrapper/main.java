package javaLEWrapper.Wrapper;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
//import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
//import java.io.Console;
import java.net.URL;
import java.awt.Desktop;

import javaLEWrapper.Wrapper.Response.Messages;
import javaLEWrapper.Wrapper.Response.Result;


public class main {
	static String version = "Alpha 0.01";
	static String sessionID = null;
	static String gameServer = null;
	static Gson gson = new Gson();
	static Server server = new Server();	
	static int newMessages;
	static HashMap <String, String> planetList = new HashMap <String, String>() ;
	static HashMap <String, Response.Result> stations = new HashMap<>();
	static HashMap <String, Response.Result> planets = new HashMap<>();
    
	public static void main(String[] args) {
    	GetSession();
    	MainMenu();
    	//System.out.println(sessionID);
    	//Gson gson = new Gson();
    	//System.out.println("Jazz Command Console");
    	//Empire empire = new Empire();
    	//Server server = new Server();
    	//AccountManager a = new AccountManager();
    	//a.CreateAccount();
    	//String acc = a.ReadFromFile();
    	//System.out.println(acc);
    	//String l = empire.Login("", "", 1);
    	//System.out.println("Login Request"+l);
    	//l = server.ServerRequest("https://us1.lacunaexpanse.com", "empire", l);
    	//System.out.println("Login Response"+l);
    	/*
    	Response response;
    	//response = gson.fromJson(l, Response.class);
    	String BodyID = response.result.status.empire.home_planet_id;
    	String SessionID = response.result.session_id;
    	Body body = new Body();
    	l = body.GetBuildings(1, SessionID, BodyID);
    	//System.out.println(l);
    	l = server.ServerRequest("https://us1.lacunaexpanse.com", "body", l);
    	System.out.println(l);
    	response = gson.fromJson(l, Response.class);
    	
    	
    	
    	System.out.println(response.result.status.empire.planets.get(BodyID));
    	System.out.println(response.result.status.empire.planets.values());
    	System.out.println(response.result.status.empire.planets.keySet());
    	Set<Integer> buildingkeys = response.result.buildings.keySet();
    	//int buildingID = 0;
    	String buildingID = null;
    	String intelmin = "Intelligence Ministry";
    	for(int j: buildingkeys){
    		String name = response.result.buildings.get(j).name;
    		System.out.println(response.result.buildings.get(j).name+" "+j);
    		if(name.equals(intelmin)){
    			buildingID = Integer.toString(j);
    			System.out.println(buildingID);
    		}
    	}
    	if(response.result.buildings.containsValue(intelmin))
    		System.out.println("found ministry");
    	System.out.println(buildingID);
    	Intelligence intel = new Intelligence();
    	*/
    	/*
    	l = intel.View(1, SessionID, buildingID);
    	System.out.println("Request: view "+l);
    	l = server.ServerRequest("https://us1.lacunaexpanse.com", intel.url, l);
    	System.out.println("Response from server View "+l);
    	*/
    	//l = intel.ViewAllSpies(1, SessionID, buildingID);
    	//System.out.println("Request: View All Spies "+l);
    	//l = server.ServerRequest("https://us1.lacunaexpanse.com", intel.url, l);
    	//System.out.println("Response from Server View All Spies "+l);
    	
    	//AccountManager account = new AccountManager();
    	//System.out.println(buildingkeys.)
    	
    	//response.result.buildings.
    	//System.out.println(response.result.body.surface_image);
    	/*boolean j = false;
    	do{
    		
    	}while(j!=true);*/
    	
    	//code for testing intel ministry
    	//Intelligence intel = new Intelligence();
    	//l = intel.View(1, sessionID, buildingID)
    	
    	//Code for testing Inbox
    	/*Inbox inbox = new Inbox();
    	Inbox.MessageTags tag = null;
    	tag = tag.Correspondence;
    	l = inbox.ViewInbox(1, SessionID, tag);
    	System.out.println("Inbox request"+l);
    	l = server.ServerRequest("https://us1.lacunaexpanse.com", "inbox", l);
    	System.out.println("Inbox Response"+l);*/
    	
    	
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
	    System.out.println(request);
	    reply = server.ServerRequest(gameServer, d.url, request);
	    System.out.println(reply);
	    //if r.result is equal to 1 the captcha worked
	    
    }
    static int FindBuildingID(String buildingName, HashMap<Integer, Response.Building> buildings){
    	int i = 0;
    	if(buildings.containsValue(buildingName)){
    		System.out.println("found "+ buildingName);
    		Set<Integer> buildingkeys = buildings.keySet();
    		String name ="";
    		for(int j: buildingkeys){
        		name = buildings.get(j).name;
        		//System.out.println(buildings.get(j).name+" "+j);
        		if(name.equals(buildingName)){	
        			//buildingID = Integer.toString(j);
        			System.out.println(j);
        			i = j;
        			return i;
        		}
    		}
    	}
    	else{
    		
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
    			System.out.println(r.result.status.body.name);
    			//System.out.println(r.result.session_id);
    			//System.out.println(r.result.buildings);
    		}
    		else{
    			planets.put(j, r.result);
    			System.out.println(r.result.status.body.name);
    			//System.out.println(r.result.session_id);
    			//System.out.println(r.result.buildings);
    		}
    		System.out.println(j);
    		endloop++;
    		if (endloop == 10)
    			break;
    	}
    	}
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
    	System.out.println("0: To return to the main menu");
    }
    static void PlanetControlsMenu(){
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
    				PlanetStatusCheck();
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
    static void PrintPlanetList(){}
    
   
    static void RepairBuilding(String buildingID, String buildingName){
    	//buildingName is used in place of the url as all buildings inherit repair from building but use their own names for their urls
    	Buildings building = new Buildings();
		String request = building.Repair(sessionID, buildingID);
		String reply = server.ServerRequest(gameServer, buildingName, request);
		Response r = gson.fromJson(reply, Response.class);
    }
    static void RepairAllPlanetBuildings(String planetIDNumber){
    	//dumbly iterates through all buildings on a planet attempting repairs if efficiency is less than 0
    	//Will save lost city for last as it's repairs are the most expensive
    	HashMap<Integer, Response.Building> buildings = planets.get(planetIDNumber).buildings;
    	Set<Integer> buildingkeys = buildings.keySet();
    	Response.Building b;
    	String bnumber;
    	for(Integer j: buildingkeys){
    		bnumber = String.valueOf(j);
    		b = buildings.get(j);
    		if(Integer.parseInt(b.efficiency)<100){
    			System.out.println("Repairing Building: "+b.name);
    			RepairBuilding(bnumber,b.name );
    		}
    	}
    }
    //static void RepairAllBuildings(){}
    //static void RepairGlyphBuildings(){}
    
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

