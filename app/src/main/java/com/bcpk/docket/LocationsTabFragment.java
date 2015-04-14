package com.bcpk.docket;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Billy on 3/30/15.
 */

public class LocationsTabFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String[] address = new String[]{
            "Archbold Gymnasium, Syracuse, NY 13210",
            "222 Waverly Ave, Syracuse, NY 13244",
            "Bowne Hall, 900 S Crouse Ave, Syracuse, NY 13210",
            "401 Van Buren St, Syracuse, NY 13210",
            "222 waverly avenue, Syracuse, NY 13244",
            "900 Irving Ave, Syracuse, NY 13244",
            "Crouse-Hinds Hall, Syracuse University, Wavely Avenue, Syracuse, NY 13210",
            "950 Irving Ave, Syracuse, NY 13244",
            "Ernie Davis Hall, Syracuse University, Syracuse, NY, 13210",
            "Archbold Gymnasium, Syracuse, NY 13210",
            "401 University Pl, Syracuse, NY 13210",
            "401 Skytop Rd, Syracuse, NY 13210",
            "Syracuse University Hall of Languages, West Zone, University Pl, Syracuse, NY 13210",
            "Hendricks Chapel West Zone Syracuse, NY 13244",
            "343 Hinds Hall, Syracuse, NY 13210",
            "The Inn Complete, Syracuse, NY 13210",
            "107 College Pl, Syracuse, New York 13210",
            "Link Hall, Syracuse, NY 13210",
            "Whitman School of Management, 721 University Ave, Syracuse, NY 13244",
            "720 University Ave Syracuse, NY 13210",
            "215 University Pl, Syracuse, NY 13244",
            "151, Waverly Ave, Syracuse, Ny",
            "Physics Bldg West Zone Crouse Dr Syracuse, NY 13210",
            "303 University Pl, Syracuse, NY 13210",
            "801 University Ave, Syracuse, NY 13210",
            "201 Slocum Hall, Syracuse, NY 13244",
            "310 Walnut Pl, Syracuse, NY 13210",
            "Steele Hall, West Zone, Syracuse University, Syracuse, NY 13210",
            "511 Skytop Rd, Syracuse, NY 13244",
            "350 W Fayette St, Syracuse, NY",
            "Hendricks Chapel West Zone Syracuse, NY 13244",


    };

    public static final String[] titles = new String[] {
            "Archbold Gymnasium",
            "Bird Library",
            "Bowne Hall",
            "Brewster- Boland - Brockway",
            "Carnegie Library",
            "Carrier Dome",
            "Crouse-Hinds Hall",
            "Dineen Hall",
            "Ernie Davis Hall",
            "Flanagan Gymnasium",
            "Goldstein Alumni and Faculty Center",
            "Goldstein Student Center",
            "Hall of Languages",
            "Hendricks Chapel",
            "Hinds Hall",
            "Inn Complete (Ski Lodge)",
            "Life-Sciences Complex",
            "Link Hall",
            "Whitman School of Management",
            "Marshall Square Mall",
            "Newhouse Communications Center II",
            "Newhouse Communications Center III",
            "Physics Building",
            "Schine Student Center",
            "Sheraton Syracuse University Hotel and Conference Center",
            "Slocum Hall",
            "Slutzker Center",
            "Steele Hall",
            "Tennity Ice Skating Pavilion",
            "Warehouse",
            "Shaw Quadrangle",
    };

    public static final String[] descriptions = new String[] {
            "Campus gymnasium building",
            "Serving students from 1972",
            "Chemistry building",
            "Residence hall on campus",
            "Library with renaissance style architecture",
            "49,262-seat domed sports stadium",
            "Home to offices",
            "State of the art law building",
            "Recidence hall, dining hall and gymnasium",
            "Flanagan is accessible only through Archbold Gym via the glass-enclosed bridge connecting the two structures.",
            "The Goldstein Alumni and Faculty Center(GAFC) is a 3-story red brick building which has a Colonial Georgianarchitecture",
            "Student centre on South Campus",
            "Landmark building on campus",
            "Home of all religious faiths on campus",
            "Houses the School Of Information Studies",
            "A pub, restaurant, games, and entertainment/meeting space",
            "Houses biology, chemistry, and biochemistry departments",
            "Home to future engineers",
            "The school of management building on campus",
            "Fun, food, coffee, shopping on campus",
            "Dedicated to broadcast and film",
            "State of the art media room",
            "The name speaks for itself",
            "Centre of student activities on campus",
            "Hotel Sheraton on campus",
            "Houses the School of Architecture",
            "Houses the Slutzker Center for International Services",
            "Administrative building",
            "It's ice skating time!",
            "This building is not on the main campus",
            "Center of activity on campus, there is always something happening on the Shaw Quadrangle",
    };

    //would be used for some of the locations
    public static final String[] longdescriptions = new String[] {
        "",
        "",
        "",
        "",
        "",
        "",
        "home to offices such as Academic Affairs, Admissions, the Chancellor's Office, and the Provost's Office.",
        "",
        "",
        "",
        "The building now serves as an Alumni Center where students, faculty and alumni may gather. In addition to this, the GAFC now serves as a restaurant as well.",
        "Built to serve students living on South Campus, its main floor contains administrative offices, lounge, meeting room, and a satellite branch of the University Bookstore. The lower level of the building has a dining area, laundry, and game room, as well as a health clinic and fitness center.",
        "This was the first building built on campus on 1873 with renovations to follow",
        "",
        "",
        "",
        "",
        "",
        "The Whitman School of Management Building replaced the old School of Management Building as the home of the Whitman School of Management.",
        "",
        "",
        "",
        "Old School of Journalism building demolished to make room for this building",
        "Designed with three levels and four wings radiating from a central atrium, the concept of the building was described as a 'reflection of the diverse intellectual, cultural, and social interests of the Syracuse University community.'",
        "",
        "",
        "The Slutzker Center for International Services (SCIS) offers supportive services and programming to international students and their families. It serves as the official liaison office with the U.S. Immigration Service for all non-immigrants at Syracuse University and SUNY ESF.",
        "",
        "",
        "A former Dunk & Bright Furniture warehouse, the facility was renovated to include community spaces for art education, artists and community use.",
        "The Quadrangle is an open green space designed to be accessible, safe, and attractive and to be used by members of the University community and their guests. The Quad is part of a centuries-old tradition in higher education: the provision of a peaceful, open-air area for both thoughtful contemplation and social interaction. As the center of campus, the Quad's walkways serve as the main lines for walks between classes. Once including the 'Oval', the space was previously used for football games, baseball games, and ROTC review. The Orange Grove is located adjacent to the Quad.",
    };

    public static final Integer[] images = {

            R.drawable.archbold,
            R.drawable.bird,
            R.drawable.bowne,
            R.drawable.bbb,
            R.drawable.carnegie,
            R.drawable.dome,
            R.drawable.crouse_hinds,
            R.drawable.dineen,
            R.drawable.ernie_davis_hall,
            R.drawable.flanagan,
            R.drawable.goldstein_alumni,
            R.drawable.goldstein_student,
            R.drawable.hl,
            R.drawable.hendricks,
            R.drawable.hinds,
            R.drawable.inn_complete,
            R.drawable.lsc,
            R.drawable.link,
            R.drawable.whitman_som,
            R.drawable.ms_mall,
            R.drawable.newhouse2,
            R.drawable.newhouse3,
            R.drawable.physics,
            R.drawable.schine,
            R.drawable.sheraton,
            R.drawable.slocum,
            R.drawable.slutzker,
            R.drawable.steele,
            R.drawable.tennity,
            R.drawable.warehouse,
            R.drawable.quad,
            R.drawable.shaw,

    };



    ListView listView;
    List<Location> rowItems;

    // For logging
    private final String TAG = "LocationsTabFragment";

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
        /*
        Toast toast = Toast.makeText(getActivity(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        */

        // Grabs location detail and on click and adds to a bundle
        Location item = rowItems.get(position);

        Bundle locBundle = new Bundle();
        locBundle.putString("title", item.getTitle());
        locBundle.putString("description", item.getDesc());
        locBundle.putString("locationType", "localized");
        locBundle.putString("image", "id");

        // Passes bundle to an intent / starts the intent
        Intent intent = new Intent(getActivity(), LocationActivity.class);
        intent.putExtras(locBundle);
        startActivity(intent);

    }

}
