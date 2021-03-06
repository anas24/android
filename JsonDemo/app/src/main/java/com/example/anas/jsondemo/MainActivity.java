package com.example.anas.jsondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class Downloader extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            String result="";
            try
            {
                URL url = new URL(strings[0]);

                HttpURLConnection connection =(HttpURLConnection) url.openConnection();
                InputStream is =connection.getInputStream();

                InputStreamReader reader =new InputStreamReader(is);

                int data=reader.read();
                while(data!=-1)
                {
                    char ch=(char)data;
                    result+=ch;
                    data=reader.read();

                }
                 return  result;


            }
            catch (Exception e)
            {

                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
                try {

                    JSONObject jsonObject   = new JSONObject(result);
                     String whetherinfo=jsonObject.getString("");
                    Log.i("whether info:",whetherinfo);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            Log.i("Weather",result);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Downloader task=new Downloader();
        task.execute("http://api.openweathermap.org/data/2.5/forecast/city?id=524901&APPID=40435882c75c95e81093a5f7b8579db2");
    }
}
