package com.example.allexamples.apps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.allexamples.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HikerWatchActivity extends AppCompatActivity {

    private TextView textViewLat;
    private TextView textViewLon;
    private TextView textViewAcc;
    private TextView textViewAlt;
    private TextView textViewAddress;
    LocationManager locMan;
    LocationListener locLis;

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
        setContentView(R.layout.activity_main);
        textViewLat = findViewById(R.id.textViewLatitude);
        textViewLon = findViewById(R.id.textViewLongtitude);
        textViewAcc = findViewById(R.id.textViewAccuracy);
        textViewAlt = findViewById(R.id.textViewAltitude);
        textViewAddress = findViewById(R.id.textViewAddress);

        locMan = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locLis = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                textViewLat.setText(Double.toString(location.getLatitude()));
                textViewLon.setText(Double.toString(location.getLongitude()));
                textViewAcc.setText(Double.toString(location.getAccuracy()));
                textViewAlt.setText(Double.toString(location.getAltitude()));
                textViewAddress.setText(getAddress(location));
                Log.i("Data", location.toString());
            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,locLis);
        }

    }

    private String getAddress(Location location){
        Geocoder goCod = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "No address found";
        try {

            double lat = location.getLatitude();
            double lon = location.getLongitude();
            List<Address> myAdrress = goCod.getFromLocation(lat, lon,1);
            if(myAdrress != null && myAdrress.size()>0){
                address ="";
                if(myAdrress.get(0).getSubThoroughfare() != null){
                    address += myAdrress.get(0).getSubThoroughfare() + "\n";
                }
                if(myAdrress.get(0).getThoroughfare() != null){
                    address += myAdrress.get(0).getThoroughfare() + "\n";
                }
                if(myAdrress.get(0).getLocality() != null){
                    address += myAdrress.get(0).getLocality() + "\n";
                }
                if(myAdrress.get(0).getPostalCode() != null){
                    address += myAdrress.get(0).getPostalCode() + "\n";
                }
                if(myAdrress.get(0).getCountryName() != null){
                    address += myAdrress.get(0).getCountryName() + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}