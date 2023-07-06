package com.example.wnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularArray;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button hinzufügen;
    public ListView list;

    static ArrayList<String> notizenliste = new ArrayList<>();
    static ArrayAdapter<String> adapter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.button) {

            // Going from MainActivity to NotesEditorActivity
            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hinzufügen = findViewById(R.id.button);
        list = findViewById(R.id.recyclerView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("notes", Context.MODE_PRIVATE);
        ArrayList<String> set = (ArrayList<String>) sharedPreferences.getStringSet("notes", null);


        if (set == null) {
            notizenliste.add("Ex. note");
        } else {
            notizenliste = new ArrayList<>(set);
        }

        adapter = new ArrayAdapter<>(this, R.id.recyclerView, notizenliste);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("noteid", i);
                startActivity(intent);
            }
        });
    }
}