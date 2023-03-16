package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notes.Activity.InsertNotesActivity;
import com.example.notes.Adapter.NotesAdapter;
import com.example.notes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecycler = findViewById(R.id.notesRecycler);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);




        newNotesBtn.setOnClickListener(view -> {

            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
            Toast.makeText(MainActivity.this, "Button is Working Properly", Toast.LENGTH_SHORT).show();
        });


        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecycler.setLayoutManager(new GridLayoutManager(this,2));
            adapter = new NotesAdapter(MainActivity.this,notes);
            notesRecycler.setAdapter(adapter);
        });
    }
}