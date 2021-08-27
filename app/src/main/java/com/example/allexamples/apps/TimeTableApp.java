package com.example.allexamples.apps;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

import java.util.ArrayList;


public class TimeTableApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        ListView timeTableList = (ListView)findViewById(R.id.TimeTableList);
        SeekBar mySeekBar = (SeekBar)findViewById(R.id.mySeekBar);
        mySeekBar.setMax(20);
        mySeekBar.setProgress(10);


        ArrayList<Integer> myTimeTableArrayList = new ArrayList<Integer> ();

        for (int i = 0; i < 10; i++) {
            myTimeTableArrayList.add(i);
        }

        ArrayAdapter<Integer> myArrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, myTimeTableArrayList);


        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar mySeekBar, int progress, boolean fromUser) {

                progress++;
                for (int i = 0; i < 10; i++) {
                    myTimeTableArrayList.set(i, (i+1)*progress);
                }
                timeTableList.setAdapter(myArrayAdapter);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }
}