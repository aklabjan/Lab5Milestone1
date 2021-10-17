package com.example.lab5milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    public int nodeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        nodeid = intent.getIntExtra("nodeid",-1);
        if (nodeid != -1){
            Note note = MainActivity2.notes.get(nodeid);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveMethod(View view){
        EditText editText = (EditText)findViewById(R.id.editText);
        String content = editText.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper database = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("Lab_5_Milestone_1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());
        if (nodeid == -1){
            title = "NOTE_" + (MainActivity2.notes.size()+1);
            database.saveNotes(username,title,content,date);
        } else{
            title = "NOTE_" + (nodeid + 1);
            database.updateNote(title,date,content,username);
        }
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}