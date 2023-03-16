package com.example.notes.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Activity.UpdateNotesActivity;
import com.example.notes.MainActivity;
import com.example.notes.Model.Notes;
import com.example.notes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @Override
    public notesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(notesViewholder holder, int position) {

        Notes note;
        note = notes.get(position);

        if ("1".equals(note.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if ("2".equals(note.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if ("3".equals(note.notesPriority)) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }
        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class  );

            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("sutitle",note.notesSubtitle);
            intent.putExtra("priority",note.notesPriority);
            intent.putExtra("note",note.notes);


            mainActivity.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewholder extends RecyclerView.ViewHolder {


        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewholder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesData);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
