package com.example.anas.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    int activePlayer=1;
    int tag;
    int[]firstPlayer=new int[10];
    int[]secondPlayer=new int[10];
    int [] gameState={3,3,3,3,3,3,3,3,3};
    boolean isGameOver=false;
    boolean isRemaining=false;
    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;
        tag=(Integer.parseInt(counter.getTag().toString()));
        System.out.println(tag);
        if(gameState[tag]==3 && !isGameOver)
        {
            counter.setTranslationY(-1000f);
            if (activePlayer == 1)
            {
                counter.setImageResource(R.drawable.yellow);
                firstPlayer[tag] = 1;
                gameState[tag]=activePlayer;
                activePlayer = 2;

            } else if (activePlayer == 2)
            {
                counter.setImageResource(R.drawable.red);
                secondPlayer[tag] = 1;
                gameState[tag]=activePlayer;
                activePlayer = 1;
            }

            counter.animate().translationYBy(1000f).setDuration(1000);
            if ((activePlayer == 2) && ((firstPlayer[0] == 1 && firstPlayer[1] == 1 && firstPlayer[2] == 1) || (firstPlayer[0] == 1 && firstPlayer[3] == 1 && firstPlayer[6] == 1) || (firstPlayer[1] == 1 && firstPlayer[4] == 1 && firstPlayer[7] == 1) || (firstPlayer[2] == 1 && firstPlayer[5] == 1 && firstPlayer[8] == 1) || (firstPlayer[3] == 1 && firstPlayer[4] == 1 && firstPlayer[5] == 1) || (firstPlayer[6] == 1 && firstPlayer[7] == 1 && firstPlayer[8] == 1) || (firstPlayer[0] == 1 && firstPlayer[4] == 1 && firstPlayer[8] == 1) || (firstPlayer[2] == 1 && firstPlayer[4] == 1 && firstPlayer[6] == 1)))
            {
                TextView winnersText =(TextView) findViewById(R.id.winnersText);
                winnersText.setText("Yellow won");
                LinearLayout layout=(LinearLayout) findViewById(R.id.winners);
                layout.setVisibility(View.VISIBLE);
                isGameOver=true;

            } else if ((activePlayer == 1) && ((secondPlayer[0] == 1 && secondPlayer[1] == 1 && secondPlayer[2] == 1) || (secondPlayer[0] == 1 && secondPlayer[3] == 1 && secondPlayer[6] == 1) || (secondPlayer[1] == 1 && secondPlayer[4] == 1 && secondPlayer[7] == 1) || (secondPlayer[2] == 1 && secondPlayer[5] == 1 && secondPlayer[8] == 1) || (secondPlayer[3] == 1 && secondPlayer[4] == 1 && secondPlayer[5] == 1) || (secondPlayer[6] == 1 && secondPlayer[7] == 1 && secondPlayer[8] == 1) || (secondPlayer[0] == 1 && secondPlayer[4] == 1 && secondPlayer[8] == 1) || (secondPlayer[2] == 1 && secondPlayer[4] == 1 && secondPlayer[6] == 1)))
            {

                TextView winnersText =(TextView) findViewById(R.id.winnersText);
                winnersText.setText("Red won");
                LinearLayout layout=(LinearLayout) findViewById(R.id.winners);
                layout.setVisibility(View.VISIBLE);
                isGameOver=true;
            }
            else {

                for(int i=0;i<9;i++)
                {
                    if (gameState[i]==3)
                    {
                        isRemaining=true;
                    }
                }
                }
            if(!isRemaining)
            {
                LinearLayout layout=(LinearLayout) findViewById(R.id.winners);
                layout.setVisibility(View.VISIBLE);
                isGameOver=true;
            }
        }

    }
    public void playAgain(View view)
    {
        System.out.println("Button tapped");
        for(int i=0;i<9;i++)
        {
            firstPlayer[i]=0;
            secondPlayer[i]=0;
            gameState[i]=3;
        }
        isGameOver=false;
        GridLayout grid =(GridLayout) findViewById(R.id.grid);
        int count=grid.getChildCount();
        for(int i=0;i<count;i++)
        {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }
        LinearLayout layout=(LinearLayout) findViewById(R.id.winners);
        layout.setVisibility(View.INVISIBLE);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<10;i++)
        {
            firstPlayer[i]=0;
            secondPlayer[i]=0;
        }
    }
}
