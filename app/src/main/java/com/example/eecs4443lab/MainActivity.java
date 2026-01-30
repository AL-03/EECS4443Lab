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
	}

	}
    //Takes users login information and verifies if it matches the accounts from the credentials.txt file.
	public void login(View view)
		//Gets the username and password id's from the XML file.
		String username = findViewById(R.id.username).getText().toString();
		String password = findViewById(R.id.password).toString();
        //verifies credentials and checks to see if it matches the names fromt the credentials.txt file.
		if (validation.validateCredentials(username, password)) {
			// if it matches, it shows the user the welcome page.
		} else {
			// if it fails, it will say that the users credentials do not match and won't change pages.
		}
	}

    //Allows a new user to be added to the sytem.
	public void register(String username, String password) {
		validation.addCredentials(username, password);
	}
}

