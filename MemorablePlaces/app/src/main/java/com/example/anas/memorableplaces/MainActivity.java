package com.example.anas.memorableplaces;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> memorableArrayList;
    static ArrayAdapter<String> adapter;
    static int flag=0;
    static ArrayList<LatLng> places;
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ListView memorbleplaces = (ListView) findViewById(R.id.listView);

            memorableArrayList = new ArrayList<>();
            memorableArrayList.add("Add a new place..");
            places=new ArrayList<>();
            places.add(new LatLng(0,0));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, memorableArrayList);
            memorbleplaces.setAdapter(adapter);
            flag =1;
            memorbleplaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(getApplicationContext(), MapsActivity.class);
                    in.putExtra("Placeinfo", i);
                    startActivity(in);

                }
            });
        memorbleplaces.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l)
            {
                if(i==0)
                    Toast.makeText(getApplicationContext(),"Bhaai ye nhi!!",Toast.LENGTH_LONG).show();
                else {
                    new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete this..")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int p) {

                                    memorableArrayList.remove(i);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
                return true;
            }
        });

        }

}
