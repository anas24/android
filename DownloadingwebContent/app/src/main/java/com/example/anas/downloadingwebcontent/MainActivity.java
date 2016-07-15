package com.example.anas.downloadingwebcontent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            Log.i("url:",strings[0]);
            String r=strings[0];
            URL url;
            char ch;
            String result="";
            HttpURLConnection urlconnection;
            try
            {
                url = new URL(r);
                urlconnection = (HttpURLConnection) url.openConnection();
                InputStream in  = urlconnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1)
                {
                    ch=(char) data;
                    result+=ch;
                    data=reader.read();
                }

            }
            catch (Exception e)
            {

             e.printStackTrace();
            }



            return result;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task= new DownloadTask();
        String result="";
        try
        {
            result=task.execute("http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=40435882c75c95e81093a5f7b8579db2").get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        Log.i("result:",result);
    }
}
