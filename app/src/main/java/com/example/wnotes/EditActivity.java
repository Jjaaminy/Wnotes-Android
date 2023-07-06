package com.example.wnotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


public class EditActivity extends AppCompatActivity {

    private Button speichern;
    private EditText text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        speichern = findViewById(R.id.button);
        text = findViewById(R.id.edittext);

        if (getIntent().hasExtra("note")) {
            String note = getIntent().getStringExtra("note");
            text.setText(note);
        }

        speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedNote = text.getText().toString();
                MainActivity.addNewNote(editedNote);
                // Notiz speichern
                SharedPreferences sharedPreferences = getSharedPreferences("notes", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("note", editedNote);
                editor.apply();
                finish(); // EditActivity schließen            }
            }
        });
    }
}