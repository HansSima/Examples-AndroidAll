package com.example.allexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allexamples.apps.EggTimerApp;
import com.example.allexamples.apps.notes.NoteChangeActivity;
import com.example.allexamples.examples.AudioAndVideoExample;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView myRecyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<String> AllExamplesActivityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullListOfExamples();
        changeExampleRecycleView();
    }

    private void fullListOfExamples(){
        AllExamplesActivityArrayList = new ArrayList<>();

        //All Apps
        AllExamplesActivityArrayList.add("Brain Trainer App");
        AllExamplesActivityArrayList.add("Egg Timer App");
        AllExamplesActivityArrayList.add("Hiker Watch App");
        AllExamplesActivityArrayList.add("Memorable Places App");
        AllExamplesActivityArrayList.add("Notes App");
        AllExamplesActivityArrayList.add("Tic Tac Toe App");
        AllExamplesActivityArrayList.add("Time Table App");
        AllExamplesActivityArrayList.add("Guess Celebrity App");

        //All examples
        AllExamplesActivityArrayList.add("Alert Dialog Example");
        AllExamplesActivityArrayList.add("Another Maps Example");
        AllExamplesActivityArrayList.add("Audio And Video Example");
        AllExamplesActivityArrayList.add("File Reader Example");
        AllExamplesActivityArrayList.add("Get Internet Resource Example");
        AllExamplesActivityArrayList.add("Grid Example");
        AllExamplesActivityArrayList.add("JSON Example");
        AllExamplesActivityArrayList.add("Layout Transformation Example");
        AllExamplesActivityArrayList.add("Life Cycle Example");
        AllExamplesActivityArrayList.add("Maps Example");
        AllExamplesActivityArrayList.add("Shared Preferences Example");
        AllExamplesActivityArrayList.add("Sqlite Example");
        AllExamplesActivityArrayList.add("Timers Example");

    }

    private void changeExampleRecycleView(){
        myRecyclerView = findViewById(R.id.simpleExamples);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, AllExamplesActivityArrayList);
        adapter.setClickListener(this);
        adapter.setClickLongListener(this);
        myRecyclerView.setAdapter(adapter);
    }

    private void changeActivity(int adapterPosition){

        String className = AllExamplesActivityArrayList.get(adapterPosition).replaceAll("\\s", "");
        String path = "com.example.allexamples.examples.";

        if(className.contains("App")){
            path = "com.example.allexamples.apps.";
            if(className.contains("Memorable")){
                path = path + "memorableplaces.";
            }
            if(className.contains("Notes")){
                path = path + "notes.";
            }
        }

        String classNameWithPath = path + className;
        try {
            Class classToChange = Class.forName(classNameWithPath);
            Intent changeIntent = new Intent(getApplicationContext(), classToChange);
            startActivity(changeIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(View v, int adapterPosition) {
        changeActivity(adapterPosition);
    }

    @Override
    public void onLongItemClick(View v, int adapterPosition) {

    }
}