package com.bcpk.docket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

/**
 * Created by Billy on 4/11/15.
 */
public class TourActivity extends ActionBarActivity {

    // Nav menu vars
    private Toolbar toolbar;
    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle navDrawerToggle;
    private ArrayAdapter<String> navArrayAdapter;
    private String[] navTitles;
    private String navTitle;

    // For logging
    private final String TAG = "TourActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        // Creates drawer view
        initDrawerView();
        if (toolbar != null) {
            toolbar.setTitle(getResources().getString(R.string.nav_name));
            setSupportActionBar(toolbar);
        }

        // Creates the drawer nav itself
        initDrawer();

        SliderLayout sliderLayout = (SliderLayout) findViewById(R.id.tour_slider);

        HashMap<String,String> locImages = new HashMap<>();
        locImages.put("Bird Library", "http://upload.wikimedia.org/wikipedia/commons/5/58/Bird_Library,_Syracuse_University_2.JPG");
        locImages.put("Hinds Hall", "http://upload.wikimedia.org/wikipedia/commons/0/0b/Hinds_Hall,_Syracuse_University.JPG");

        for (String name : locImages.keySet()) {
            TextSliderView loc = new TextSliderView(this);
            loc.description(name)
                    .image(locImages.get(name));
            sliderLayout.addSlider(loc);
        }
    }

    // Populates our nav drawer view
    private void initDrawerView() {
        navDrawerList = (ListView) findViewById(R.id.tour_left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.tour_drawer_layout);
        navTitles = getResources().getStringArray(R.array.nav_array);

        // Sets adapter
        navDrawerList.setAdapter(new ArrayAdapter<String>(TourActivity.this,
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
                    Intent resources = new Intent(getApplicationContext(), ResourcesActivity.class);
                    startActivity(resources);
                    break;
                case "Contact Us":
                    Intent contactIntent = new Intent(getApplicationContext(), ContactUs.class);
                    startActivity(contactIntent);
                    break;
            }
        }
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
}
