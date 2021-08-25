package com.example.allexamples.examples;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class LifeCycleActivity extends AppCompatActivity {

    final String TAG_LIFECYCLE = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ...

        Log.d(TAG_LIFECYCLE, "onCreate()");

        // ...
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG_LIFECYCLE, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFECYCLE, "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "onDestroy()");
    }
}