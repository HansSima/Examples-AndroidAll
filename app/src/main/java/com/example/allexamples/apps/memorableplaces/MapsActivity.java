package com.example.allexamples.apps.memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import com.example.allexamples.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    LocationManager locMan;
    LocationListener locLis;
    SharedPreferences sharedPreferences;
    ArrayList<String> listLatLng = new ArrayList<>();
    int position;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,locLis);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorable_places_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", Context.MODE_PRIVATE);
        position = getIntent().getIntExtra("positionOfCoordinates", 0);

        try {
            listLatLng = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("LatLng", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        locMan = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.i("Data", location.toString());
            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,locLis);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(!listLatLng.isEmpty()) {
            String[] coordinates = listLatLng.get(position).split("\n");
            LatLng ourPlace = new LatLng(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
            mMap.addMarker(new MarkerOptions().position(ourPlace).title("Here we go!"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ourPlace));
        }
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Intent changeIntent = new Intent(getApplicationContext(), MemorablePlacesApp.class);
        listLatLng.add(latLng.latitude + "\n" + latLng.longitude);

        try {
            String serializace = ObjectSerializer.serialize(listLatLng);
            sharedPreferences.edit().putString("LatLng", serializace).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startActivity(changeIntent);
    }

}