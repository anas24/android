package com.example.anas.showingandhiding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    public void press(View view)
    {
        if(view.getId()==R.id.show)
        {
            txt.setVisibility(View.VISIBLE);

        }
        else if(view.getId()==R.id.hide)
        {
            txt.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=(TextView)findViewById(R.id.textView);
    }
}
