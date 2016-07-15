package com.example.anas.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String[] imagesurls =new String [1000] ;
    String[] names = new String[1000];
    ImageView img;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    int locationOfAnswer=0;
    int k;
    public class Getter extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... strings)
        {
            String urlString=strings[0];
            URL url;
            String result="";
            try
            {
                url=new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream stream =  connection.getInputStream();
                InputStreamReader reader =new InputStreamReader(stream);
                int data=reader.read();
                while(data!=-1)
                {
                    char ch=(char)data;
                    result=result+ch;
                    data=reader.read();

                }
                return  result;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return  null;
            }




        }
    }
    public class getImages extends AsyncTask<String,Void,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings)
        {
            String urls=strings[0];
            URL url;
            try
            {
                url=new URL(urls);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(is);
                return  bmp;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return  null;
            }


        }
    }

    public void Correction(View view)
    {
        System.out.println("Button tapped");
        if(Integer.valueOf((String) view.getTag()) == k)
            Toast.makeText(getApplicationContext(),"Correct!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Incorrect! it was "+names[locationOfAnswer],Toast.LENGTH_LONG).show();
        guess();
    }
    public void guess()
    {
            getImages gi =new getImages();
            try
            {
                Random r =new Random();
                locationOfAnswer=r.nextInt(100);
                Bitmap bmp=gi.execute(imagesurls[locationOfAnswer]).get();
                System.out.println("1");
                img.setImageBitmap(bmp);
                k=r.nextInt(4)+1;
                System.out.println("2");
                for(int i=1;i<=4;i++)
                {
                    if(i==k)
                    {
                        System.out.println("3");
                        if(Integer.valueOf((String) btn1.getTag())==k)
                            btn1.setText(names[locationOfAnswer]);
                        else if(Integer.valueOf((String) btn2.getTag())==k)
                            btn2.setText(names[locationOfAnswer]);
                        else if(Integer.valueOf((String) btn3.getTag())==k)
                            btn3.setText(names[locationOfAnswer]);
                        else
                            btn4.setText(names[locationOfAnswer]);
                        System.out.println("4");
                    }
                    else
                    {
                        int different;
                        different=r.nextInt(100);
                        System.out.println("5");
                        while(different==locationOfAnswer)
                        {
                            different=r.nextInt(100);
                        }
                        System.out.println("6");
//                        int i = Integer.valueOf((String) object);
                        if( Integer.valueOf((String) btn1.getTag())==i)
                            btn1.setText(names[different]);
                        else if( Integer.valueOf((String) btn2.getTag())==i)
                            btn2.setText(names[different]);
                        else if( Integer.valueOf((String) btn3.getTag())==i)
                            btn3.setText(names[different]);
                        else
                            btn4.setText(names[different]);
                        System.out.println("7");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("10");
                e.printStackTrace();
            }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageView);
        btn1=(Button)findViewById(R.id.option1);
        btn2=(Button)findViewById(R.id.option2);
        btn3=(Button)findViewById(R.id.option3);
        btn4=(Button)findViewById(R.id.option4);
        TextView tv=(TextView)findViewById(R.id.textView);
        tv.setText("Loading....");
        Getter g=new Getter();
        try
        {
            String result=g.execute("http://www.posh24.com/celebrities").get();
            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m =p.matcher(result);
            int i=0;
            while(m.find())
            {
                imagesurls[i]=m.group(1);
                i++;
            }
            System.out.println(Arrays.toString(imagesurls));
            p=Pattern.compile("alt=\"(.*?)\"");
            m=p.matcher(result);
            i=0;
            while(m.find())
            {
                names[i]=m.group(1);
                i++;
            }
            tv.setVisibility(View.INVISIBLE);
            guess();

        }
        catch(Exception e)
        {

            System.out.println("error occured");
            e.printStackTrace();
        }
    }
}
