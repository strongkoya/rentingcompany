package com.example.rentingcompany.NavigationDrawerFragments.RentingAgencyFragments;

import static com.example.rentingcompany.MainActivity.email;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.MainActivity;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> propertyArrayList = new ArrayList();
    int SurfaceArea = 100, NumOfBedrooms = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] cities = {"Jerusalem", "Ramallah", "Gaza", "Hebron", "Nablus", "Akka", "Bethlehem", "Oran", "Constantine", "Annaba", "Djelfa", "Biskra", "Setif", "Amman", "Zarqa", "Irbid", "Russeifa", "Wadi as-Ser", "Madaba", "al-Baq'a", "Sahab", "Doha", "Abu az Zuluf", "Abu Thaylah", "Al Ghanim", "Al Ghuwariyah", "Al `Arish", "Aleppo", "Damascus", "Homs", "Latakia", "Hama", "Qamishli", "Tartus", "Beirut", "Tripoli", "Sidon", "Zahle", "Batroun", "Tyre"};
        Spinner citySpinner = (Spinner) getActivity().findViewById(R.id.spinner2);
        ArrayAdapter<String> arrayAdapter_child = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, cities);
        citySpinner.setAdapter(arrayAdapter_child);

        TextView surfaceAreaSeekTextView = (TextView) getActivity().findViewById(R.id.surfaceareaseek_textView);
        TextView numOfBedroomsSeekTextView = (TextView) getActivity().findViewById(R.id.bedroomsseek_textView);
        EditText PostalAddress_editText = (EditText) getActivity().findViewById(R.id.editTextPostalAddress);
        EditText ConstructionYear_editText = (EditText) getActivity().findViewById(R.id.editTextConstructionYear);
        EditText RentalPrice_editText = (EditText) getActivity().findViewById(R.id.editTextRentalPrice1);
        EditText AvailabilityDate_editText = (EditText) getActivity().findViewById(R.id.editTextAvailabilityDate);
        EditText PhotoURL_editText = (EditText) getActivity().findViewById(R.id.editTextPhotoURL);
        SeekBar SurfaceAreaSeekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
        SeekBar NumOfBedroomsSeekBar = (SeekBar) getActivity().findViewById(R.id.seekBar2);
        RadioButton furnishedRadioButton = (RadioButton) getActivity().findViewById(R.id.furnished_radioButton2);
        MultiAutoCompleteTextView DescriptionText = (MultiAutoCompleteTextView) getActivity().findViewById(R.id.multiAutoCompleteTextView);
        Button addButton = (Button) getActivity().findViewById(R.id.add_button);

        SurfaceAreaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SurfaceArea = (int) (100 + (i * 2.7));
                surfaceAreaSeekTextView.setText(SurfaceArea + " m\u00B2");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        NumOfBedroomsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                NumOfBedrooms = (int) (1 + (i * 0.09));
                numOfBedroomsSeekTextView.setText(NumOfBedrooms + " Bedroom(s)");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TOASTMSG = "";
                if (PostalAddress_editText.getText().toString().isEmpty() || ConstructionYear_editText.getText().toString().isEmpty() || RentalPrice_editText.getText().toString().isEmpty() || AvailabilityDate_editText.getText().toString().isEmpty() || PhotoURL_editText.getText().toString().isEmpty()) {
                    TOASTMSG = "Complete The Fields!";

                } else if (!MainActivity.validateJavaDate(AvailabilityDate_editText.getText().toString())) {
                    TOASTMSG = "Wrong Date!";
                } else {

                    DataBaseHelper dBHelper = new
                            DataBaseHelper(getActivity(), "EXP4", null, 1);
                    Cursor cursor = dBHelper.getPropertyData();

                    while (cursor.moveToNext())
                        propertyArrayList.add(cursor.getString(0));

                    if (propertyArrayList.contains(PostalAddress_editText.getText().toString()))
                        TOASTMSG = "There is Something Wrong!!";
                    else {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                        dataBaseHelper.insertProperty(new Property(PostalAddress_editText.getText().toString(), citySpinner.getSelectedItem().toString(), SurfaceArea, Integer.valueOf(ConstructionYear_editText.getText().toString()), NumOfBedrooms, Double.valueOf(RentalPrice_editText.getText().toString()), (furnishedRadioButton.isChecked() ? true : false), PhotoURL_editText.getText().toString(), AvailabilityDate_editText.getText().toString(), DescriptionText.getText().toString()));
                        ;
                        dataBaseHelper.insertHave(PostalAddress_editText.getText().toString(), email);
                        TOASTMSG = "The Property has been added Correctly!";
                    }

                }
                Toast toast = Toast.makeText(getActivity(), TOASTMSG, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}