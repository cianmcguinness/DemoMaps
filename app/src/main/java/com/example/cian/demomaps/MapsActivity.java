package com.example.cian.demomaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import java.util.List;
import java.util.Locale;

//ryan test comment for first commit

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener {
    DatabaseHelper myDb;
    private GoogleMap mMap;
    double reportLat = 0;
    double reportLong = 0;
    String reportAddress = "";
    String reportDescription = "";

    Marker reportMarker = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);

        myDb = new DatabaseHelper(this);


    }

    public void report(View view) {
        EditText description_tf = (EditText) findViewById(R.id.editDescription);
        String description = description_tf.getText().toString();

        reportDescription = description;

        Toast.makeText(MapsActivity.this, "report:\n" +
                "reportLat: " + reportLat + "\n" +
                "reportLong: " + reportLong + "\n" +
                "reportAddress: " + reportAddress + "\n" +
                "reportDescription: " + reportDescription, Toast.LENGTH_LONG).show();


        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************
        //Todo insert to database************************************


    }

    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();

        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);


            } catch (IOException e) {
                e.printStackTrace();
            }


            Address address = addressList.get(0);
            LatLng latlng = new LatLng(address.getLatitude(), address.getLongitude());

            reportMarker.setPosition(new LatLng(address.getLatitude(), address.getLongitude()));
            reportAddress = address.toString();
            reportLat = address.getLatitude();
            reportLong = address.getLongitude();

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));

        }
    }

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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
        mMap.setMyLocationEnabled(true);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ireland));
        //LatLng here = new LatLng(53.2734, -7.778320310000026);
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(here, 6));
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);

        //LocationManager locationManager = (LocationManager)
          //      getSystemService(Context.LOCATION_SERVICE);
        //Criteria criteria = new Criteria();

        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
          //  return;
        //}
        //String provider = locationManager.getBestProvider(criteria, false);

        //Location location = locationManager.getLastKnownLocation(provider);

        //final double latitude = location.getLatitude();
        //final double longitude = location.getLongitude();

        double latitude = 0;
        double longitude = 0;

        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            latitude = gpsTracker.latitude;
            longitude = gpsTracker.longitude;
        }
        else
        {
            Toast.makeText(MapsActivity.this,
                    "GPS_Disabled:\n",
                    Toast.LENGTH_LONG).show();
        }

        LatLng here = new LatLng(latitude, longitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(here, 14));

        MarkerOptions markerOptions =
                new MarkerOptions().position(here).draggable(true).title("Drag Marker To Location Of Report");
        reportMarker = mMap.addMarker(markerOptions);
        reportMarker.showInfoWindow();

        reportLat = latitude;
        reportLong = longitude;


        final Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this,Locale.getDefault());
        try{
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        }catch (IOException e){
            e.printStackTrace();
        }
        reportAddress = addresses.get(0).getAddressLine(0);


        Toast.makeText(MapsActivity.this,
                "onMapReady:\n" + reportAddress,
                Toast.LENGTH_LONG).show();

        mMap.setOnMarkerDragListener(new OnMarkerDragListener(){

            @Override
            public void onMarkerDragStart(Marker marker) {
                // TODO Auto-generated method stub
                // Here your code
                //Toast.makeText(MapsActivity.this, "Dragging Start",
                       // Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng position = marker.getPosition(); //

                reportLat =  position.latitude;
                reportLong = position.longitude;

                List<Address> addresses = null;
                try{
                    addresses = geocoder.getFromLocation(reportLat, reportLong, 1);
                }catch (IOException e){
                    e.printStackTrace();
                }

                reportAddress = addresses.get(0).getAddressLine(0);

                Toast.makeText(MapsActivity.this,
                        "onMarkerDragEnd:\n" + reportAddress,
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                // TODO Auto-generated method stub
                // Toast.makeText(MainActivity.this, "Dragging",
                // Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onMapClick(LatLng latLng) {
        /*Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this,Locale.getDefault());
        try{
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        }catch (IOException e){
            e.printStackTrace();
        }

        String address = "";
        address = addresses.get(0).getAddressLine(0);

        Toast.makeText(MapsActivity.this,
                "onMapClick:\n" + address + "\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();*/
    }



    @Override
    public void onMapLongClick(LatLng latLng) {


        Geocoder geocoder;
        List <Address> addresses = null;
        geocoder = new Geocoder(this,Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = "";
        address = addresses.get(0).getAddressLine(0);

        reportMarker.setPosition(latLng);
        reportAddress = address;
        reportLat = latLng.latitude;
        reportLong = latLng.longitude;

        Toast.makeText(MapsActivity.this,
                "onMapLongClick:\n" + address + "\n" + latLng.latitude + " : " + latLng.longitude,
                Toast.LENGTH_LONG).show();

        //Add marker on LongClick position
        //MarkerOptions markerOptions =
          //      new MarkerOptions().position(latLng).draggable(true).title(latLng.toString());
        //mMap.addMarker(markerOptions);

        /*Toast.makeText(MapsActivity.this,
                "Long " + mMap.getMyLocation().getLongitude()
                        + " Lat " + mMap.getMyLocation().getLongitude(),
                Toast.LENGTH_LONG).show();*/

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }
        return true;
    }

}
