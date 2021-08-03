package com.example.allexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    RecyclerView myRecyclerView;
    MyRecyclerViewAdapter adapter;
    ArrayList<String> AllExamplesActivityArrayList;

    RecyclerView myRecyclerView2;
    MyRecyclerViewAdapter adapter2;
    ArrayList<String> AllProjectActivityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullListOfExamples();
        changeExampleRecycleView();
        changeProjectRecycleView();
    }

    private void fullListOfExamples(){
        AllExamplesActivityArrayList = new ArrayList<>();
        AllExamplesActivityArrayList.add("Get Internet Resource");
        AllExamplesActivityArrayList.add("Audio/Video");
        AllExamplesActivityArrayList.add("Call API");
    }

    private void changeExampleRecycleView(){
        myRecyclerView = findViewById(R.id.simpleExamples);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, AllExamplesActivityArrayList);
        adapter.setClickListener(this);
        adapter.setClickLongListener(this);
        myRecyclerView.setAdapter(adapter);
    }

    private void changeProjectRecycleView(){
        myRecyclerView2 = findViewById(R.id.projectList);
        myRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new MyRecyclerViewAdapter(this, AllExamplesActivityArrayList);
        adapter2.setClickListener(this);
        adapter2.setClickLongListener(this);
        myRecyclerView2.setAdapter(adapter);
    }

    private void changeActivity(int adapterPosition, boolean isExample){
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
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View v, int adapterPosition) {
        changeActivity(adapterPosition, true);
    }

    @Override
    public void onLongItemClick(View v, int adapterPosition) {

    }
}