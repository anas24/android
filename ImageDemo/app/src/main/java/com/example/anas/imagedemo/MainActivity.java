package com.example.anas.imagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int sign=-1;
    ImageView dot1,dot2;
    public void Clickable(View view)
    {
     if (sign==-1)
     {
         dot1.setImageResource(R.drawable.dot2);
         sign=sign*(-1);
     }
    else
     {
         dot1.setImageResource(R.drawable.dot1);
         sign=sign*(-1);
     }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot1=(ImageView)findViewById(R.id.imageView1);
        dot1.setImageResource(R.drawable.dot1);
    }
}
