package com.example.rentingcompany.NavigationDrawerFragments.RentingAgencyFragments;

import static com.example.rentingcompany.MainActivity.email;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;
import com.example.rentingcompany.Grids.RAHistoryGrid;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgencyHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgencyHistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgencyHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgencyHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgencyHistoryFragment newInstance(String param1, String param2) {
        AgencyHistoryFragment fragment = new AgencyHistoryFragment();
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
        return inflater.inflate(R.layout.fragment_agency_history, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Example::::::::::::::::::::::::::::::::::
//        DBHelper.insertProperty(new Property("XYZ", "Ramallah", 250, 2000, 5, 30000, true, "x", "4/10/2023", "Beautiful"));
//        DBHelper.insertHave("XYZ", "t@gmail.com");
//        DBHelper.insertContract("XYZ", "abd@gmail.com");


        TextView emptyTextView = (TextView) getActivity().findViewById(R.id.emptyRAHistoryTextView);
        GridView grid;
        DataBaseHelper DB = new
                DataBaseHelper(getActivity(), "EXP4", null, 1);
        ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
        Cursor cursor = DB.getAllContractDataForRentingAgency(email);
        Boolean empty = true;
        Cursor cursor2;
        while (cursor.moveToNext()) {
            empty = false;
            cursor2 = DB.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE POSTALADDRESS LIKE '" + cursor.getString(0) + "'", null);
            while (cursor2.moveToNext())
                propertiesArrayList.add(new Property(cursor2.getString(0), cursor2.getString(1), cursor2.getInt(2), cursor2.getInt(3), cursor2.getInt(4), cursor2.getDouble(5), (cursor2.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9)));
        }

        if (empty)
            emptyTextView.setVisibility(View.VISIBLE);
        else {
            emptyTextView.setVisibility(View.INVISIBLE);
            RAHistoryGrid adapter = new RAHistoryGrid(getActivity(), propertiesArrayList);
            grid = (GridView) getActivity().findViewById(R.id.rHistoryGrid);
            grid.setAdapter(adapter);
        }
    }
}