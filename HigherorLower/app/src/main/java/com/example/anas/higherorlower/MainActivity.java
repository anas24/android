package com.example.anas.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int actual;
    EditText et;
    int number;
    String n;
    Random randomNumberGenerator;
    public void Clickable(View view)
    {
        n=et.getText().toString();
        if(n.equals(""))
            Toast.makeText(getApplicationContext(),"First guess the number!!",Toast.LENGTH_LONG).show();
        else
        {
            number = Integer.parseInt(et.getText().toString());
            System.out.println(actual);
            if (number > actual)
                Toast.makeText(getApplicationContext(), "Lower", Toast.LENGTH_LONG).show();
            else if (number < actual)
                Toast.makeText(getApplicationContext(), "Higher", Toast.LENGTH_LONG).show();
            else
            {
                Toast.makeText(getApplicationContext(), "Guessed it!!", Toast.LENGTH_LONG).show();
                actual=randomNumberGenerator.nextInt(20);
            }
            et.setText("");
            System.out.println(actual);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText) findViewById(R.id.editText);
        randomNumberGenerator=new Random();
        actual=randomNumberGenerator.nextInt(11)+10;

    }
}
