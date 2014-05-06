package javaLEWrapper.Wrapper;

//import com.google.gson.Gson;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.google.gson.Gson;
public class Server {
	public String ServerRequest(String gameServer, String methodURL, String JsonRequest, String sessionID) { //for use with methods that may require a captcha
        String output = "";
        //Gson gson = new Gson();
        try {
        	try { //putting thread to sleep for just over a second to throttle client because of the limit of 60 calls per minute
        	    Thread.sleep(1100);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
            URL url = new URL(gameServer+"/" + methodURL);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(JsonRequest);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = in.readLine();
            
            //adding captcha support
            Gson gson = new Gson();
            Response r = gson.fromJson(output, Response.class);
            if(r.result.guid.length()>2){
            	Captcha c = new Captcha();
            	String request = c.Fetch(sessionID);
            	
            	output = ServerRequest(gameServer, methodURL, request);
            	r = gson.fromJson(output, Response.class);
            	
            	Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        	        try {
        	        	URL captchaURL = new URL(r.result.url);
        	            desktop.browse(captchaURL.toURI());
        	        } catch (Exception e) {
        	            e.printStackTrace();
        	        }
        	        Scanner input = new Scanner(System.in);
        		    String solution = input.next();
        		    //String solution = console.readLine("Enter Answer ");
        		    Captcha d = new Captcha();
        		    request = d.Solve(sessionID, r.result.guid, solution);
        		    System.out.println(request);
        		    output = ServerRequest(gameServer, d.url, request);
        		    System.out.println(output);
        	    }

            }
            


        } catch (java.net.MalformedURLException e) {
        	System.out.println("Server Error IO Exception possible bad url");
            output = "Bad URL";
        } catch (java.io.IOException e) {
        	System.out.println("Server Error IO Exception possible bad url");
            output = "Server IO Exception";
        }
        return output;

    }
    public String ServerRequest(String gameServer, String methodURL, String JsonRequest) {
        String output = "";
        //Gson gson = new Gson();
        try {
        	try { //putting thread to sleep for just over a second to throttle client because of the limit of 60 calls per minute
        	    Thread.sleep(1100);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
            URL url = new URL(gameServer+"/" + methodURL);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(JsonRequest);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            output = in.readLine();
        } catch (java.net.MalformedURLException e) {
        	System.out.println("Server Error IO Exception possible bad url");
            output = "Bad URL";
        } catch (java.io.IOException e) {
        	System.out.println("Server Error IO Exception possible bad url");
            output = "Server IO Exception";
        }
        return output;

    }

}
