package javaLEWrapper;
import com.google.gson.Gson;
import java.util.Set;

public class main {
    public static void main(String[] args) {
    	Gson gson = new Gson();
    	System.out.println("Jazz Command Console");
    	Empire empire = new Empire();
    	Server server = new Server();
    	String l = empire.Login("username", "password", 1);
    	System.out.println(l);
    	l = server.ServerRequest("https://us1.lacunaexpanse.com", "empire", l);
    	System.out.println(l);
    	Response response;
    	response = gson.fromJson(l, Response.class);
    	String BodyID = response.result.status.empire.home_planet_id;
    	String SessionID = response.result.session_id;
    	Body body = new Body();
    	l = body.GetBuildings(1, SessionID, BodyID);
    	System.out.println(l);
    	l = server.ServerRequest("https://us1.lacunaexpanse.com", "body", l);
    	System.out.println(l);
    	response = gson.fromJson(l, Response.class);
    	System.out.println(response.result.status.empire.planets.get(BodyID));
    	System.out.println(response.result.status.empire.planets.values());
    	System.out.println(response.result.status.empire.planets.keySet());
    	Set<Integer> buildingkeys = response.result.buildings.keySet();
    	//System.out.println(buildingkeys.)
    	System.out.println(response.result.body.surface_image);
    }

}
