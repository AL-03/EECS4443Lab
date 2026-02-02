package com.example.eecs4443lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    TextView messageBox;
    private Button loginButton;
    private Button cancelButton;
    private CheckBox rememberMe;
    private SharedPreferences sharedPreferences;
    
    //Default code for view empty activity android studio template
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

      
        // Access username and password TextInputEditText fields from xml
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        //Failure/success message
        messageBox = findViewById(R.id.messageBox);
        // Connect to login button in xml
        loginButton = findViewById(R.id.loginButton);
        // Connect to cancel button in xml
        cancelButton = findViewById(R.id.cancelButton);
        rememberMe=findViewById(R.id.rememberMe);

        // Make message blank
        messageBox.setText(" ");

        
        // When user presses cancel, clear the inputted text
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set both username and password to empty
                username.setText("");
                password.setText("");
            }
        });

        // Change screen to Welcome screen when login button pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
      
        sharedPreferences=getSharedPreferences("Login Preferences",MODE_PRIVATE);
        boolean loggedIn=sharedPreferences.getBoolean("loggedIn", false);

        // On relaunch, if user is logged in, skip login screen
        if (loggedIn){
            String username=sharedPreferences.getString("username","");
            Intent intent=new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }
    }

    //Takes users login information and verifies if it matches the accounts from the credentials.txt file.
    public void login(View view) {
        //Gets the username and password id's from the XML file.
        String usernameStr = username.getText().toString();
        String passStr = password.getText().toString();
        //Get remember me textbox from xml
        CheckBox rememberMeCheckbox = findViewById(R.id.rememberMe);
        //verifies credentials and checks to see if it matches the names from the credentials.txt file.
        if (validateCredentials(usernameStr, passStr)) {
            // if it matches, it shows the user the welcome page. Additionally, if the remember me checkbox is checked, it will save users credentials;
            if (rememberMeCheckbox.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn", true);
                editor.putString("username", usernameStr);
                editor.apply();
            }
            messageBox.setText("");
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("username", usernameStr);
            startActivity(intent);
        }
        else {
            // if it fails, it will say that the users credentials do not match and won't change pages.
            messageBox.setText("Login failed, please try again.");
        }
    }
    public boolean validateCredentials(String username, String password)
    {
        return (!username.isEmpty() && !password.isEmpty() && username.equals("anna24") && password.equals("abc123"));
    }
}



//UNFINISHED (bonus task): Allows a new user to be added to the system.
//public void register(String username, String password) {
//	validation.addCredentials(username, password);
//}
