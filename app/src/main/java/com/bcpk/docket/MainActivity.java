package com.bcpk.docket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    private String[] mPlanetTitles;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
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