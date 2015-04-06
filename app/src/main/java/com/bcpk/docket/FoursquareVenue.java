package com.bcpk.docket;

import android.location.Location;

/**
 * @author - Billy Ceskavich (3/29/15)
 *
 * FoursquareVenue - Used to create standardized location objects based on Foursquare API responses
 */

public class FoursquareVenue {
    public String name;
    public String rating;
    public String street;
    public String city;
    public String state;
    public String zip;
    public String photoUrl;
    public Location location;
    public String description; // Based on Foursquare category info
}
