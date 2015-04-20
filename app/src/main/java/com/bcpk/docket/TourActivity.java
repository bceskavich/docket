package com.bcpk.docket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import java.util.ArrayList;
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

    public static final String[] address = new String[]{
        "Archbold Gymnasium, Syracuse, NY 13210",
        "222 Waverly Ave, Syracuse, NY 13244",
        "222 waverly avenue, Syracuse, NY 13244",
        "900 Irving Ave, Syracuse, NY 13244",
        "950 Irving Ave, Syracuse, NY 13244",
        "Syracuse University Hall of Languages, West Zone, University Pl, Syracuse, NY 13210",
        "Hendricks Chapel West Zone Syracuse, NY 13244",
        "343 Hinds Hall, Syracuse, NY 13210",
        "107 College Pl, Syracuse, New York 13210",
        "Link Hall, Syracuse, NY 13210",
        "Whitman School of Management, 721 University Ave, Syracuse, NY 13244",
        "215 University Pl, Syracuse, NY 13244",
        "151, Waverly Ave, Syracuse, Ny",
        "303 University Pl, Syracuse, NY 13210",
        "201 Slocum Hall, Syracuse, NY 13244",
        "Hendricks Chapel West Zone Syracuse, NY 13244",
    };

    public static final String[] titles = new String[] {
        "Archbold Gymnasium",
        "Bird Library",
        "Carnegie Library",
        "Carrier Dome",
        "Dineen Hall",
        "Hall of Languages",
        "Hendricks Chapel",
        "Hinds Hall",
        "Life-Sciences Complex",
        "Link Hall",
        "Whitman School of Management",
        "Marshall Square Mall",
        "Newhouse Communications Center III",
        "Schine Student Center",
        "Slocum Hall",
        "Shaw Quadrangle",
    };

    public static final String[] descriptions = new String[] {
        "Campus gymnasium building",
        "Serving students from 1972",
        "Library with renaissance style architecture",
        "49,262-seat domed sports stadium",
        "State of the art law building",
        "Landmark building on campus",
        "Home of all religious faiths on campus",
        "Houses the School Of Information Studies",
        "Houses biology, chemistry, and biochemistry departments",
        "Home to future engineers",
        "The school of management building on campus",
        "Dedicated to broadcast and film",
        "State of the art media room",
        "Centre of student activities on campus",
        "Houses the School of Architecture",
        "Center of activity on campus, there is always something happening on the Shaw Quadrangle",
    };

    //would be used for some of the locations
    public static final String[] longdescriptions = new String[] {
        "",
        "",
        "",
        "",
        "",
        "This was the first building built on campus on 1873 with renovations to follow",
        "",
        "",
        "",
        "",
        "The Whitman School of Management Building replaced the old School of Management Building as the home of the Whitman School of Management.",
        "",
        "",
        "Designed with three levels and four wings radiating from a central atrium, the concept of the building was described as a 'reflection of the diverse intellectual, cultural, and social interests of the Syracuse University community.'",
        "",
        "The Quadrangle is an open green space designed to be accessible, safe, and attractive and to be used by members of the University community and their guests. The Quad is part of a centuries-old tradition in higher education: the provision of a peaceful, open-air area for both thoughtful contemplation and social interaction. As the center of campus, the Quad's walkways serve as the main lines for walks between classes. Once including the 'Oval', the space was previously used for football games, baseball games, and ROTC review. The Orange Grove is located adjacent to the Quad.",
    };

    public static final Integer[] images = {
        R.drawable.archbold,
        R.drawable.bird,
        R.drawable.carnegie,
        R.drawable.dome,
        R.drawable.dineen,
        R.drawable.hl,
        R.drawable.hendricks,
        R.drawable.hinds,
        R.drawable.lsc,
        R.drawable.link,
        R.drawable.whitman_som,
        R.drawable.ms_mall,
        R.drawable.newhouse3,
        R.drawable.schine,
        R.drawable.slocum,
        R.drawable.shaw,
    };

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

        // Loops thru & adds in locations to slider bundle
        ArrayList<Bundle> sliderLocations = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Bundle item = new Bundle();
            item.putString("title", titles[i]);
            item.putInt("image", images[i]);
            item.putString("description", descriptions[i]);
            item.putString("longDescription", longdescriptions[i]);
            item.putString("locationType", "localized");
            item.putString("address", address[i]);
            sliderLocations.add(item);
        }

        for (Bundle location : sliderLocations) {
            DocketTextSliderView loc = new DocketTextSliderView(this);
            loc.description(location.getString("title"))
                    .image(location.getInt("image"));
            loc.bundle(location);
            sliderLayout.addSlider(loc);
            loc.setOnSliderClickListener(new OnTourItemClickListener());
        }
    }

    private class OnTourItemClickListener implements BaseSliderView.OnSliderClickListener {
        @Override
        public void onSliderClick(BaseSliderView view) {
            // Passes bundle to an intent / starts the intent
            Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
            intent.putExtras(view.getBundle());
            startActivity(intent);
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
