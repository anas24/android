package com.example.anas.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static ArrayAdapter arrayAdapter;
    static ArrayList<String>notesdata  =new ArrayList<>();
     static Set<String> set ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView notesListView =(ListView)findViewById(R.id.listView);
        notesdata.clear();
        SharedPreferences sharedPreferences =this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        set =sharedPreferences.getStringSet("notes",null);
        if(set==null)
        {
            set=new HashSet<String>();
            notesdata.add("sample");
            set.addAll(notesdata);

        }
        else
        {
            notesdata.addAll(set);
        }
        sharedPreferences.edit().remove("notes").apply();
        sharedPreferences.edit().putStringSet("notes",set).apply();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notesdata);
        notesListView.setAdapter(arrayAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in =new Intent(getApplicationContext(),Main2Activity.class);
                in.putExtra("index",i);
                in.putExtra("text",notesdata.get(i));
                startActivity(in);

            }
        });
        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int location, long l)
            {
                new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this,R.style.myDialog))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete it?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                notesdata.remove(location);
                                SharedPreferences sharedPreferences =getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                                set =sharedPreferences.getStringSet("notes",null);
                                if(set==null)
                                {
                                    set=new HashSet<String>();


                                }
                                else
                                {
                                    set.clear();

                                }
                                set.addAll(notesdata);
                                sharedPreferences.edit().remove("notes").apply();
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                                arrayAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
                return true;
            }
        });
        //arrayAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addnew)
        {
            Intent in = new Intent(this,Main2Activity.class);
            in.putExtra("text","");
            in.putExtra("index",-1);
            startActivity(in);
            arrayAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
