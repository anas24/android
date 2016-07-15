package com.example.anas.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int loctionOfCorrectAnswer;
    public void chooseAnswer(View view)
    {
        if((int)(view.getTag())==loctionOfCorrectAnswer)
        {
            
        }
    }
    public void start(View view)
    {
        start.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=(Button)findViewById(R.id.StartButton);
        Button button0=(Button)findViewById(R.id.button0);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        TextView question=(TextView)findViewById(R.id.question);
        Random r=new Random();

        int a=r.nextInt(21);
        int b=r.nextInt(21);
        question.setText(Integer.toString(a)+"+"+Integer.toString(b));
        loctionOfCorrectAnswer=r.nextInt(3);
        for(int i=0;i<=3;i++)
        {
            if(i==loctionOfCorrectAnswer)
                answers.add(a+b);
            else
            {
                int incorrectanswer=r.nextInt(41);
                while(incorrectanswer==a+b)
                {
                    incorrectanswer=r.nextInt(41);
                }
                answers.add(incorrectanswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
}
