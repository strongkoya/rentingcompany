package com.example.rentingcompany.NavigationDrawerFragments.RentingAgencyFragments;

import static com.example.rentingcompany.MainActivity.email;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rentingcompany.Grids.CustomGrid;
import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.MainActivity;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String chosenPostalAddress;
    int SurfaceArea = 100, NumOfBedrooms = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equalsIgnoreCase(value)) {
                spnr.setSelection(position);
                return;
            }
        }
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
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] cities = {"Jerusalem", "Ramallah", "Gaza", "Hebron", "Nablus", "Akka", "Bethlehem", "Oran", "Constantine", "Annaba", "Djelfa", "Biskra", "Setif", "Amman", "Zarqa", "Irbid", "Russeifa", "Wadi as-Ser", "Madaba", "al-Baq'a", "Sahab", "Doha", "Abu az Zuluf", "Abu Thaylah", "Al Ghanim", "Al Ghuwariyah", "Al `Arish", "Aleppo", "Damascus", "Homs", "Latakia", "Hama", "Qamishli", "Tartus", "Beirut", "Tripoli", "Sidon", "Zahle", "Batroun", "Tyre"};
        Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.citySpinner);
        ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, cities);
        citySpinner.setAdapter(arrayAdapter_child);


        EditText updateTextPhotoURL = (EditText) getActivity().findViewById(R.id.updateTextPhotoURL);
        EditText updateTextConstructionYear = (EditText) getActivity().findViewById(R.id.updateTextConstructionYear);
        EditText updateTextRentalPrice = (EditText) getActivity().findViewById(R.id.updateTextRentalPrice);
        EditText updateTextAvailabilityDate = (EditText) getActivity().findViewById(R.id.updateTextAvailabilityDate);
        MultiAutoCompleteTextView updateDescriptionTextView = (MultiAutoCompleteTextView) getActivity().findViewById(R.id.updateDescriptionTextView);
        SeekBar updateSurfaceAreaSeekBar = (SeekBar) getActivity().findViewById(R.id.updateSurfaceAreaSeekBar);
        SeekBar updateNumOfBedroomsSeekBar = (SeekBar) getActivity().findViewById(R.id.updateNumOfBedroomsSeekBar);
        TextView updatesurfaceareaseek_textView = (TextView) getActivity().findViewById(R.id.updatesurfaceareaseek_textView);
        TextView updatebedroomsseek_textView = (TextView) getActivity().findViewById(R.id.updatebedroomsseek_textView);
        RadioButton furnishedRadioButton = (RadioButton) getActivity().findViewById(R.id.updateFurnished_radioButton);
        RadioButton unfurnishedRadioButton = (RadioButton) getActivity().findViewById(R.id.updateUnfurnished_radioButton);
        Button saveButton = (Button) getActivity().findViewById(R.id.update_button);
        GridView grid;
        Boolean empty = true;
        ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
        DataBaseHelper dataBaseHelper = new
                DataBaseHelper(getActivity(), "EXP4", null, 1);
        Cursor allHaveCursor = dataBaseHelper.getAllHaveData(email);
        DataBaseHelper DBHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
        while (allHaveCursor.moveToNext()) {
            empty = false;
            Cursor cursor = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE POSTALADDRESS LIKE '" + allHaveCursor.getString(0) + "'", null);
            while (cursor.moveToNext())
                propertiesArrayList.add(new Property(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5), (cursor.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor.getString(7), cursor.getString(8), cursor.getString(9)));
        }

        TextView editPropertyTextView = (TextView) getActivity().findViewById(R.id.editPropertyTextView);
        if (empty) {
            editPropertyTextView.setText("No Property to Edit!");
        } else {
            editPropertyTextView.setText("Click to Edit");
            CustomGrid adapter = new CustomGrid(getActivity(), propertiesArrayList);
            grid = (GridView) getActivity().findViewById(R.id.grid2);
            grid.setAdapter(adapter);


            //editPropertyTextView
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    LinearLayout updateLayout = (LinearLayout) getActivity().findViewById(R.id.updateLinearLayout);
                    grid.setVisibility(View.INVISIBLE);
                    updateLayout.setVisibility(View.VISIBLE);
                    chosenPostalAddress = "";

                    Cursor allHaveCursor2 = dataBaseHelper.getAllHaveData(email);
                    int count = 0;
                    while (allHaveCursor2.moveToNext() && count != position)
                        count++;
                    Cursor crs = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE POSTALADDRESS LIKE '" + allHaveCursor2.getString(0) + "'", null);


                    if (crs.moveToNext()) {
                        chosenPostalAddress = crs.getString(0);
                        selectSpinnerItemByValue(citySpinner, crs.getString(1));

                        SurfaceArea = crs.getInt(2);
                        updateSurfaceAreaSeekBar.setProgress((int) Math.ceil((SurfaceArea - 100) / 2.7));
                        updatesurfaceareaseek_textView.setText(SurfaceArea + " m\u00B2");

                        updateTextConstructionYear.setText(crs.getString(3));

                        NumOfBedrooms = crs.getInt(4);
                        updateNumOfBedroomsSeekBar.setProgress((int) Math.ceil((NumOfBedrooms - 1) / 0.09));
                        updatebedroomsseek_textView.setText(NumOfBedrooms + " Bedroom(s)");

                        updateTextRentalPrice.setText(crs.getString(5));

                        if (crs.getString(6).equalsIgnoreCase("TRUE"))
                            furnishedRadioButton.setChecked(true);
                        else
                            unfurnishedRadioButton.setChecked(true);

                        updateTextPhotoURL.setText(crs.getString(7));
                        updateTextAvailabilityDate.setText(crs.getString(8));
                        updateDescriptionTextView.setText(crs.getString(9));


                    }


                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String TOASTMSG = "";
                            if (updateTextConstructionYear.getText().toString().isEmpty() || updateTextRentalPrice.getText().toString().isEmpty() || updateTextAvailabilityDate.getText().toString().isEmpty() || updateTextPhotoURL.getText().toString().isEmpty()) {
                                TOASTMSG = "Complete The Fields!";

                            } else if (!MainActivity.validateJavaDate(updateTextAvailabilityDate.getText().toString())) {
                                TOASTMSG = "Wrong Date!";
                            } else {

                                Property newProperty = new Property();
                                newProperty.setSurfaceArea(SurfaceArea);
                                newProperty.setNumOfBedrooms(NumOfBedrooms);
                                newProperty.setDescryption(updateDescriptionTextView.getText().toString());
                                newProperty.setAvailabilityDate(updateTextAvailabilityDate.getText().toString());
                                newProperty.setPhotoURL(updateTextPhotoURL.getText().toString());
                                newProperty.setRentalPrice(Double.valueOf(updateTextRentalPrice.getText().toString()));
                                newProperty.setConstructionYear(Integer.valueOf(updateTextConstructionYear.getText().toString()));
                                newProperty.setCity(citySpinner.getSelectedItem().toString());
                                newProperty.setFurnished((furnishedRadioButton.isChecked() ? true : false));

                                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                                dataBaseHelper.updateProperty(newProperty, chosenPostalAddress);

                                TOASTMSG = "The Property has been updated Correctly!";

                            }
                            Toast toast = Toast.makeText(getActivity(), TOASTMSG, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });


                }
            });
        }

        updateSurfaceAreaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SurfaceArea = (int) (100 + (i * 2.7));
                updatesurfaceareaseek_textView.setText(SurfaceArea + " m\u00B2");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        updateNumOfBedroomsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                NumOfBedrooms = (int) (1 + (i * 0.09));
                updatebedroomsseek_textView.setText(NumOfBedrooms + " Bedroom(s)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button deleteButton = (Button) getActivity().findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                Cursor cursor = dataBaseHelper.getPropertyData();
                Boolean found = false;
                while (cursor.moveToNext())
                    if (cursor.getString(0).equalsIgnoreCase(chosenPostalAddress)) {
                        dataBaseHelper.deleteProperty(chosenPostalAddress);
                        found = true;
                        break;
                    }
                Toast toast;
                if (found)
                    toast = Toast.makeText(getActivity(), "The Property has been deleted Correctly!", Toast.LENGTH_SHORT);
                else
                    toast = Toast.makeText(getActivity(), "Already Deleted!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });

    }
}