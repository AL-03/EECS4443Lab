package com.example.eecs4443lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CheckBox showPassword;
    private CheckBox rememberMe;
    private SharedPreferences sharedPreferences;

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
        //Checkbox Ids
        showPassword=findViewById(R.id.showPassword);
        rememberMe=findViewById(R.id.rememberMe);

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
    }

    //this code would be a part of the login button logic
    //after user clicks button and information provided is valid
    /*
    //if user checked remember me, set loggedIn to true
    if (rememberMeCheckbox.isChecked()){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("loggedIn", true);
        editor.putString("username", usernameInput);
        editor.apply();
     */

}