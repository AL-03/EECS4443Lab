package com.example.eecs4443lab;

import android.content.Context;
import java.io.*;
import java.util.HashMap;
import android.content.res.AssetManager;

//Validates Log in Credentials.
public class Validation {

	//Hashmap for storing registered users and buffered reader for reading the credentials.txt file.
	HashMap<String, String> registeredUsers = new HashMap<>();
    //Used for reader list of user credentials
    BufferedReader bf;

    public Validation(Context context)
    {
        initCredentials(context);
    }

    //Hardcode initial list of user credentials
    public void initCredentials(Context c) {
        // Access credentials.txt
        try {
            bf = new BufferedReader(new InputStreamReader(c.getAssets().open("credentials.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Reads each line from the credentials.txt file and adds the usernames and passwords to the registeredUsers hashmap
        String line;
        while (true) {
            try {
                if ((line = bf.readLine()) == null) {
                    break;
                }
                else {
                    registeredUsers.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") +  1));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Makes sure that credentials provided by the user are not empty and actually match a username and password associated to it from the registered users hashmap.
    public boolean validateCredentials(String username, String password) {
        return !username.isEmpty() && !password.isEmpty() && registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
    }

	//Allows a new users credentials to be added to the system (used for the register feature).
	public void addCredentials(String username, String password)
	{
		registeredUsers.put(username, password);
	}
}



