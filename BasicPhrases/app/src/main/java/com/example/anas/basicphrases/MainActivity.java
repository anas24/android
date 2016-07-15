package com.example.anas.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void speak(View view)
    {


        if(view.getId()==R.id.hello)
        {
            System.out.println("Hello");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.hello);
            mp.start();
        }
        if(view.getId()==R.id.speakenglish)
        {
            System.out.println("Do you speak English");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.doyouspeakenglish);
            mp.start();

        }
        if(view.getId()==R.id.GoodEve)
        {
            System.out.println("Good Eve");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.goodevening);
            mp.start();
        }
        if(view.getId()==R.id.howareyou)
        {
            System.out.println("How are");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.howareyou);
            mp.start();
        }
        if(view.getId()==R.id.ILive)
        {
            System.out.println("I Live");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.ilivein);
            mp.start();
        }
        if(view.getId()==R.id.myname)
        {
            System.out.println("myname");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.mynameis);
            mp.start();
        }
        if(view.getId()==R.id.please)
        {
            System.out.println("pleaase");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.please);
            mp.start();
        }
        if(view.getId()==R.id.welcome)
        {
            System.out.println("Welcome");
            MediaPlayer mp  =MediaPlayer.create(this,R.raw.welcome);
            mp.start();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
