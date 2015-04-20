package com.bcpk.docket;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Billy on 4/7/15.
 */

public class TipsTabFragment2 extends Fragment implements AdapterView.OnItemClickListener {
    public final static String ID_EXTRA = "com.bcpk.docket._ID1";
    public final static String ID_TITLE = "com.bcpk.docket._ID2";
    public final static String ID_DESC = "com.bcpk.docket._ID3";
    public final static String ID_IMG = "com.bcpk.docket._ID4";


    public static final String[] descriptions = new String[] {
            "Select good courses and take full advantage of all the services available on campus.",
            "Learn to love the snow. Try skiing, ice skating",
            "Don't blindly follow the crowd and be afraid to try new things.",
            "Plan your academic life so that you don't rush at the last moment.",
            "Have plenty of time in your schedule for rest and recreation.",
            "Visit the New York State fair for amazing local cultural experience.",
            "Utilize vacation time well. thanksgiving in fall semester and spring break in spring semester. Provide recommendations from seniors",
            "Should make proper planning for the basic stuffs before coming to syracuse",
            "You should learn driving so that you can book a zipcar and explore nearby places and hangout with friends on weekends",
            "Relax and enjoy the summer days as much as possible.",
            "Tell them what all arr hot places on the campus..and if there is any event going on.",
            "Pay attention to socializing to get useful information as much as possible",
            "Get warm clothes",
            "Be yourself and don't be afraid to out yourself out there. Everyone's in the same boat as you.",
            "Try to just soak it all in and enjoy your experience, it's going to go by very quick.",
            "Join clubs",
            "Check out Orange after dark events",
            "You need warm clothes, which you will get here." };



    ListView listView;
    List<Tip> tipItems;

    // For logging
    private final String TAG = "ResourcesTabFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.tab_location_list, container, false);





        tipItems = new ArrayList<Tip>();
        for (int i = 0; i < descriptions.length; i++) {
            Tip item = new Tip(descriptions[i]);
            tipItems.add(item);
        }

        listView = (ListView) view.findViewById(R.id.list);
        TipsAdapter adapter = new TipsAdapter(getActivity(),
                R.layout.singletip_item, tipItems);
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


    }
}
