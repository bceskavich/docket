package com.bcpk.docket;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


//A single resource activity. Starts when a Resource from ResourceTabFragment is clicked.
public class SingleResource extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_resource);
        ImageView image;
        TextView description;
        TextView title;

        title = (TextView) findViewById(R.id.resourceTitle);
        description= (TextView) findViewById(R.id.resourceDescription);
        image= (ImageView) findViewById(R.id.resourceImage);


        Log.d("whatTitle", "" + getIntent().getExtras().getString(ResourcesActivity.ID_TITLE) +
                getIntent().getExtras().getString(ResourcesActivity.ID_DESC));


        title.setText(getIntent().getExtras().getString(""+ResourcesActivity.ID_TITLE));
        description.setText(getIntent().getExtras().getString("" + ResourcesActivity.ID_DESC));

                image.setImageResource(getIntent().getExtras().getInt(ResourcesActivity.ID_IMG));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_resource, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
