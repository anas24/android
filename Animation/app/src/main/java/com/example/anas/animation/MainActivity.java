package com.example.anas.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView homer;
    public void fade(View view)
    {
        ImageView bart=(ImageView) findViewById(R.id.bart);
//        homer=(ImageView) findViewById(R.id.homer);
//        homer.animate().alpha(1f).setDuration(2000);
        bart.animate().rotationBy(360f).scaleX(0.5f).scaleY(0.5f).setDuration(2000);
//        bart.animate().rotationBy(360f).scaleX(2f).scaleY(2f).setDuration(2000);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bart=(ImageView) findViewById(R.id.bart);
//        bart.setTranslationX(-1000f);
    }
}
