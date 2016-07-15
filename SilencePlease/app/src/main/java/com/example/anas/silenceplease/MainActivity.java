package com.example.anas.silenceplease;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity
{
    int mylatitude,mylongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textgsmcellLocation=(TextView)findViewById(R.id.gsmcelllocation);
        TextView textcid=(TextView)findViewById(R.id.cid);
        TextView textLac=(TextView)findViewById(R.id.lac);
        TextView textGeo=(TextView)findViewById(R.id.geo);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation cellLocation= (GsmCellLocation) telephonyManager.getCellLocation();
        int cid=cellLocation.getCid();
        int lac=cellLocation.getLac();
        textgsmcellLocation.setText(cellLocation.toString());
        textcid.setText("gsm cell id "+ String.valueOf(cid));
        textLac.setText("gsm location area code "+String .valueOf(lac));
        Rqslocation obj=new  Rqslocation();
        Boolean r=obj.execute("http://www.google.com/glm/mmap").get();

        if(RqsLocation(cid,lac))
        {
            textGeo.setText(String.valueOf((float) mylatitude/1000000)+":"+String.valueOf((float) mylongitude/1000000));
        }
        else
        {
            textGeo.setText("Cant find Location");
        }

    }

    protected class  Rqslocation extends AsyncTask<String,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... strings)
        {

            return null;
        }
    }

    private void WriteData(OutputStream out, int cid, int lac)
            throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeShort(21);
        dataOutputStream.writeLong(0);
        dataOutputStream.writeUTF("en");
        dataOutputStream.writeUTF("Android");
        dataOutputStream.writeUTF("1.0");
        dataOutputStream.writeUTF("Web");
        dataOutputStream.writeByte(27);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(3);
        dataOutputStream.writeUTF("");

        dataOutputStream.writeInt(cid);
        dataOutputStream.writeInt(lac);

        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.writeInt(0);
        dataOutputStream.flush();
    }

}
