package com.example.notes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;

import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityInsertNotesBinding;

import java.text.DateFormat;
import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(view -> {

            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";
        });

        binding.yellowPriority.setOnClickListener(view -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });


        binding.redPriority.setOnClickListener(view -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority = "3";
        });


        binding.doneNotesBtn.setOnClickListener(view -> {

            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();

            CreateNotes(title, subtitle, notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.getDateInstance().format(date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "Note Created Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }
}