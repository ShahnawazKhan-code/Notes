package com.example.notes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.Model.Notes;
import com.example.notes.R;
import com.example.notes.ViewModel.NotesViewModel;
import com.example.notes.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority = "1";
    String stitle, ssubtitle, snotes, spriority;
    int sid;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("sutitle");
        snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);

        if ("1".equals(spriority)) {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
        } else if ("2".equals(spriority)) {
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        } else if ("3".equals(spriority)) {
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }


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


        binding.updateNotesBtn.setOnClickListener(view -> {

            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String notes = binding.upNotes.getText().toString();

            UpdateNotes(title, subtitle, notes);
        });

    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.getDateInstance().format(date.getTime());

        Notes upNotes1 = new Notes();
        upNotes1.notesTitle = title;
        upNotes1.id = sid;
        upNotes1.notesSubtitle = subtitle;
        upNotes1.notes = notes;
        upNotes1.notesPriority = priority;
        upNotes1.notesDate = sequence.toString();

        notesViewModel.updateNote(upNotes1);

        Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_delete) {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this);

            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_bottom_sheet, (LinearLayout) findViewById(R.id.bottomSheet));

            sheetDialog.setContentView(view);
            TextView yes , no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v ->{

                notesViewModel.deleteNote(sid);
                finish();

            });

            no.setOnClickListener(v ->{
                sheetDialog.dismiss();
            });
            sheetDialog.show();
        }

        return true;
    }
}

