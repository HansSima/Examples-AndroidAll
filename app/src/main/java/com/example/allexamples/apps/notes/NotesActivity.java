package com.example.allexamples.apps.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allexamples.R;

import java.io.IOException;
import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView myRecyclerView;
    MyNotesRecyclerViewAdapter adapter;
    ArrayList<String> notesArrayList;
    ArrayList<String> shortNotesArrayList;
    SharedPreferences sharedPreferences;
    String serActualNotes;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.toString()){
            case "Add note":
                addNote();
                break;
            case "Delete all notes":
                notesArrayList.removeAll(notesArrayList);
                changeRecycleView();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        serActualNotes = sharedPreferences.getString("Notes", "");


        notesArrayList = new ArrayList<>();
        if (serActualNotes.isEmpty()){
            notesArrayList.add("Add note");
        }

        else{
                try {
                    notesArrayList = (ArrayList<String>) ObjectSerializer.deserialize(serActualNotes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        myRecyclerView = findViewById(R.id.myRecycleView);
        changeRecycleView();
    }

    @Override
    public void onItemClick(View v, int adapterPosition) {
        changeActivity(adapterPosition);
    }

    @Override
    public void onLongItemClick(View v, int adapterPosition) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning!!!")
                .setMessage("Want you delete note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notesArrayList.remove(adapterPosition);
                        serializeNotes();
                        changeRecycleView();
                    }
                })
                .setNegativeButton("Not Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void deleteNotes(View view){
        notesArrayList.removeAll(notesArrayList);
        changeRecycleView();
    }

    private void changeRecycleView(){
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shorterArrayListNotes();
        adapter = new MyNotesRecyclerViewAdapter(this, shortNotesArrayList);
        adapter.setClickListener(this);
        adapter.setClickLongListener(this);
        myRecyclerView.setAdapter(adapter);
    }

    private void shorterArrayListNotes(){
        shortNotesArrayList = new ArrayList<String>(notesArrayList);
        for (int i = 0; i < notesArrayList.size(); i++)
        {
            if(notesArrayList.get(i).length() > 20){
                String newShortNote = notesArrayList.get(i).substring(0,20) + "...";
                shortNotesArrayList.set( i, newShortNote );
            }
        }
    }

    private void addNote(){
        notesArrayList.add("New note");
        changeRecycleView();

    }

    private void serializeNotes(){
        try {
            String serActualNotes = ObjectSerializer.serialize(notesArrayList);
            sharedPreferences.edit().putString("Notes", serActualNotes).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeActivity(int adapterPosition){
        serializeNotes();
        Intent changeIntent = new Intent(getApplicationContext(), NoteChangeActivity.class);
        changeIntent.putExtra("Position", adapterPosition);
        startActivity(changeIntent);
    }

}