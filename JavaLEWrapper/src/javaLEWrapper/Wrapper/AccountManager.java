package javaLEWrapper.Wrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class AccountManager {
	static Gson gson = new Gson();
	Accounts accounts;
	AccountManager(){
		accounts=new Accounts();
		if(!new File("accounts.jazz").isFile()) //if an account file doesn't exist one is created
		    CreateAccount();
		else{
		BufferedReader br = null;
		String i = "";
		try{
			br = new BufferedReader(new FileReader("accounts.jazz"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	            i = sb.toString();
	            }
	       accounts = gson.fromJson(i, Accounts.class);
		}catch (FileNotFoundException e){
			CreateAccount();
	     }catch(IOException e){
	    	 
	     }
		}
	}
	void Save(){
		System.out.println("Starting Save, serializing account");
		String i = gson.toJson(accounts, Accounts.class);
		System.out.println("serialized");
		try {
			PrintWriter writer = new PrintWriter("accounts.jazz");
			System.out.println("saving");
			writer.print(i);
			System.out.println("closing writer");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			
		}
	}
	AccountInfo SelectAccountMenu(){
		Scanner input = new Scanner(System.in);
		int control = 0;
		boolean x = false;
		do{
			System.out.println("Select and account");
			int counter = 0;
			for(AccountInfo j: accounts.accounts){
				counter++;
				System.out.println(counter+" : "+j.userName);
			}
			System.out.println("requesting input");
			boolean z = false;
			do{  //Setting up this do loop with a try because sometimes input throws an error because what is there isn't a valid int.
				try{
					control = input.nextInt();
					System.out.println("setting z = true");
					z = true;
				}catch(java.util.NoSuchElementException e){
					
				}
				
			
			}while(z == false);
			if(control == 0){
				System.out.println("calling create account");
				CreateAccount();
			}
			else if(control > accounts.accounts.size())
    			System.out.println("Invalid Selection.");
    		else{
    			System.out.println("returning selected account");
    			return accounts.accounts.get((control -1));	
    		}
		}while(x == false);
		return null;
	}
	void CreateAccount(){
		AccountInfo a = new AccountInfo();
		Scanner input = new Scanner(System.in);
		int control = 0;
        int i = 1;
        //do{
        	do {
              try {
            	  System.out.println("Select Server \n 1, US1\n 2, PT \n 3, ICD");
            	  control = input.nextInt();
              	switch(control){
            	case 1:
            		a.server = "https://us1.lacunaexpanse.com";
            		i = 0;
            		break;
            	case 2:
            		a.server = "https://pt.lacunaexpanse.com";
            		i = 0;
            		break;
            	case 3:
            		a.server = "http://lacuna.icydee.com";
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
        				
		System.out.println("Enter Username");
		a.userName = input.next();
		System.out.println("Enter Password");
		a.password = input.next();
		a.aPIKey = "";
		System.out.println("Adding account to list");
		accounts.accounts.add(a);
		input.close();
		Save();
	}
//	void LoadFromFile(){}
	void SaveToFile(String userName, String password, String apiKey, String server){
		Gson g = new Gson();
		//private StringWriter w = new StringWriter();
		//private JsonWriter writer = new JsonWriter(w);
		try {
			AccountInfo account = new AccountInfo();
			account.userName = userName;
			account.password = password;
			account.aPIKey = apiKey;
			account.server = server;
			
			String i = g.toJson(account);
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
class Accounts{
	Accounts(){
		accounts = new ArrayList<AccountInfo>();
	}
	ArrayList<AccountInfo> accounts;
}
class AccountInfo{
	String userName, password, aPIKey, server;
}