package com.bcpk.docket;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


public class LocationActivity extends ActionBarActivity {

    private static final String TAG = "LocationActivity";

    // Location info strings
    private String locationName;
    private String locationAddress;
    private String locationDescription;

    // Location info text views
    private TextView locationDetailNameView;
    private TextView locationDetailAddressView;
    private TextView locationDetailDescView;

    // Image view
    private ImageView locationDetailImageView;

    // TODO - Map Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // TODO - Make more dynamic, test values for now
        locationName = "Hinds Hall (iSchool)";
        locationAddress = "University Place, Syracuse, NY 13244";
        locationDescription = "The home of the iSchool, Hinds Hall is an innovation center filled" +
                "with collaborative spaces.";

        // Populate the text views
        locationDetailNameView = (TextView) findViewById(R.id.locationDetailNameView);
        locationDetailAddressView = (TextView) findViewById(R.id.locationDetailAddressView);
        locationDetailDescView = (TextView) findViewById(R.id.locationDetailDescriptionView);

        // Populate the image view
        locationDetailImageView = (ImageView) findViewById(R.id.locationDetailImageView);

        // Sets text values
        locationDetailNameView.setText(locationName);
        locationDetailAddressView.setText(locationAddress);
        locationDetailDescView.setText(locationDescription);

        // Uses the AssetManager to load from the assets folder
        AssetManager assets = getAssets();

        try {
            // get an InputStream
            InputStream stream = assets.open("hinds.png");

            // Load asset as a Drawable and display
            Drawable headerImage = Drawable.createFromStream(stream, "hinds");
            locationDetailImageView.setImageDrawable(headerImage);
        } catch (IOException exception) {
            Log.e(TAG, "Error loading hinds", exception);
        }

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
