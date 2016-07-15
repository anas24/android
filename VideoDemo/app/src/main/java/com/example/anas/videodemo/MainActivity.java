package com.example.anas.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView video =(VideoView) findViewById(R.id.videoView);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);
        MediaController mediaC =new MediaController(this);
        mediaC.setAnchorView(video);
        video.setMediaController(mediaC);

    }
}
