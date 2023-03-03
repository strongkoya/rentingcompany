package com.example.rentingcompany.NavigationDrawerFragments.TenantFragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Grids.CustomGrid;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import me.bendik.simplerangeview.SimpleRangeView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int lowerLimitSurfaceArea = 100, upperLimitSurfaceArea = 370, lowerLimitNumOfBedrooms = 1, upperLimitNumOfBedrooms = 10;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayout firstLayout = (LinearLayout) getActivity().findViewById(R.id.searchLayout);
        LinearLayout secondLayout = (LinearLayout) getActivity().findViewById(R.id.resultLayout);
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.INVISIBLE);
        EditText minRentalPrice = (EditText) getActivity().findViewById(R.id.editTextRentalPrice);
        RadioButton furnishedRadioButton = (RadioButton) getActivity().findViewById(R.id.furnished_radioButton);
//        RadioButton unfurnishedRadioButton = (RadioButton) getActivity().findViewById(R.id.unfurnished_radioButton);

        String[] cities = {"Tunis", "Sfax", "Tozeur", " Ben Arous", "Jendouba", "Nabeul", "Beja",
                "Paris", "Marseille", "Toulouse", "Nantes","Montpellier", "Bordeaux", "Lille", "Rennes",
                "Rome", "Milan", "Florence", "venise", "Naples", "Bologne"};


        Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.spinner1);
        ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, cities);
        citySpinner.setAdapter(arrayAdapter_child);

        TextView surfaceAreaRangeTextView = (TextView) getActivity().findViewById(R.id.surfacearearange_textView);
        SimpleRangeView rangeView = getActivity().findViewById(R.id.simpleRangeView);
        rangeView.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int lowerLimit, int upperLimit) {
                lowerLimitSurfaceArea = lowerLimit * 30 + 100;
                upperLimitSurfaceArea = upperLimit * 30 + 100;
                surfaceAreaRangeTextView.setText(lowerLimitSurfaceArea + " - " + upperLimitSurfaceArea + " Square Meters");
            }
        });

        TextView bedroomsRangeTextView = (TextView) getActivity().findViewById(R.id.bedroomsrange_textView);
        SimpleRangeView range2View = getActivity().findViewById(R.id.simpleRangeView2);
        range2View.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener() {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int lowerLimit, int upperLimit) {
                lowerLimitNumOfBedrooms = lowerLimit + 1;
                upperLimitNumOfBedrooms = upperLimit + 1;
                bedroomsRangeTextView.setText(lowerLimitNumOfBedrooms + " - " + upperLimitNumOfBedrooms + " Bedrooms");
            }
        });

        Button searchButton = (Button) getActivity().findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DataBaseHelper DBHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
                Cursor cursor = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE CITY LIKE '" + citySpinner.getSelectedItem().toString() + "' AND SURFACEAREA >= " + lowerLimitSurfaceArea + " AND SURFACEAREA <= " + upperLimitSurfaceArea + " AND NUMOFBEDROOMS >= " + lowerLimitNumOfBedrooms + " AND NUMOFBEDROOMS <= " + upperLimitNumOfBedrooms + " AND RENTALPRICE >= " + (minRentalPrice.getText().toString().isEmpty() ? "RENTALPRICE" : minRentalPrice.getText().toString()) + " AND ISFURNISHED = '" + (furnishedRadioButton.isChecked() ? "TRUE" : "FALSE") + "'", null);
                Boolean flag = false;
                while (cursor.moveToNext()) {
                    flag = true;
                    propertiesArrayList.add(new Property(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5), (cursor.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
                }
                if (!flag) {
                    Toast toast = Toast.makeText(getActivity(), "There is No Property with these Specifications to Show!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    GridView grid;
                    firstLayout.setVisibility(View.INVISIBLE);
                    secondLayout.setVisibility(View.VISIBLE);
                    CustomGrid adapter = new CustomGrid(getActivity(), propertiesArrayList);
                    grid = (GridView) getActivity().findViewById(R.id.resultGrid);
                    grid.setAdapter(adapter);
//                    grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view,
//                                                int position, long id) {
//                            Toast.makeText(getActivity(), "You Clicked at ..", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
                }
//                propertiesArrayList.add(new Property("T", "Tunis", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("S", "Sfax", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("B", "Beja", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("P", "Paris", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("T", "Toulouse", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("N", "Nantes", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));
//                propertiesArrayList.add(new Property("R", "Rome", 250, 2000, 5, 30000, true, "x", "2010-10-4", "Beautiful"));

            }
        });
    }
}