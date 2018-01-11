package com.example.toshiba10.mapppppps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity implements  OnMapReadyCallback{
    GoogleMap Map;
    LocationManager locationManager;
    locationlistner locationlistner;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationlistner = new locationlistner(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, locationlistner);
        }
        catch (SecurityException e)
        {
            Toast.makeText(this,"can not access to the cureent location",Toast.LENGTH_LONG).show();
        }

        SupportMapFragment MapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
          MapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
            Map=googleMap;
            Map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960,31.235711600),8));
            //Get Current location
            Map.clear();
            Geocoder geocoder = new Geocoder(getApplicationContext());
            List<Address> addressesList ;
            Location location = null;
            try {
                location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            }
            catch (SecurityException e)
            {
                Toast.makeText(this,"can not access to the cureent location",Toast.LENGTH_LONG).show();
            }

            if (location!=null)
            {
                LatLng mypostion= new LatLng(location.getLatitude() , location.getLongitude());
                try{
                    addressesList = geocoder.getFromLocation(mypostion.latitude ,mypostion.longitude,1);
                    if (!addressesList.isEmpty())
                    {
                        String address="";
                        for (int i = 0 ; i <= addressesList.get(0).getMaxAddressLineIndex();i++ )
                        {
                            address+=addressesList.get(0).getAddressLine(i)+", ";
                        }
                        Map.addMarker(new MarkerOptions().position(mypostion).title("My location").snippet(address)).setDraggable(true);
                        Toast.makeText(this,address,Toast.LENGTH_LONG).show();
                    }
                }
                catch (IOException e)
                {  Map.addMarker(new MarkerOptions().position(mypostion).title("My location"));

                }
                Map.moveCamera(CameraUpdateFactory.newLatLngZoom(mypostion,15));
            }else {
                Toast.makeText(this,"please waite until network load",Toast.LENGTH_LONG).show();
            }


    }

}
