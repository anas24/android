package com.example.anas.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar timeseekbar;
    TextView timetext;
    Boolean IsCounterActive=false;
    Button go;
    CountDownTimer ct;
    public void reset()
    {
        IsCounterActive=false;
        go.setText("Go!");
        timetext.setText("0:30");
        timeseekbar.setEnabled(true);
        timeseekbar.setProgress(30);
        ct.cancel();
    }
    public void update(int i)
    {
        int minutes=(int)i/60;
        int seconds=i-minutes*60;
        String second=Integer.toString(seconds);

        if(seconds<=9)
        {

            second="0"+second;

        }
        timetext.setText(Integer.toString(minutes)+":"+second);
    }
    public void Controller(View view)
    {

        if (IsCounterActive == false)
        {
            IsCounterActive=true;
            go.setText("Stop");
            timeseekbar.setEnabled(false);
            ct = new CountDownTimer(timeseekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l)
                {
                    update((int) l / 1000);

                }

                @Override
                public void onFinish()
                {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    mp.start();
                    update(0);
                    reset();

                }
            }.start();
        }
        else if(IsCounterActive == true)
        {
           reset();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeseekbar = (SeekBar) findViewById(R.id.seekBar);
        timetext = (TextView) findViewById(R.id.timertextview);
        go=(Button)findViewById(R.id.button);
        timeseekbar.setProgress(30);
        timeseekbar.setMax(600);
        timeseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                update(i);
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
