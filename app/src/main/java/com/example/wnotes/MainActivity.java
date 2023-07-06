package com.example.wnotes;


import androidx.appcompat.app.AppCompatActivity;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button hinzufügen;
    public ListView list;

    private TextView textView;

    public static ArrayList<String> notizenliste = new ArrayList<>();
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

        loadNotesFromSharedPreferences();

        String savedNotes = sharedPreferences.getString("notes", "");
        if (!savedNotes.isEmpty()) {
            String[] notesArray = savedNotes.split(",");
            for (String note : notesArray) {
                notizenliste.add(note);
            }
        }

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
        hinzufügen.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
        });
    }





    //hinzufügen zu der Liste
    public static void addNewNote(String note) {
        if (!notizenliste.contains(note)) {
            notizenliste.add(note);
            adapter.notifyDataSetChanged();
            saveNotesToSharedPreferences();
            Log.d("MainActivity", "Anzahl der Notizen: " + notizenliste.size());

        }
    }

    //löschen
    public static void deleteNote(int position) {
        notizenliste.remove(position);
        adapter.notifyDataSetChanged();
        saveNotesToSharedPreferences();
        Log.d("MainActivity", "Anzahl der Notizen: " + notizenliste.size());

    }


    private static void saveNotesToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder notesString = new StringBuilder();
        for (String note : notizenliste) {
            notesString.append(note).append(",");
        }
        editor.putString("notes", notesString.toString());
        editor.apply();
    }

    private void loadNotesFromSharedPreferences() {
        String savedNotes = sharedPreferences.getString("notes", "");
        if (!savedNotes.isEmpty()) {
            String[] notesArray = savedNotes.split(",");
            for (String note : notesArray) {
                notizenliste.add(note);

            }
        }
        Log.d("MainActivity", "Anzahl der Notizen: " + notizenliste.size());
    }

    public static ArrayList<String> getNotizenliste() {
        return notizenliste;
    }
}