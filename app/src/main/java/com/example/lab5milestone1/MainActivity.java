package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
       if(!sharedPreferences.getString(usernameKey,"").equals("")){
            goToActivity2();
       } else {
           setContentView(R.layout.activity_main);
       }
    }

    public void clickFunction(View view){
        EditText usernameText = (EditText)findViewById(R.id.usernameText);
        String username = usernameText.getText().toString();
        EditText passwordText = (EditText)findViewById(R.id.passwordText);
        String password = passwordText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", username).apply();
        sharedPreferences.edit().putString("password", password).apply();
        goToActivity2();
    }

    public void goToActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}