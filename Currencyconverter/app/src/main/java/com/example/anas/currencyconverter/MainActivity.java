package com.example.anas.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    double rupee,dollar;
    public void Clickable(View view)
    {
        et1=(EditText)findViewById(R.id.editText);

        dollar=Double.parseDouble(et1.getText().toString());
        rupee=dollar*60.0;
        Toast.makeText(getApplicationContext(),"rupees "+Double.toString(rupee),Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
