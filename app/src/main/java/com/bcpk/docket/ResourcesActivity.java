package com.bcpk.docket;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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


public class ResourcesActivity extends ActionBarActivity {



    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";
    public final static String ID_WEBADD = "com.bcpk.docket._ID5";

    // Nav menu vars
    private Toolbar toolbar;
    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle navDrawerToggle;
    private ArrayAdapter<String> navArrayAdapter;
    private String[] navTitles;
    private String navTitle;

    // Tab vars
    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;
    private SlidingTabLayout tabLayout;
    private final CharSequence tabTitles[] = {"Resources", "Tips"};
    private final int numTabs = 2;

    // For logging
    private final String TAG = "ResourcesActivity";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        // Creates drawer view
        initDrawerView();
        if (toolbar != null) {
            toolbar.setTitle(getResources().getString(R.string.nav_name));
            setSupportActionBar(toolbar);
        }

        // Creates the drawer nav itself
        initDrawer();

        // Creates the ViewPagerAdapter and ViewPager for the tabs
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabTitles, numTabs,
                TAG);
        pager = (ViewPager) findViewById(R.id.resources_pager);
        pager.setAdapter(pagerAdapter);

        // Creates tabs and colors them
        tabLayout = (SlidingTabLayout) findViewById(R.id.resources_tabs);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.orangeDark);
            }
        });

        tabLayout.setViewPager(pager);
    }

    // Populates our nav drawer view
    private void initDrawerView() {
        navDrawerList = (ListView) findViewById(R.id.resources_left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.resources_drawer_layout);
        navTitles = getResources().getStringArray(R.array.nav_array);

        // Sets adapter
        navDrawerList.setAdapter(new ArrayAdapter<>(ResourcesActivity.this,
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
                    Intent tour = new Intent(getApplicationContext(), TourActivity.class);
                    startActivity(tour);
                    break;
                case "Resources":
                    break;
                case "Contact Us":
                    Intent contactIntent = new Intent(getApplicationContext(), ContactUs.class);
                    startActivity(contactIntent);
                    break;
            }
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

}