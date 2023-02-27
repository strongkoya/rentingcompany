package com.example.rentingcompany.NavigationDrawerFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rentingcompany.Grids.CustomGrid;
import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridView grid;


        DataBaseHelper DBHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);

//        DBHelper.insertProperty(new Property("Beitonia", "Ramallah", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("X", "Jenin", 250, 2000, 5, 30000, false, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("Y", "Tulkarm", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("Z", "Qalqilyah", 250, 2000, 5, 30000, false, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("A", "Nablus", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("B", "Jericho", 250, 2000, 5, 30000, false, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("C", "Haifa", 250, 2000, 5, 30000, false, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("G", "Akka", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//        DBHelper.insertProperty(new Property("tareq", "Jerusalem", 444, 44444, 4, 444, true, "x", "2010-10-4", "Nice"));
//        DBHelper.insertProperty(new Property("Zaid", "akka", 244, 44444, 4, 244, true, "x", "2010-10-4", "Nice"));

//        DBHelper.insertHave("C", email);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
        Cursor cursor = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE PROPERTY.POSTALADDRESS NOT IN ( SELECT CONTRACT.POSTALADDRESS FROM CONTRACT ) ORDER BY POSTDATE DESC", null);
        int count = 0;
        while (cursor.moveToNext() & count!=5) {

            Date firstDate = null;
            try {
                firstDate = sdf.parse(cursor.getString(10));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date secondDate = new Date(System.currentTimeMillis());
            long diff = secondDate.getTime() - firstDate.getTime();
            long days = (diff / 1000 / 60 / 60 / 24);
            if(days > 90){
                DBHelper.deleteProperty(cursor.getString(0));
                continue;
            }

            propertiesArrayList.add(new Property(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5), (cursor.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
            count++;
        }


        CustomGrid adapter = new CustomGrid(getActivity(), propertiesArrayList);
        grid = (GridView) getActivity().findViewById(R.id.grid);
        grid.setAdapter(adapter);
//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(getActivity(), "You Clicked at ..", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}