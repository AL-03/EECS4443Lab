import android.content.Context;
import java.io.*;
import java.util.HashMap;

public class Validation {
	
	HashMap<String, String> registeredUsers = new HashMap<>();
    BufferedReader bf = new BufferedReader(context.getAssets().open("credentials.txt")); 
    
    public Validation()
    {
        for(int i = 0; i < 100; i++)
        {
           String line = bf.readLine();
           registeredUsers.put(line.substring(0, line.indexOf(":")), line.substring(line.indexOf(":") +  1, line.length()));
        }
    }
	

	public void addCredentials(String username, String password)
	{
		registeredUsers.put(username, password);
	}
	
	public boolean validateCredentials(String username, String password)
	{   
		return !username.isEmpty() && !password.isEmpty() && registeredUsers.containsKey(username) && registeredUsers.get(username).equals(password);
	}

}
