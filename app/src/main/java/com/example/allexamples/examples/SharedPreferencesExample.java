package com.example.allexamples.examples;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

import java.io.IOException;
import java.util.ArrayList;

public class SharedPreferencesExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Jenda");
        friends.add("Hanis");

        try {
            String serializace = ObjectSerializer.serialize(friends);
        Log.i("Serializace", serializace);
        sharedPreferences.edit().putString("friends", serializace).apply();
    } catch (IOException e) {
        e.printStackTrace();
    }

        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
            Log.i("Serializace", "Pomocna");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*sharedPreferences.edit().putString("Name", "Honza").apply();
        sharedPreferences.edit().putString("Name2", "Jenda").apply();
        String name = sharedPreferences.getString("Name", "Je Frajer");
        Log.i("Memory", name);*/


    }
}