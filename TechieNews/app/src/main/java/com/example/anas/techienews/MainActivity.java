package com.example.anas.techienews;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    public class  gettingNewsList extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            URL url;

            try
            {
                char ch;
                String result="";
                url = new URL(strings[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is =connection.getInputStream();
                InputStreamReader reader=new InputStreamReader(is);
                int data=reader.read();
                while(data!=-1)
                {

                    data=reader.read();
                    ch=(char)data;
                    result+=ch;

                }
                return  result;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        SQLiteDatabase datas=this.openOrCreateDatabase("users", Context.MODE_PRIVATE,null);

        //table should contain all the data
        ListView newsList =(ListView)findViewById(R.id.listView);
        gettingNewsList newsitems=new gettingNewsList();
        ArrayList<String> newsArrayList=new ArrayList<String>();
        final ArrayList<String> urls=new ArrayList<>();
        ImageView download= (ImageView)findViewById(R.id.imageView2);

        int count=20;
        boolean isPresent=false;
        try {
            Cursor c = datas.rawQuery("SELECT name FROM users WHERE type='table' AND name='user'", null);
            if (c!=null) {
                    isPresent = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(!isPresent) {
            try
            {
                String result = newsitems.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty").get();
                String articleid = "";
                for (int i = 1; i < result.length() && count >= 0; i++)
                {

                    if (result.charAt(i) != ',')
                    {
                        articleid += result.charAt(i);
                    }
                    else
                    {
                        //Log.i("articleId",articleid.trim());
                        gettingNewsList newsss = new gettingNewsList();
                        String news = newsss.execute("https://hacker-news.firebaseio.com/v0/item/" + articleid + ".json?print=pretty").get();
                        JSONObject jsonobject = new JSONObject("{" + news);

                        String title = jsonobject.getString("title");
                        String url = jsonobject.getString("url");
                        datas.execSQL("create table if not exists user (title varchar(1000) ,url varchar(1000))");
                        datas.execSQL("insert into user values ("+title+","+url+")");

                        newsArrayList.add(title);
                        urls.add(url);
                        articleid = "";
                        i++;
                        count--;
                    }
                }
                download.setAlpha(0f);
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newsArrayList);
                newsList.setAdapter(adapter);
                newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(getApplicationContext(), Main2Activity.class);
                        in.putExtra("url", urls.get(i));
                        startActivity(in);
                    }
                });


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Cursor c1=datas.rawQuery("select * from user",null);
            int titleIndex=c1.getColumnIndex("title");
            int urlIndex=c1.getColumnIndex("url");
            c1.moveToFirst();
            while(!c1.isAfterLast())
            {
                newsArrayList.add(c1.getString(titleIndex));
                urls.add(c1.getString(urlIndex));
            }
            adapter.notifyDataSetChanged();
            newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(getApplicationContext(), Main2Activity.class);
                    in.putExtra("url", urls.get(i));
                    startActivity(in);
                }
            });
        }

    }
}
