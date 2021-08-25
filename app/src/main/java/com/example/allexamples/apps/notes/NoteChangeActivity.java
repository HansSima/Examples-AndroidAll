package com.example.allexamples.apps.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.MainActivity;
import com.example.allexamples.R;

import java.io.IOException;
import java.util.ArrayList;

public class NoteChangeActivity extends AppCompatActivity {

    int adapterPosition;
    ArrayList<String> notesArrayList;
    SharedPreferences sharedPreferences;
    String serActualNotes;
    EditText notesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_change);

        Intent notesFromMainActivity = getIntent();
        adapterPosition = notesFromMainActivity.getIntExtra("Position", 0);

        notesText = findViewById(R.id.editTextTextMultiLine);

        notesArrayList = new ArrayList<>();

        sharedPreferences = this.getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        serActualNotes = sharedPreferences.getString("Notes", "");

        try {
            notesArrayList = (ArrayList<String>) ObjectSerializer.deserialize(serActualNotes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        notesText.setText(notesArrayList.get(adapterPosition));
    }

    public void savingNote(View view){
        notesArrayList.set(adapterPosition, notesText.getText().toString());
        try {
            String serActualNotes = ObjectSerializer.serialize(notesArrayList);
            sharedPreferences.edit().putString("Notes", serActualNotes).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent changeIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(changeIntent);
    }

}