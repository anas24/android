package com.example.anas.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;


public class Main2Activity extends AppCompatActivity implements TextWatcher {

    int index;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text= (EditText) findViewById(R.id.editText);
        Intent in = getIntent();
        String initial=in.getStringExtra("text");
        index=in.getIntExtra("index",-1);
        text.setText(initial);
        text.addTextChangedListener(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                switch (item.getItemId())
                {
                    case android.R.id.home:
                        this.finish();
                        return true;
                    default:

                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {


    }

    @Override
    public void afterTextChanged(Editable editable) {


    }
    public void save(View view)
    {
        if(index==-1)
        {
            MainActivity.notesdata.add(MainActivity.notesdata.size(),text.getText().toString());
        }

        else {
            MainActivity.notesdata.set(index,text.getText().toString());
        }
        MainActivity.arrayAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"SAVED!!!",Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences =this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        //Log.i("set:",MainActivity.set.toString());
//
        if(MainActivity.set==null)
        {
            MainActivity.set=new HashSet<String>();
        }
        else
        {
            MainActivity.set.clear();
        }

        MainActivity.set.addAll(MainActivity.notesdata);
        sharedPreferences.edit().putStringSet("notes",MainActivity.set).apply();
        this.finish();
    }
}
