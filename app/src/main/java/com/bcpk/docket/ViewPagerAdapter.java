package com.bcpk.docket;

import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Billy on 4/3/15.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    // Tab info - instantiated via constructor
    CharSequence tabTitles[];
    int numTabs;
    String activity;

    public ViewPagerAdapter(FragmentManager fm, CharSequence tabTitles[], int numTabs, String activity) {
        super(fm);

        this.tabTitles = tabTitles;
        this.numTabs = numTabs;
        this.activity = activity;
    }

    // Gets the appropriate tab fragment and returns
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            if (this.activity.equals("MainActivity")) {
                LocationsTabFragment locationsTabFragment = new LocationsTabFragment();
                return locationsTabFragment;
            }
            else {
                ResourcesTabFragment resourcesTabFragment = new ResourcesTabFragment();
                return resourcesTabFragment;
            }
        } else {
            if (this.activity.equals("MainActivity")) {
                FoursquareTabFragment foursquareTabFragment = new FoursquareTabFragment();
                return foursquareTabFragment;
            }
            else {
                OrganizationsTabFragment organizationsTabFragment = new OrganizationsTabFragment();
                return organizationsTabFragment;
            }
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
