package com.operationcodify.cavoid.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.operationcodify.cavoid.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    private NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.dashboardBottomMenu:
                        Intent dashboardIntent = new Intent(MapsActivity.this, DashboardActivity.class);
                        startActivity(dashboardIntent);
                        break;
                    case R.id.pastLocationBottomMenu:
                        Intent pastLocationIntent = new Intent(MapsActivity.this, PastLocationActivity.class);
                        startActivity(pastLocationIntent);
                        break;
                }
                return false;
            }
        });

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(MapsActivity.this, SettingsActivity.class);
                //Log.d(DashboardActivity.class.getName(), "Intent didn't start" + settingsIntent);
                this.startActivity(settingsIntent);
                break;
            case R.id.action_appInfo:
                Intent appInfoIntent = new Intent(MapsActivity.this, AppInfoActivity.class);
                this.startActivity(appInfoIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
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

//        Polygon polygon = PolygonUtils.createCountyPolygon(MapsActivity.this, mMap);
//        polygon.setClickable(true);
//        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
//            @Override
//            public void onPolygonClick(Polygon polygon){
//                System.out.println("click");
//                polygon.setFillColor(Color.RED);
//            }
//        });

    }



}