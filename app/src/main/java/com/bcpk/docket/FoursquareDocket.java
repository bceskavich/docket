package com.bcpk.docket;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.bcpk.docket.FoursquareVenue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author - Billy Ceskavich (3/29/15)
 *
 * FoursquareDocket - A Java class for handling Foursquare connectivity for the Docket app.
 *
 * Modeled after Lorensius's Android Foursquare library tutorial:
 * https://github.com/lorensiuswlt/AndroidFoursquare
 *
 */

public class FoursquareDocket {
    // Class vars for the Foursquare API
    private static final String API_URL = "https://api.foursquare.com/v2";
    private static final String VENUES_ENDPOINT = "/venues/explore";
    private static final String CLIENT_ID = "AICCHQOFSIJ5VJWZFBKLVT3MFTAMH3C5UAYWL5D5MG5L2XHI";
    private static final String CLIENT_SECRET = "4BT3M3CLDZQEJJAPSR52TIZAWJ2UZS3UOYM0VZCMHJU3QSNE";
    private static final String M = "foursquare";
    private static final String V = "20150329";

    // For logging
    private static final String TAG = "FoursquareDocket";

    // For GPS
    private Context context;

    public FoursquareDocket(Context context) {
        this.context = context;
    }

    /**
     * Grabs a nearby list of popular venues
     *
     */
    public ArrayList<FoursquareVenue> getVenues() throws Exception {
        ArrayList<FoursquareVenue> venueList = new ArrayList<>();

        String near = "Syracuse%20NY";
        String section = "topPicks";
        String venuePhotos = "1";
        String limit = "20";

        // Creates the full GET URL from params
        // If GPS location isn't available, uses "Syracuse, NY" by default
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        URL url;

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "GPS enabled!");
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (l != null) {
                Log.d(TAG, "Location found.");
                double lat = l.getLatitude();
                double lng = l.getLongitude();

                url = new URL(API_URL + VENUES_ENDPOINT + "?ll=" + Double.toString(lat) + "," +
                        Double.toString(lng) + "&section=" + section + "&venuePhotos=" + venuePhotos
                        + "&limit=" + limit + "&client_id=" + CLIENT_ID + "&client_secret=" +
                        CLIENT_SECRET + "&m=" + M + "&v=" + V);
            } else {
                Log.d(TAG, "Couldn't find last location.");
                url = new URL(API_URL + VENUES_ENDPOINT + "?near=" + near + "&section=" + section +
                        "&venuePhotos=" + venuePhotos + "&limit=" + limit + "&client_id=" + CLIENT_ID +
                        "&client_secret=" + CLIENT_SECRET + "&m=" + M + "&v=" + V);
            }
        } else {
            Log.d(TAG, "GPS not enabled.");
            url = new URL(API_URL + VENUES_ENDPOINT + "?near=" + near + "&section=" + section +
                    "&venuePhotos=" + venuePhotos + "&limit=" + limit + "&client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&m=" + M + "&v=" + V);
        }

        Log.d(TAG, "Connecting to Foursquare API URL: " + url.toString());

        // Try to download the data - throw error if it fails
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Sets to GET, sends input, and timeouts
            urlConnection.setReadTimeout(10000); // 10 sec.
            urlConnection.setConnectTimeout(15000); // 15 sec.
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            // Starts the connection
            urlConnection.connect();

            // Converts and loads in response
            JSONObject jsonResp = (JSONObject) new JSONTokener(
                    respToString(urlConnection.getInputStream())).nextValue();

            // First, check if the status code returns as good
            Integer respCode = jsonResp.getJSONObject("meta").getInt("code");
            if (respCode != 200) {
                Log.d(TAG, "Foursquare returned error code: " + respCode.toString());
                throw new Exception("Foursquare API error.");
            }

            // If the status is good, loads in the info
            JSONArray groups = (JSONArray) jsonResp.getJSONObject("response").getJSONArray("groups");

            // If we have actual locations, loop thru and load information in
            if (groups.length() > 0) {
               for (int i = 0; i < groups.length(); i++) {
                   // Loads in venue list for the given group
                   JSONObject group = (JSONObject) groups.get(i);
                   JSONArray items = (JSONArray) group.getJSONArray("items");

                   // Loops thru the current venue list for info
                   for (int j = 0; j < items.length(); j++) {
                       JSONObject venue = (JSONObject) items.getJSONObject(j).getJSONObject("venue");

                       FoursquareVenue fsqVenue = new FoursquareVenue();

                       fsqVenue.name = venue.getString("name");

                       try {
                           fsqVenue.rating = venue.getString("rating");
                       } catch (JSONException e) {
                           Log.d(TAG, "Location rating not found for: " + fsqVenue.name);
                           fsqVenue.rating = "No Rating";
                       }

                       JSONObject location = venue.getJSONObject("location");
                       fsqVenue.street = location.getJSONArray("formattedAddress").getString(0);
                       fsqVenue.city = location.getString("city");
                       fsqVenue.state = location.getString("state");

                       try {
                           fsqVenue.zip = location.getString("postalCode");
                       } catch (JSONException e) {
                           Log.d(TAG, "Location zip code not found for: " + fsqVenue.name);
                           fsqVenue.zip = null;
                       }

                       // Parses location lat / long vals
                       Location loc = new Location(LocationManager.GPS_PROVIDER);
                       loc.setLatitude(Double.valueOf(location.getString("lat")));
                       loc.setLongitude(Double.valueOf(location.getString("lng")));
                       fsqVenue.location = loc;

                       // Creates the F

                       // Creates the photo URL (if it exists)
                       try {
                           // If a featured photo exists, use it
                           JSONObject featuredPhotos = venue.getJSONObject("featuredPhotos");
                           if (featuredPhotos.getInt("count") > 0) {
                               // Loads in the photo item
                               JSONObject photoItem = featuredPhotos.getJSONArray("items")
                                       .getJSONObject(0);

                               // Pull out photo pieces
                               String prefix = photoItem.getString("prefix");
                               String width = photoItem.getString("width");
                               String suffix = photoItem.getString("suffix");

                               // Build URL
                               fsqVenue.photoUrl = prefix + "width" + width + suffix;
                           } else {
                               fsqVenue.photoUrl = null;
                           }
                       } catch (JSONException e) {
                           Log.d(TAG, "Featured image not found for: " + fsqVenue.name);
                           fsqVenue.photoUrl = null;
                       }

                       // Finally, do the categorical description
                       try {
                           JSONObject category = venue.getJSONArray("categories").getJSONObject(0);
                           fsqVenue.description = category.getString("name");
                       } catch (JSONException e) {
                           Log.d(TAG, "Foursquare description not found for: " + fsqVenue.name);
                           fsqVenue.description = null;
                       }

                       // TODO - remove per testing
                       Log.d(TAG, fsqVenue.name + " - " + fsqVenue.rating);

                       // After all the information has been loaded in, add to list
                       venueList.add(fsqVenue);
                   }
               }
            } else {
                Log.d(TAG, "No Foursquare locations returned.");
                throw new Exception("No Foursquare locations returned.");
            }

        } catch (Exception e) {
            throw e;
        }

        return venueList;
    }

    // Converts initial Foursquare response to a String
    private String respToString(InputStream stream) throws IOException {
        String stringResp = "";

        // First, parse stream into string by looping thru the lines
        // Then, load into a JSON object
        if (stream != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }

                reader.close();
            } finally {
                stream.close();
            }

            stringResp = sb.toString();
        }

        return stringResp;
    }
}
