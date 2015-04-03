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
 * Created by Billy on 3/30/15.
 */

public class LocationsTabFragment extends Fragment implements AdapterView.OnItemClickListener {

    // TODO - remove test vars
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
            "Yo"
    };

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
            "this should work"
    };

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_location_list, container, false);

        rowItems = new ArrayList<Location>();
        for (int i = 0; i < titles.length; i++) {
            Location item = new Location(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) view.findViewById(R.id.list);
        LocationAdapter adapter = new LocationAdapter(getActivity(), R.layout.singlelocation_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

}
