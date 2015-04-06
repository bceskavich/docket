package com.bcpk.docket;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ledzee on 3/24/15.
 */


public class ResourcesActivity extends ActionBarActivity implements
        AdapterView.OnItemClickListener {


    public final static String ID_EXTRA = "com.bcpk.docket._ID1";
    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";

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
            "MakerSpace",
            "Nexis",
            "Career Services",
            "Greek Fratenities",
            "Writing Cetre",
            "Campus Tours",
            "Banana",
            "Orange",
            "Mixed",
            "Yo"};

    public static final String[] descriptions = new String[] {
            "Home to iSchool, School of Information Studies",
            "Library for all your library needs",
            "cafe, Box Office, Career Services. You will find everything here",
            "School of management. MBA stuff.",
            "Life sciences",
            "Your religious place on campus",
            "It is the largest herbaceous flowering plant",
            "Citrus Fruit",
            "Mixed Fruits",
            "this should work" };

    public static final Integer[] images = {
            R.drawable.ic_launcher,
            R.drawable.bird,
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
    private Toolbar toolbar;
    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle navDrawerToggle;
    private ArrayAdapter<String> navArrayAdapter;
    private String[] navTitles;
    private String navTitle;

    // For logging
    private final String TAG = "MainActivity";
    private String Title;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates drawer view
        initDrawerView();
        if (toolbar != null) {
            toolbar.setTitle(getResources().getString(R.string.nav_name));
            setSupportActionBar(toolbar);
        }

        // Creates the drawer nav itself
        initDrawer();

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

    // Populates our nav drawer view
    private void initDrawerView() {
        navDrawerList = (ListView) findViewById(R.id.main_left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        navTitles = getResources().getStringArray(R.array.nav_array);

        // Sets adapter
        navDrawerList.setAdapter(new ArrayAdapter<String>(ResourcesActivity.this,
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
                    break;
                case "Contact Us":
                    Intent contactIntent = new Intent(getApplicationContext(), ContactUs.class);
                    startActivity(contactIntent);
                    break;
            }
            Log.d("navPos", "This is the navTittles"+ navTitles[position]);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (navDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {


        String passableDesc = rowItems.get(position).getDesc();
        String passableTitle = rowItems.get(position).getTitle();
        int passableImage = rowItems.get(position).getImageId();
        Log.d("ImgID", "This is the imageID"+passableImage);

        Intent goToResource = new Intent(ResourcesActivity.this, SingleResource.class);

        goToResource.putExtra(ID_TITLE, passableTitle);
        goToResource.putExtra(ID_IMG, passableImage);
        goToResource.putExtra(ID_DESC, passableDesc);

        startActivity(goToResource);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}