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

    static SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hinzufügen = findViewById(R.id.button);
        list = findViewById(R.id.listview);

        sharedPreferences = getSharedPreferences("notes", Context.MODE_PRIVATE);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notizenliste);
        list.setAdapter(adapter);

        String savedNote = sharedPreferences.getString("note", "");
        notizenliste.add(savedNote);
        adapter.notifyDataSetChanged();

        //liste auswählen
        list.setOnItemClickListener((parent, view, position, id) -> {
            String selectedNote = notizenliste.get(position);
            // EditActivity öffnen und ausgewählte Notiz übergeben
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra("note", selectedNote);
            startActivity(intent);
        });

        list.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteNote(position);
            return true;
        });

        //button hinzufügen
        hinzufügen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    //hinzufügen zu der Liste
    public static void addNewNote(String note) {
        notizenliste.add(note);
        adapter.notifyDataSetChanged();
    }

    //löschen
    public static void deleteNote(int position) {
       notizenliste.remove(position);
       adapter.notifyDataSetChanged();
    }
}