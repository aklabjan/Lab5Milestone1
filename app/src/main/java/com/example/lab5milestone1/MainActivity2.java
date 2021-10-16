package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("Welcome "+ username + "!");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                sharedPreferences.edit().remove("password").apply();
                goToMainActivity();
                return true;
            case R.id.addNotes:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}