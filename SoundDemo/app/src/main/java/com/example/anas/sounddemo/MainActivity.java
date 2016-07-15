package com.example.anas.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    SeekBar volumeController,location;
    AudioManager audios;
    public void start(View view)
    {
        mp=MediaPlayer.create(this,R.raw.sound);
        mp.start();
    }
    public  void pause(View view)
    {
        mp.pause();
    }
    public void resume(View view)
    {
        mp.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volumeController =(SeekBar)findViewById(R.id.volume);
        location=(SeekBar) findViewById(R.id.location);
        mp=MediaPlayer.create(this,R.raw.sound);
        int duration=mp.getDuration();
        int currentLocation=mp.getCurrentPosition();
        audios=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume=audios.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume=audios.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeController.setMax(maxVolume);
        volumeController.setProgress(currVolume);
        location.setMax(duration);
        location.setProgress(currentLocation);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                location.setProgress(mp.getCurrentPosition());
            }
        },0,100);//update seek bar's current position every 10th of a second
        location.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mp.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                Log.i("Message",Integer.toString(i));
                audios.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
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
