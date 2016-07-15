package com.example.anas.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    String provider;
    TextView LatText,LongText,altitud,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        LatText =(TextView)findViewById(R.id.latvlue);
        LongText =(TextView)findViewById(R.id.longvalue);
        altitud =(TextView)findViewById(R.id.alvalue);
         address =(TextView)findViewById(R.id.addressvalue);



        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider=locationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location location = locationManager.getLastKnownLocation(provider);
        if(location!=null)
        {
            Log.i("Location","achieved");
        }
        else
        {
            Log.i("Location","not achieved");
            Toast.makeText(getApplicationContext(),"Kindly connect to GPS services and check your internet connection",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(provider,400,1,this);
    }

    @Override
    public void onLocationChanged(Location location)
    {

        Double lat= location.getLatitude();
        Double lng= location.getLongitude();
        Double alt=location.getAltitude();
        float bearing=location.getBearing();
        float speed=location.getSpeed();
        Geocoder geocoder =new Geocoder(getApplicationContext(), Locale.getDefault());
        Log.i("longitude:",lng.toString());
        Log.i("Latitude:",lat.toString());
        Log.i("bearing:",Float.toString(bearing));
        Log.i("speed:",Float.toString(speed));
        LatText.setText(lat.toString());
        LongText.setText(lng.toString());
        altitud.setText(alt.toString());

        try
        {
            List<Address> addressList = geocoder.getFromLocation(lat,lng,1);
            if(addressList!=null && addressList.size()>0)
            {
                Log.i("address",addressList.get(0).toString());
                String addressHolder="";
                for(int i=0;i<=addressList.get(0).getMaxAddressLineIndex();i++)
                {
                    addressHolder+=addressList.get(0).getAddressLine(i)+"\n";
                }
               address.setText(addressHolder.toString());
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
