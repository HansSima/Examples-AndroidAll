package com.example.allexamples.apps.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allexamples.MyRecyclerViewAdapter;
import com.example.allexamples.examples.ObjectSerializer;
import com.example.allexamples.R;

import java.io.IOException;
import java.util.ArrayList;

public class MemorablePlacesActivity extends AppCompatActivity implements com.example.allexamples.MyRecyclerViewAdapter.ItemClickListener {

    RecyclerView myPlaces;
    ArrayList<String> myPlacesList = new ArrayList<>();
    com.example.allexamples.MyRecyclerViewAdapter adapter;
    SharedPreferences sharedPreferences;
    ArrayList<String> listLatLng = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);

        try {
            listLatLng = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("LatLng", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myPlaces = findViewById(R.id.placesRecView);
        myPlaces.setLayoutManager(new LinearLayoutManager(this));


        if (listLatLng.isEmpty()){
            listLatLng.add("Zadej prvni pozici");
        }

        adapter = new MyRecyclerViewAdapter(this, listLatLng);
        adapter.setClickListener(this);
        myPlaces.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        adapter.notifyItemInserted(listLatLng.size());
        changeToMapActivity(position);
    }

    

    public void changeToMapActivity(int position){
        Intent changeIntent = new Intent(getApplicationContext(), MapsActivity.class);
        changeIntent.putExtra("positionOfCoordinates", position);
        startActivity(changeIntent);
    }


}