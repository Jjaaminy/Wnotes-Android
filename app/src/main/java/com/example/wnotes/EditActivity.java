package com.example.wnotes;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class EditActivity extends AppCompatActivity {
    private Button speichern;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speichern = findViewById(R.id.button);
        list = findViewById(R.id.recyclerView);
    }
}
