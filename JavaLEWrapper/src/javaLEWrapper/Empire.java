package javaLEWrapper;
//package JavaLEWrapper;

import com.google.gson.*;

public class Empire {
	private Gson gson = new Gson();
	String Login(String Username, String Password, int requestID ){
		LoginObject login = new LoginObject(requestID, Username, Password);
		return gson.toJson(login);
	}
	String FetchCaptcha(){
		
		return "nothing";
	}
	private class LoginObject{

	    LoginObject(int i, String u, String p) {
	        params[0] = u;
	        params[1] = p;
	        params[2] = "6266769d-1f73-4325-a40f-6660c4c6440d";
	        id = i;
	    }
	    String jsonrpc = "2.0";
	    int id;
	    String method = "login";
	    String[] params = new String[3];
	}

}
/*
class Login{

    Login(int i, String u, String p) {
        params[0] = u;
        params[1] = p;
        params[2] = "6266769d-1f73-4325-a40f-6660c4c6440d";
        id = i;
    }
    String jsonrpc = "2.0";
    int id;
    String method = "login";
    String[] params = new String[3];
} */