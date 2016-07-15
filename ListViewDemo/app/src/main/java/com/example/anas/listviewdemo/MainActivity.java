package com.example.anas.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView friends =(ListView)findViewById(R.id.listView);
        final ArrayList<String> myfriends =new ArrayList<String>();
        myfriends.add("fatty");
        myfriends.add("saba");
        myfriends.add("rijul");
        myfriends.add("aquil");
        myfriends.add("amir");
        myfriends.add("gagan");
        myfriends.add("shoeb");
        myfriends.add("fatty");
        myfriends.add("fatty");


        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myfriends);
        friends.setAdapter(adapter);
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),myfriends.get(i),Toast.LENGTH_LONG).show();
            }
        });

    }
}
