import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

		//Checkbox Ids
        showPasswordCheckbox=findViewById(R.id.showPasswordCheckbox);
        rememberMeCheckbox=findViewById(R.id.rememberMeCheckBox);

        sharedPreferences=getSharedPreferences("Login Preferences",MODE_PRIVATE);

        boolean loggedIn=sharedPreferences.getBoolean("loggedIn", false);

        //on relaunch, if user is logged in, skip login screen
        if (loggedIn){
            String username=sharedPreferences.getString("username","");
            Intent intent=new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }

        showPassword();

		    // Connect to login button in xml
        loginButton = findViewById(R.id.login);
        // Change screen to Welcome screen when login button pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
	}
	
    //Takes users login information and verifies if it matches the accounts from the credentials.txt file.
	public void login(View view)
		//Gets the username and password id's from the XML file.
		String username = findViewById(R.id.username).getText().toString();
		String password = findViewById(R.id.password).toString();
        //verifies credentials and checks to see if it matches the names fromt the credentials.txt file.
		if (validation.validateCredentials(username, password)) {
			// if it matches, it shows the user the welcome page. Additionally, if the remember me checkbox is checked, it will save users credentials;
	       if (rememberMeCheckbox.isChecked()){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("loggedIn", true);
            editor.putString("username", usernameInput);
            editor.apply();
		    messageBox.setText("");
	        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);	
	        intent.putExtra("username", username);
         	startActivity(intent);
		
		} else {
			// if it fails, it will say that the users credentials do not match and won't change pages.
            messageBox.setText("Login failed, please try again.");
		}
	}

    //Allows a new user to be added to the sytem.
	public void register(String username, String password) {
		validation.addCredentials(username, password);
	}

    private void showPassword(){
        showPasswordCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            //if user checks show password, password visibility will be set to on
            if (showPasswordCheckbox.isChecked()){
                passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            //if user unchecks show password, password visibility will be set to off
            else{
                passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            passwordText.setSelection(passwordText.length());
        });
    }
}





