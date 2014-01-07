package javaLEWrapper.Wrapper;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class AccountManager {
	private Gson gson = new Gson();
	private StringWriter w = new StringWriter();
	private JsonWriter writer = new JsonWriter(w);
	
	void Menu(){}
	void CreateAccount(){
		Scanner input = new Scanner(System.in);
		String apiKey = "";
		//System.out.println("Select Server \n 1, US1\n 2, PT");
		String server = null;
		int control = 0;
        int i = 1;
        //do{
        	do {
              try {
            	  System.out.println("Select Server \n 1, US1\n 2, PT");
            	  control = input.nextInt();
              	switch(control){
            	case 1:
            		server = "https://us1.lacunaexpanse.com";
            		i = 0;
            		break;
            	case 2:
            		server = "https://pt.lacunaexpanse.com";
            		i = 0;
            		break;
            	default:
            		System.out.println("Select Server \n 1, US1\n 2, PT");
            		i = 1;
            	}
            	  //i = 0;
              	} catch (InputMismatchException e) {
              		System.out.println("Not a valid selection. \nPlease enter only numbers 1 or 2");
              		i = 1;
              }
        	} while (i == 1);
        		
       // } while (control != 0);
		
		System.out.println("Enter Username");
		String userName = input.next();
		System.out.println("Enter Password");
		String password = input.next();
		
		SaveToFile(userName, password, apiKey, server);
	}
//	void LoadFromFile(){}
	void SaveToFile(String userName, String password, String apiKey, String server){
		try {
			AccountInfo account = new AccountInfo();
			account.userName = userName;
			account.Password = password;
			account.APIKey = apiKey;
			account.Server = server;
			
			String i = gson.toJson(account);
			PrintWriter w = new PrintWriter("Account.Jazz","UTF-8");
			w.println(i);
			w.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String  GetAccount(){
	    BufferedReader br = null;
	    String i = null;
		try {
			//System.out.println("Reading File");
			br = new BufferedReader(new FileReader("Account.Jazz"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            //sb.append('\n');
	            line = br.readLine();
	            i = sb.toString();
	        }
	     }catch (FileNotFoundException e){
	     } catch (IOException e) {
	    	 System.out.println("Account was empty");
	    	 i = null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	    	 try {
				br.close();
	    	 	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
	    	 }
		//System.out.println("returning");
		return i;
		}
	}

class AccountInfo{
	String userName, Password, APIKey, Server;
}