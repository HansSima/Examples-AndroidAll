package com.example.allexamples.apps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allexamples.R;

import java.util.concurrent.TimeUnit;

public class EggTimer extends AppCompatActivity {
    /*
    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer() {

        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;

    }

    public void updateTimer(int secondsLeft) {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {

            secondString = "0" + secondString;

        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);

    }


    public void controlTimer(View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.hotovo);
                    mplayer.start();

                }
            }.start();

        } else {

            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar)findViewById(R.id.seekBarTimer);
        timerTextView = (TextView)findViewById(R.id.editTextTime);
        controllerButton = (Button)findViewById(R.id.btnStart);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    */

    CountDownTimer myTimer;
    SeekBar myTimerSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTimerSeekBar = (SeekBar) findViewById(R.id.seekBarTimer);
        myTimerSeekBar.setMax(10);
        myTimerSeekBar.setProgress(5);
        TextView editTextTime = findViewById(R.id.editTextTime);
        //editTextTime.setText("05:00");
        MediaPlayer mySound = MediaPlayer.create(this, R.raw.hotovo);

        myTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int time = 0;
                time = progress * 60 * 1000;

                myTimer = new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String hms = String.format("%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                        editTextTime.setText(hms);
                    }

                    @Override
                    public void onFinish() {
                        mySound.start();
                        //myTimerSeekBar.setEnabled(true);
                    }
                }.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myTimerSeekBar.setEnabled(false);
            }
        });

    }

    public void btnGoPressed(View view) {
        restartApp(myTimer);
    }

    private void restartApp(CountDownTimer myTimer){
        myTimerSeekBar = findViewById(R.id.seekBarTimer);
        myTimer.cancel();
        myTimerSeekBar.setEnabled(true);
        myTimerSeekBar.setMax(10);
        myTimerSeekBar.setProgress(5);
        TextView editTextTime = findViewById(R.id.editTextTime);
        editTextTime.setText("05:00");

    }

}
