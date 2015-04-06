package com.bcpk.docket;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Billy on 4/3/15.
 */

public class FoursquareTabFragment extends Fragment implements AdapterView.OnItemClickListener {

    // Foursquare instance vars
    private ListView fsqListView;
    private FoursquareDocket fsqDocket;
    private ArrayList<FoursquareVenue> venueList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_foursquare_list, container, false);

        // Sets the Foursquare vars & loads in info
        fsqListView = (ListView) view.findViewById(R.id.fsqListView);
        fsqListView.setOnItemClickListener(this);
        fsqDocket = new FoursquareDocket();
        loadFoursquareLocations();

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        /*
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        */

        // Grabs location detail and on click and adds to a bundle
        FoursquareVenue item = venueList.get(position);

        Bundle locBundle = new Bundle();
        locBundle.putString("title", item.name);
        locBundle.putString("description", item.description);

        // Passes bundle to an intent / starts the intent
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        intent.putExtras(locBundle);
        startActivity(intent);

    }

    private void loadFoursquareLocations() {
        // First, test if we have internet connectivity
        ConnectivityManager connManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FoursquareTask().execute("Go!");
        }
    }

    private class FoursquareTask extends AsyncTask<String, Void, ArrayList<FoursquareVenue>> {

        @Override
        protected ArrayList<FoursquareVenue> doInBackground(String... message) {
            venueList = new ArrayList<>();

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
            FoursquareAdapter adapter = new FoursquareAdapter(getActivity(),
                    R.layout.singlelocation_item, venueList);
            fsqListView.setAdapter(adapter);
        }
    }

}
