package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper database = new DBHelper(sqLiteDatabase);
        notes = database.readNotes(username);
        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes){
            displayNotes.add(String.format("Title:%s\nDate:%s",note.getTitle(),note.getDate()));
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra( "nodeid", position);
                startActivity(intent);
            }
        });
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
                goToMainActivity3();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity3(){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }

}