package com.example.anas.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText uname,pass;
    public void Clickable(View view)
    {
        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.password);
        Toast.makeText(getApplicationContext(),"hello "+uname.getText().toString(),Toast.LENGTH_LONG).show();
        Log.i("Username",uname.getText().toString());
        Log.i("Password",pass.getText().toString());

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
