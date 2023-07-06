package com.example.wnotes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EditActivity extends AppCompatActivity {
    private Button speichern;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speichern = findViewById(R.id.button);
        text = findViewById(R.id.recyclerView);
    }
}
