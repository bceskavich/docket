package com.bcpk.docket;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 4/7/15.
 */

public class OrganizationsTabFragment extends Fragment implements AdapterView.OnItemClickListener {
    public final static String ID_EXTRA = "com.bcpk.docket._ID1";
    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";

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

    // For logging
    private final String TAG = "ResourcesTabFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_location_list, container, false);

        rowItems = new ArrayList<Location>();
        for (int i = 0; i < titles.length; i++) {
            Location item = new Location(images[i], titles[i], descriptions[i], "none");
            rowItems.add(item);
        }

        listView = (ListView) view.findViewById(R.id.list);
        LocationAdapter adapter = new LocationAdapter(getActivity(),
                R.layout.singlelocation_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        /*
        String passableDesc = rowItems.get(position).getDesc();
        String passableTitle = rowItems.get(position).getTitle();
        int passableImage = rowItems.get(position).getImageId();
        Log.d("ImgID", "This is the imageID"+passableImage);

        Intent goToResource = new Intent(ResourcesActivity.this, SingleResource.class);

        goToResource.putExtra(ID_TITLE, passableTitle);
        goToResource.putExtra(ID_IMG, passableImage);
        goToResource.putExtra(ID_DESC, passableDesc);

        startActivity(goToResource);
        */

        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
