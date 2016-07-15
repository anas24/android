package com.example.anas.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent in =getIntent();
        Toast.makeText(getApplicationContext(),in.getStringExtra("name"),Toast.LENGTH_LONG).show();
    }
}
