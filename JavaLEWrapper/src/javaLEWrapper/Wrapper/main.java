package javaLEWrapper.Wrapper;
import com.google.gson.Gson;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class main {
	static String version = "Alpha 0.01";
	static String sessionID = null;
	static String gameServer = null;
	static Gson gson = new Gson();
	static Server server = new Server();
	
	static int newMessages;
	static Map <String, String> planetList;
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
    	String i = a.GetAccount();
    	//System.out.println(i);
    	if (i.isEmpty()){ //Get account returns null if the file isn't found, so create account, then get the account
    		//System.out.println("Account was empty");
    		a.CreateAccount();
    		i = a.GetAccount();
    	}
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
    }
    static void MainMenu(){
    	int i = 0;
    	int control = 0;
    	Scanner input = new Scanner(System.in);
    	do{
    		PrintMainMenu();
    		try{
    			System.out.println("starting try block");
    			control = input.nextInt();
    			System.out.println(control);
    			switch(control){
    			case 1:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 2:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 3:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 4:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
    				break;
    			case 5:
    				System.out.println("not implemented yet");
    				PrintMainMenu();
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
    static void SeperateSSandPlanets(){}
    
    //Planetary Controls
    static void PrintPlanetList(){}
    
    //Station Controls
    static void PrintSSList(){}
    
    
}

