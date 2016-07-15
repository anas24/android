package com.example.anas.memorableplaces;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent in =getIntent();
        index=in.getIntExtra("Placeinfo",-1);

        Log.i("place info",Integer.toString(in.getIntExtra("Placeinfo",-1)));
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                switch (item.getItemId())
                {
                    case android.R.id.home:
                        this.finish();
                        return true;
                    default:

                }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        if (index > 0)
        {
            LatLng sydney = MainActivity.places.get(index);
            mMap.addMarker(new MarkerOptions().position(sydney).title(MainActivity.memorableArrayList.get(index)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.setOnMapLongClickListener(this);
            mMap.animateCamera( CameraUpdateFactory.newLatLngZoom(sydney,10));
        }
        if(index==-1 || index==0)
        {
            LatLng sydney=new LatLng(0,0);
            mMap.addMarker(new MarkerOptions().position(sydney).title("add new place"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.setOnMapLongClickListener(this);
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng)
    {
        String label=(new Date()).toString();
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try
        {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addressList!=null && addressList.size()>=1)
            {
                label=addressList.get(0).getAddressLine(0).toString();
            }
            addressList.clear();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        MainActivity.memorableArrayList.add(label);
        MainActivity.adapter.notifyDataSetChanged();
        MainActivity.places.add(latLng);
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(label)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }
}