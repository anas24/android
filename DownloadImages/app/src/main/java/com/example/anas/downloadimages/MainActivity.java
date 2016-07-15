package com.example.anas.downloadimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    ImageView img;

    //
    public class Downloader extends AsyncTask<String,Void,Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings)
        {
            String r=strings[0];
            URL url;
            try
            {
                url = new URL(r);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is=urlConnection.getInputStream();
                Bitmap bmp= BitmapFactory.decodeStream(is);
                return bmp;
            }
            catch (Exception e)
            {
                System.out.println("error1 ");
                e.printStackTrace();
            }

            return  null ;
        }
    }
    public void pressed(View view)
    {
        Downloader task=new Downloader();
        Bitmap bmp=null;
        try
        {
            bmp = (Bitmap) task.execute("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png").get();
        }
        catch (Exception e)
        {
            System.out.println("error2");
            e.printStackTrace();
        }
        img.setImageBitmap(bmp);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img =(ImageView)findViewById(R.id.imageView);
    }
}
