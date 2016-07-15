package com.example.anas.timers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountDownTimer ct=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                Log.i("Seconds Passed:",String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                Log.i("khatm","Ho gaya");
            }
        }.start();
//        final Handler hand = new Handler();
//        Runnable r =new Runnable() {
//            @Override
//            public void run() {
//                Log.i("Seconds passed:","One Second Passed");
//                hand.postDelayed(this,1000);
//
//            }
//        };
//        hand.post(r);
    }
}
