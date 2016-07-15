package com.example.anas.databasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            SQLiteDatabase mydb = this.openOrCreateDatabase("users", Context.MODE_PRIVATE, null);
            mydb.execSQL("create table if not exists users (name varchar(30) ,age int )");
//            mydb.execSQL("insert into users values ('anas',21)");
//            mydb.execSQL("insert into users values ('saba',22)");
//            mydb.execSQL("insert into users values ('amir',21)");

            mydb.execSQL("delete from users where name='anas' ");
            Cursor c = mydb.rawQuery("select * from users", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            c.moveToFirst();
            while (!c.isAfterLast() ) {
                Log.i("name-", c.getString(nameIndex));
                Log.i("age-", c.getString(ageIndex));
                c.moveToNext();
            }
            c.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
