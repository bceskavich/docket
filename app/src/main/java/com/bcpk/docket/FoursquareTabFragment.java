package com.bcpk.docket;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Billy on 4/3/15.
 */

public class FoursquareTabFragment extends Fragment {

    // Foursquare instance vars
    private TextView foursquareTestView;
    private FoursquareDocket fsqDocket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_foursquare_list, container, false);

        // Sets the Foursquare vars & loads in info
        foursquareTestView = (TextView) view.findViewById(R.id.foursquareTestText);
        fsqDocket = new FoursquareDocket();
        loadFoursquareLocations();

        return view;
    }

    private void loadFoursquareLocations() {
        // First, test if we have internet connectivity
        ConnectivityManager connManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
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
