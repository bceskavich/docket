package com.bcpk.docket;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.AsyncTask;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.content.Context;

import com.bcpk.docket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ledzee on 3/24/15.
 */


public class MainActivity extends ActionBarActivity {

    // Foursquare instance vars
    private TextView foursquareTestView;
    private FoursquareDocket fsqDocket;

    // Declaring the tabs & corresponding fragments
    private ActionBar.Tab locationsTab, foursquareTab;
    private Fragment locationsTabFragment = new LocationsTabFragment();
    private Fragment foursquareTabFragment = new FoursquareTabFragment();
    private String[] tabNames = {"On Campus", "In Your Area"}; // Tab titles

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the Foursquare vars & loads in info
        foursquareTestView = (TextView) findViewById(R.id.foursquareTestView);
        fsqDocket = new FoursquareDocket();
        loadFoursquareLocations();


    }

    private void loadFoursquareLocations() {
        // First, test if we have internet connectivity
        ConnectivityManager connManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FoursquareTask().execute("Go!");
        } else {
            foursquareTestView.setText("No Internet connection.");
        }
    }

    private class FoursquareTask extends AsyncTask<String, Void, ArrayList<FoursquareVenue>> {

        @Override
        protected ArrayList<FoursquareVenue> doInBackground(String... message) {
            ArrayList<FoursquareVenue> venueList = new ArrayList<>();

            try {
                venueList = fsqDocket.getVenues();
            } catch (Exception e) {
                try {
                    throw e;
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            return venueList;
        }

        @Override
        protected void onPostExecute(ArrayList<FoursquareVenue> venueList) {
            Integer venuesLength = venueList.size();
            foursquareTestView.setText(venuesLength.toString());
        }
    }
}