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

    private CheckBox showPasswordCheckbox;
    private CheckBox rememberMeCheckbox;
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