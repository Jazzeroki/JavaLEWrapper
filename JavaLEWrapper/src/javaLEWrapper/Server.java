package javaLEWrapper;

//import com.google.gson.Gson;
import java.io.*;
import java.net.*;
public class Server {
	String Request(){
		
		return "nothing";
	}
    public String ServerRequest(String gameServer, String methodURL, String JsonRequest) {
        String i = null;
        //Gson gson = new Gson();
        try {
            URL url = new URL(gameServer+"/" + methodURL);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(JsonRequest);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output = in.readLine();
            i = output;
            //System.out.println(output);
            //response = gson.fromJson(output, Response.class);

        } catch (java.net.MalformedURLException e) {
            i = "Bad URL";
        } catch (java.io.IOException e) {
            i = "IO Exception";
        }
        return i;

    }

}
