package com.example.pedrolopes.internshapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        try {
            final JSONArray[] js = new JSONArray[1];
            Thread t = new Thread(){
                public void run(){
                    try {
                        //aqui vamos buscar pelos objetos onde ta ai nformação
                       String address = (String) getIntent().getStringExtra("address");
                        PostHandler ps = new PostHandler(address);
                        js[0] = ps.getResponse().getJSONArray("results");

                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
            //start the thread
            t.start();

            //waits for the thread
            t.join();


            //check if js.length() is 0, it means its empty and no results.
            JSONObject latLn = js[0].getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
            LatLng sydney = new LatLng(latLn.getDouble("lat"), latLn.getDouble("lng"));
            mMap.addMarker(new MarkerOptions().position(sydney).title("Localizaçao"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
