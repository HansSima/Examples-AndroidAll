package com.example.allexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allexamples.apps.BrainTrainerApp;
import com.example.allexamples.examples.AudioAndVideo;
import com.example.allexamples.examples.GetInternetResource;
import com.example.allexamples.examples.JSONDemo;

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
        AllExamplesActivityArrayList.add("Get Internet Resource");
        AllExamplesActivityArrayList.add("Audio/Video");
        AllExamplesActivityArrayList.add("JSON Demo");
        AllExamplesActivityArrayList.add("Brain Trainer App");
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
        Intent changeIntent;

        switch (adapterPosition){
            case 0:
                changeIntent = new Intent(getApplicationContext(), GetInternetResource.class);
                startActivity(changeIntent);
                break;
            case 1:
                changeIntent = new Intent(getApplicationContext(), AudioAndVideo.class);
                startActivity(changeIntent);
                break;
            case 2:
                changeIntent = new Intent(getApplicationContext(), JSONDemo.class);
                startActivity(changeIntent);
                break;
            case 3:
                changeIntent = new Intent(getApplicationContext(), BrainTrainerApp.class);
                startActivity(changeIntent);
                break;
            default:
                break;
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