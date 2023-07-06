package com.example.wnotes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;


public class EditActivity extends AppCompatActivity {
    int idnotes;
    private Button speichern;
    private EditText text;
    Intent intent = getIntent();


    SharedPreferences.Editor editor = getSharedPreferences("edit",MODE_PRIVATE).edit();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speichern = findViewById(R.id.button);
        text = findViewById(R.id.edittext);

        idnotes = intent.getIntExtra("id", -1);
        if (idnotes != -1) {
            text.setText(MainActivity.notizenliste.get(idnotes));
        } else {
            MainActivity.notizenliste.add("");
            idnotes = MainActivity.notizenliste.size() - 1;
            MainActivity.adapter.notifyDataSetChanged();
        }

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence input, int i, int i1, int i2) {
                MainActivity.notizenliste.set(idnotes, String.valueOf(input));
                MainActivity.adapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("noteslist", Context.MODE_PRIVATE);
                ArrayList<String> set = new ArrayList<String>(MainActivity.notizenliste);
                sharedPreferences.edit().putStringSet("note", (Set<String>) set).apply();

            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        }
        );
    }
}
