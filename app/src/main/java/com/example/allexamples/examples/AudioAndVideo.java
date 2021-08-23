package com.example.allexamples.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.allexamples.R;

import java.util.Timer;
import java.util.TimerTask;

public class AudioAndVideo extends AppCompatActivity {

    MediaPlayer mySound;
    AudioManager audioManager;

    public void btnPlay(View view){
        mySound.start();
    }

    public void btnStop(View view){
        mySound.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_and_video);
       /* VideoView marioVideo = (VideoView) findViewById(R.id.mojeVideo);
        marioVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mario);
        MediaController myMediaController = new MediaController(this);
        myMediaController.setAnchorView(marioVideo);
        marioVideo.setMediaController(myMediaController);
        marioVideo.start();*/

        mySound = MediaPlayer.create(this, R.raw.hello);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar posunovac = (SeekBar) findViewById(R.id.seekBar);
        posunovac.setMax(maxVolume);
        posunovac.setProgress(currentVolume);

        posunovac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Nastaveni Hlasitosti: ", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar setAudioProgress = (SeekBar) findViewById(R.id.seekBar2);
        setAudioProgress.setMax(mySound.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setAudioProgress.setProgress(mySound.getCurrentPosition());
            }
        },0, 100);

        setAudioProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Progress", Integer.toString(progress));
                mySound.seekTo(progress);
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