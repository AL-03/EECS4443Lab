package com.example.eecs4443lab;

import android.content.Context;
import java.io.*;
import java.util.HashMap;

//Validates Log in Credentials.
public class Validation {

	//Hashmap for storing registered users and buffered reader for reading the credentials.txt file.
	HashMap<String, String> registeredUsers = new HashMap<>();
    BufferedReader bf = new BufferedReader(new File("app/src/main/assets/credentials.txt)); 

	//reads each line from the credentials.txt file and adds the usernames and passwords to the registeredUsers hashmap.
    public Validation()
    {
        for(int i = 0; i < 100; i++)
        {
           String line = bf.readLine();
           registeredUsers.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") +  1, line.length()));
        }
	}

	//Allows a new users credentials to be added to the system (used for the register feature).
	public void addCredentials(String username, String password)
	{
		registeredUsers.put(username, password);
	}

	//Makes sures that credentials provided by the user are not empty and actually match a username and password assosciated to it from the registered useres hashmap.
	public boolean validateCredentials(String username, String password)
	{   
		return !username.isEmpty() && !password.isEmpty() && registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
	}

}




