package com.example.toshiba10.mapppppps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by toshiba10 on 15/12/2017.
 */

public class locationlistner implements LocationListener {
    private Context context;

    public locationlistner(Context context) {
     this.context=context;

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(context,location.getLatitude()+","+location.getLongitude(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(context,"Gps   Enabled...!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(context,"Gps Disabled...!",Toast.LENGTH_LONG).show();
    }
}
