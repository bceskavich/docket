package com.bcpk.docket;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 4/7/15.
 */
public class ResourcesTabFragment extends Fragment implements AdapterView.OnItemClickListener {

    public final static String ID_EXTRA = "com.bcpk.docket._ID1";
    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";

    public static final String[] titles = new String[] {
            "NEXIS",
            "Makerspace",
            "Career services",
            "Writing center",
            "Student Legal Services",
            "IDEA",
            "All the clubs and organizations on campus",
            "Know resources you think we should add to this list?",
    };

    public static final String[] descriptions = new String[] {
            "NEXIS is a student based, membership driven research lab based in the Syracuse University iSchool",
            "A MakerSpace is a collaborative space to imagine, design, build, tinker, modify, hack, teach, and learn",
            "Syracuse University Career Services, located in Suite 235 of the Schine Student Center, is an all-university office serving undergraduate, graduate, and PhD students, as well as alumni",
            "The primary aim of the Writing Center is to help you become a stronger, more accomplished writer",
            "Have been helping students with their legal dilemmas since 1972",
            "IDEA's mission is to be a catalyst for a thriving entrepreneurship ecosystem on the SU campus. They connect students with ideas to the resources they need to move ideas forward.",
            "",
            "Please contact us for a helpful university resource that you would like us to add to the list",
};

    public static final Integer[] images = {
            R.drawable.nexis,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.studentlegal,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,

    };

    public static final String[] websites = new String[]{
            "http://nexis.ischool.syr.edu/",
            "http://makerspace.syr.edu/",
            "http://careerservices.syr.edu/aboutus/",
            "http://wc.syr.edu/",
            "http://www.studentlegal.net/",
            "http://idea.syr.edu/",
            "http://syr.orgsync.com/studentorganizations#",
            "Send us an email",
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
            Location item = new Location(images[i], titles[i], descriptions[i], "", "none");
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

        //defining the data to be passed with intent
        String passableDesc = rowItems.get(position).getDesc();
        String passableTitle = rowItems.get(position).getTitle();
        int passableImage = rowItems.get(position).getImageId();
        Log.d("ImgID", "This is the imageID" + passableImage);

        Intent goToResource;
        goToResource = new Intent(getActivity(), SingleResource.class);
        //declaring extras with the intent
        goToResource.putExtra(ID_TITLE, passableTitle);
        goToResource.putExtra(ID_IMG, passableImage);
        goToResource.putExtra(ID_DESC, passableDesc);

        startActivity(goToResource);



    }
}
