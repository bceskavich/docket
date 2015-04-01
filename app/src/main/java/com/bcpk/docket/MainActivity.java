package com.bcpk.docket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bcpk.docket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ledzee on 3/24/15.
 */


public class MainActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {

   /* public static final String[] titles = new String[] {
            "Strawberry",
            "Banana",
            "Orange",
            "Mixed",
            "Banana",
            "Orange",
            "Mixed",
            "Strawberry",
            "Banana",
            "Orange",
            "Mixed" };

    public static final String[] descriptions = new String[] {
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            "Mixed Fruits",
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            "Mixed Fruits",
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            };*/

    public static final String[] titles = new String[] {
            "Strawberry",
            "Banana",
            "Orange",
            "Mixed",
            "Yo",
            "Strawberry",
            "Banana",
            "Orange",
            "Mixed",
            "Yo"};

    public static final String[] descriptions = new String[] {
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            "Mixed Fruits",
            "this should work",
            "It is an aggregate accessory fruit",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            "Mixed Fruits",
            "this should work" };

    public static final Integer[] images = {
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher

    };

    ListView listView;
    List<Location> rowItems;

    // Nav menu vars
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private String[] mActivityTitles;
    private String navTitle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigation titles
        navTitle = getResources().getString(R.string.nav_name);
        mActivityTitles = getResources().getStringArray(R.array.nav_array);

        // Gets the layouts for the nav
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.main_left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mActivityTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        // getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Sets listener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }

        rowItems = new ArrayList<Location>();
        for (int i = 0; i < titles.length; i++) {
            Location item = new Location(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        //listView = (ListView) findViewById(R.layout.activity_main.list);
        listView = (ListView) findViewById(R.id.list);
        LocationAdapter adapter = new LocationAdapter(this,
                R.layout.singlelocation_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_popup:
                // Initiates nav menu & inflater
                PopupMenu navMenu = new PopupMenu(this, v);
                MenuInflater inflater = navMenu.getMenuInflater();

                // Sets listener and nav logic
                navMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        // Based on given ID, launches new activity
                        switch(item.getItemId()){
                            case R.id.menuOptionMain:
                                return true;
                            case R.id.menuOptionContact:
                                startActivity(new Intent("com.bcpk.LocationActivity"));
                            default:
                                return false;
                        }
                    }
                });

                return true;
            default:
                return false;
        }
    }

    // Handles menu click and navigates to new activities
    public void showPopupNav(View v) {
        // Initiates nav menu & inflater
        PopupMenu navMenu = new PopupMenu(this, v);
        MenuInflater inflater = navMenu.getMenuInflater();

        // Sets listener and nav logic
        navMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                // Based on given ID, launches new activity
                switch(item.getItemId()){
                    case R.id.menuOptionMain:
                        return true;
                    case R.id.menuOptionContact:
                        startActivity(new Intent("com.bcpk.LocationActivity"));
                    default:
                        return false;
                }
            }
        });

    }

}