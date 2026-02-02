package com.example.eecs4443lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

  private TextInputEditText username;
  private TextInputEditText password;
  private SharedPreferences sharedPreferences;
  private CheckBox rememberMeCheckbox;
	Validation validation = new Validation();
	TextView messageBox = findViewById(R.id.messageBox);
    
	//Default code for view empty activity android studio template
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);
		messageBox.setText("");
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
    
    // Connect to cancel button in xml
    Button cancelButton = findViewById(R.id.cancelButton);
    rememberMeCheckbox = findViewById(R.id.rememberMe);
    // When user presses cancel, clear the inputted text
    cancelButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Access username and password TextInputEditText fields from xml
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            // Set both username and password to empty
            username.setText("");
            password.setText("");
        }
    });
	}
	
  //Takes users login information and verifies if it matches the accounts from the credentials.txt file.
	public void login(View view)
	{
		//Gets the username and password id's from the XML file.
		String usernameStr = username.getText().toString();
		String passwordStr = password.toString();
        //verifies credentials and checks to see if it matches the names fromt the credentials.txt file.
		if (validation.validateCredentials(usernameStr, passwordStr)) {
			// if it matches, it shows the user the welcome page. Additionally, if the remember me checkbox is checked, it will save users credentials;
	       if (rememberMeCheckbox.isChecked()){
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("loggedIn", true);
            editor.putString("username", usernameStr);
            editor.apply();
		   }
		    messageBox.setText("");
	        // Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
	        //  intent.putExtra("username", usernameStr);
         	//startActivity(intent);
		
		} else {
			// if it fails, it will say that the users credentials do not match and won't change pages.
            messageBox.setText("Login failed, please try again.");
		}
	 }


    //Allows a new user to be added to the sytsem.

}
