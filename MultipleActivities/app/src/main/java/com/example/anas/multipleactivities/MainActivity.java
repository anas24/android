package com.example.anas.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView friends = (ListView)findViewById(R.id.listView);
        final ArrayList<String>  friendsList = new ArrayList<>();
        friendsList.add("anas");
        friendsList.add("owais");
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friendsList);
        friends.setAdapter(adapter);
        friends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in =new Intent(getApplicationContext(),Second.class);
                in.putExtra("name",friendsList.get(i));
                startActivity(in);
            }
        });
    }

}
