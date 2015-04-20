package com.bcpk.docket;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class LocationActivity extends ActionBarActivity implements OnMapReadyCallback {

    // For logging
    private static final String TAG = "LocationActivity";

    // Nav menu vars
    private Toolbar toolbar;
    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle navDrawerToggle;
    private ArrayAdapter<String> navArrayAdapter;
    private String[] navTitles;
    private String navTitle;

    // Location info strings
    private String locationName;
    private String locationAddress;
    private String locationDescription;
    private String locationType;
    private LatLng location;

    // Location info text views
    private TextView locationDetailNameView;
    private TextView locationDetailAddressView;
    private TextView locationDetailDescView;

    // Image view
    private ImageView locationDetailImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Creates drawer view
        initDrawerView();
        if (toolbar != null) {
            toolbar.setTitle(getResources().getString(R.string.nav_name));
            setSupportActionBar(toolbar);
        }

        // Creates the drawer nav itself
        initDrawer();

        Bundle extras = getIntent().getExtras();
        locationName = extras.getString("title");

        // Check to see if we have a long description
        Log.d(TAG, extras.getString("longDescription"));
        if (extras.getString("longDescription").equals("")) {
            locationDescription = extras.getString("description");
        } else {
            locationDescription = extras.getString("longDescription");
        }

        locationType = extras.getString("locationType");

        // Populate the text views
        locationDetailNameView = (TextView) findViewById(R.id.locationDetailNameView);
        locationDetailAddressView = (TextView) findViewById(R.id.locationDetailAddressView);
        locationDetailDescView = (TextView) findViewById(R.id.locationDetailDescriptionView);

        // Populate the image view
        locationDetailImageView = (ImageView) findViewById(R.id.locationDetailImageView);

        // Load specific content for Foursquare vs. Hard-Coded
        if (locationType.equals("foursquare")) {
            // TODO - default image
            String image = extras.getString("image");
            Ion.with(locationDetailImageView).load(image);

            locationDescription = locationDescription + " - Rated " + extras.getString("rating") +
                    " by Foursquare users.";

            // Get address & lat/long info
            locationAddress = extras.getString("street");
            location = new LatLng(extras.getDouble("lat"), extras.getDouble("lng"));
        } else {
            // Loads hard-coded image
            Integer imageId = extras.getInt("image");
            locationDetailImageView.setImageResource(imageId);

            // Loads string address & converts to lat/long for the map
            locationAddress = extras.getString("address");
            getLatLng();
        }

        // Sets text values
        locationDetailNameView.setText(locationName);
        locationDetailAddressView.setText(locationAddress);
        locationDetailDescView.setText(locationDescription);

        // Finally, sets up the map fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    // Populates our nav drawer view
    private void initDrawerView() {
        navDrawerList = (ListView) findViewById(R.id.location_left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.location_drawer_layout);
        navTitles = getResources().getStringArray(R.array.nav_array);

        // Sets adapter
        navDrawerList.setAdapter(new ArrayAdapter<>(LocationActivity.this,
                R.layout.drawer_list_item, navTitles));
        navDrawerList.setOnItemClickListener(new NavItemClickListener());
    }

    private void initDrawer() {
        navDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
            }

        };

        navDrawerLayout.setDrawerListener(navDrawerToggle);
    }

    private void getLatLng() {
        Geocoder coder = new Geocoder(this);

        // Try to get the address
        // If it fails to load or doesn't exist, set the default lat/long of Syracuse
        try {
            List<Address> address = coder.getFromLocationName(locationAddress, 1);

            if (address == null) {
                location = new LatLng(43.0377, -76.1340);
            } else {
                Address loc = address.get(0);
                location = new LatLng(loc.getLatitude(), loc.getLongitude());
            }
        } catch (IOException|IndexOutOfBoundsException e) {
            location = new LatLng(43.0377, -76.1340);
        }
    }

    // On nav menu item click
    private class NavItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(navTitles[position]){


                case "Locations":
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                    break;
                case "Take A Tour":
                    break;
                case "Resources":
                    Intent resources = new Intent(getApplicationContext(), ResourcesActivity.class);
                    startActivity(resources);
                    break;
                case "Contact Us":
                    Intent contactIntent = new Intent(getApplicationContext(), ContactUs.class);
                    startActivity(contactIntent);
                    break;
            }
        }
    }

    // OnMapReadyCallback required override
    @Override
    public void onMapReady(GoogleMap map) {
        // Allows user to find their location on the map
        map.setMyLocationEnabled(true);

        // Adds test marker for Syracuse, NY
        map.addMarker(new MarkerOptions()
                .position(location)
                .title(locationName));

        // Finally, zooms to the location
        CameraPosition position = new CameraPosition.Builder()
                .target(location)
                .zoom(15)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
