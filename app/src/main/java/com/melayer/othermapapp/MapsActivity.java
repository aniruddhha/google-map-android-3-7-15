package com.melayer.othermapapp;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {

    private Geocoder geoCoder;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       geoCoder = new Geocoder(this);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.setOnMapClickListener(this);
        mMap.addMarker(new MarkerOptions().position(new LatLng(18.56, 73.816)).title("Marker"));
    }

    @Override
    public void onMapClick(LatLng latLng) {

        MarkerOptions opts = new MarkerOptions();
        opts.position(latLng);

        try {

            List<Address> list =  geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 5); // forward geocoding
            Address a = list.get(0);

            opts.title(a.getCountryName());
            Log.i("###### ADDRESS ######",a.toString());

        }
        catch (Exception e){

            e.printStackTrace();
        }
        mMap.addMarker(opts);
    }
}
