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


    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";
    public final static String ID_WEBADD = "com.bcpk.docket._ID5";

    public static final String[] titles = new String[] {
            "NEXIS",
            "IDEA",
            "Makerspace",
            "Syracuse University Student SandBox",
            "Career services",
            "Student Legal Services",
            "Writing center",
            "Know a campus resources you think we should add to this list?",
    };

    public static final String[] descriptions = new String[] {
            "NEXIS is a student based, membership driven research lab based in the Syracuse University iSchool",
            "IDEA's mission is to be a catalyst for a thriving entrepreneurship ecosystem on the SU campus. They connect students with ideas to the resources they need to move ideas forward.",
            "A MakerSpace is a collaborative space to imagine, design, build, tinker, modify, hack, teach, and learn",
            "An excellent opportunity for students who want to start their own businesses",
            "Syracuse University Career Services, located in Suite 235 of the Schine Student Center, is an all-university office serving undergraduate, graduate, and PhD students, as well as alumni",
            "Have been helping students with their legal dilemmas since 1972",
            "The primary aim of the Writing Center is to help you become a stronger, more accomplished writer",
            "Email us at prasanna.k158@gmail.com",
};

    public static final Integer[] images = {
            R.drawable.nexis,
            R.drawable.idea,
            R.drawable.ic_launcher,
            R.drawable.studentsandbox,
            R.drawable.ic_launcher,
            R.drawable.studentlegal,
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,


    };

    public static final String[] websites = new String[]{
            "http://nexis.ischool.syr.edu/",
            "http://makerspace.syr.edu/",
            "http://syracusestudentsandbox.syr.edu/",
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
            Location item = new Location(images[i], titles[i], descriptions[i], websites[i], "", "none");
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
        String passableWebAddress = rowItems.get(position).getWebAdd();
        Log.d("ImgID", "This is the imageID" + passableImage);
        Log.d("WEBADD", "This is the imageID" + passableImage);

        Intent goToResource;
        goToResource = new Intent(getActivity(), SingleResource.class);
        //declaring extras with the intent
        goToResource.putExtra(ID_WEBADD, passableWebAddress);
        goToResource.putExtra(ID_TITLE, passableTitle);
        goToResource.putExtra(ID_IMG, passableImage);
        goToResource.putExtra(ID_DESC, passableDesc);

        startActivity(goToResource);



    }
}
