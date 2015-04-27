package com.bcpk.docket;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcpk.docket.R;

import org.w3c.dom.Text;

// Only activity for contact us screen for the app

public class ContactUs extends ActionBarActivity {

    // Nav menu vars
    private Toolbar toolbar;
    private DrawerLayout navDrawerLayout;
    private ListView navDrawerList;
    private ActionBarDrawerToggle navDrawerToggle;
    private ArrayAdapter<String> navArrayAdapter;
    private String[] navTitles;
    private String navTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        // Creates drawer view
        initDrawerView();
        if (toolbar != null) {
            toolbar.setTitle("Contact Docket");
            setSupportActionBar(toolbar);
        }

        // Creates the drawer nav itself
        initDrawer();

        final EditText personName;
        final EditText message;
        Button sendEmailButton;

        personName = (EditText) findViewById(R.id.editText);
        message = (EditText) findViewById(R.id.editText2);
        sendEmailButton = (Button) findViewById(R.id.sendEmailButton);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                String[] recipients = new String[]{"pkulka02@syr.edu", "",};
                String personalMessage = message.getText().toString();
                String name = personName.getText().toString();
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Feedback/Suggestion for Docket!");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, personalMessage);
                emailIntent.setType("message/rfc822");
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
                //sendEmail();
            }
        });
    }

    // Populates our nav drawer view
    private void initDrawerView() {
        navDrawerList = (ListView) findViewById(R.id.contact_left_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.contact_drawer_layout);
        navTitles = getResources().getStringArray(R.array.nav_array);

        // Sets adapter
        navDrawerList.setAdapter(new ArrayAdapter<String>(ContactUs.this,
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
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        navDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_us, menu);
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
