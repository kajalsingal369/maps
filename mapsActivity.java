package com.example.kj.maps;

import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Perfect", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_maps);
            initMap();
        }
        else
        {
            setContentView(R.layout.error);
        }
    }

    private void initMap() {
        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);


    }

    public boolean googleServicesAvailable()
    {
        GoogleApiAvailability api= GoogleApiAvailability.getInstance();
        int isAvailable= api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(api.isUserResolvableError(isAvailable))
        {
            Dialog dialog=api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
    else{
            Toast.makeText(this,"Can't connect to play services",Toast.LENGTH_LONG).show();
        }
        return false;
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        goToLocationZoom(39.008224,-76.8984527,15.0);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void goToLocation(double lat, double lng) {
        LatLng l1=new LatLng(lat,lng);
        CameraUpdate update=CameraUpdateFactory.newLatLng(l1);
        googleMap.moveCamera(update);
    }
    private void goToLocationZoom(double lat, double lng,double zoom) {
        LatLng l1=new LatLng(lat,lng);
        CameraUpdate update=CameraUpdateFactory.newLatLngZoom(l1, (float) zoom);
        googleMap.moveCamera(update);
    }
}
