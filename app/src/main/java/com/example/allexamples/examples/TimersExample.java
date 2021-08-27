package com.example.allexamples.examples;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

public class TimersExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long current1 = System.currentTimeMillis();
       int neco = 0;
        for(int x = 0; x<10000000; x++){
            neco++;
        }

        //Countdown timer
        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Second Left", String.valueOf(millisUntilFinished/1000));
                long currnt3 = System.currentTimeMillis() - current1;
                Log.i("Doba trvani programu1:", String.valueOf(currnt3));
            }

            @Override
            public void onFinish() {
                Log.i("Finish", "Countdown finish ");
            }
        }.start();

        long currnt2 = System.currentTimeMillis() - current1;
        Log.i("Doba trvani programu2:", String.valueOf(currnt2));
        //Timer every 1second
        /*Handler mainHandler = new Handler(Looper.getMainLooper());
        Runnable run = new Runnable() {
            @Override
            public void run() {
                mainHandler.postDelayed(this, 1000);
                Log.i("Timer:", "run: Program running");
            }
        };
        mainHandler.post(run);*/


        //Executor myExecutor = ContextCompat.getMainExecutor(this);
        /*myExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("Cas", "Casovac jede ");
                myExecutor.
            }
        });*/


    }
}