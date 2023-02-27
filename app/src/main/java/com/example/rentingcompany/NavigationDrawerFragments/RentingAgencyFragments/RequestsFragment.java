package com.example.rentingcompany.NavigationDrawerFragments.RentingAgencyFragments;

import static com.example.rentingcompany.MainActivity.email;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Grids.MenuGrid;
import com.example.rentingcompany.Grids.RequestGrid;
import com.example.rentingcompany.Grids.THistoryGrid;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String tenantEmail = "";
    String postal = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestsFragment newInstance(String param1, String param2) {
        RequestsFragment fragment = new RequestsFragment();
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
        return inflater.inflate(R.layout.fragment_requests, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayout firstLayout = (LinearLayout) getActivity().findViewById(R.id.requestsLayout);
        LinearLayout secondLayout = (LinearLayout) getActivity().findViewById(R.id.tenantDetailsLayout);
        LinearLayout ApprovedLayout = (LinearLayout) getActivity().findViewById(R.id.ApprovedLayout);
        LinearLayout RejectedLayout = (LinearLayout) getActivity().findViewById(R.id.RejectedLayout);
        firstLayout.setVisibility(View.VISIBLE);
        secondLayout.setVisibility(View.INVISIBLE);
        ApprovedLayout.setVisibility(View.INVISIBLE);
        RejectedLayout.setVisibility(View.INVISIBLE);


        TextView tenantDetailsTextView = (TextView) getActivity().findViewById(R.id.tenantDetailsTextView);
        TextView QemptyTHistoryTextView = (TextView) getActivity().findViewById(R.id.QemptyTHistoryTextView);
        GridView previousProperties = (GridView) getActivity().findViewById(R.id.previousProperties);
        Button approveButton = (Button) getActivity().findViewById(R.id.approveButton);
        Button rejectButton = (Button) getActivity().findViewById(R.id.rejectButton);


        TextView ViewTenantTextView = (TextView) getActivity().findViewById(R.id.ViewTenantTextView);
        GridView grid = (GridView) getActivity().findViewById(R.id.requestsGrid);

        DataBaseHelper DBHelper = new DataBaseHelper(getActivity(), "EXP4", null, 1);
        ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
        Cursor cursor = DBHelper.getAllHaveData(email);
        Boolean empty = true;
        Cursor cursor2;
        while (cursor.moveToNext()) {
            empty = false;
            cursor2 = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY, REQUEST WHERE REQUEST.POSTALADDRESS  LIKE PROPERTY.POSTALADDRESS AND PROPERTY.POSTALADDRESS LIKE '" + cursor.getString(0) + "'", null);
            while (cursor2.moveToNext())
                propertiesArrayList.add(new Property(cursor2.getString(0), cursor2.getString(1), cursor2.getInt(2), cursor2.getInt(3), cursor2.getInt(4), cursor2.getDouble(5), (cursor2.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor2.getString(7), cursor2.getString(8), cursor2.getString(9)));
        }

        if (empty) {
            ViewTenantTextView.setText("No Requests!");
        } else {
            ViewTenantTextView.setText("Click on Request to View Tenant");
            RequestGrid adapter = new RequestGrid(getActivity(), propertiesArrayList);
            grid.setAdapter(adapter);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    firstLayout.setVisibility(View.INVISIBLE);
                    secondLayout.setVisibility(View.VISIBLE);
                    ApprovedLayout.setVisibility(View.INVISIBLE);
                    RejectedLayout.setVisibility(View.INVISIBLE);
                    postal = propertiesArrayList.get(position).getPostalAddress();

                    Cursor cursor3 = DBHelper.getReadableDatabase().rawQuery("SELECT TENANT.EMAIL FROM TENANT, REQUEST WHERE REQUEST.EMAIL = TENANT.EMAIL AND REQUEST.POSTALADDRESS LIKE '" + postal + "'", null);
                    if(cursor3.moveToNext()){
                        tenantEmail = cursor3.getString(0);
                        Cursor cursor4 = DBHelper.getReadableDatabase().rawQuery("SELECT * FROM TENANT WHERE EMAIL LIKE '" + tenantEmail + "'", null);
                        if(cursor4.moveToNext())
                            tenantDetailsTextView.setText("Tenant Name: " + cursor4.getString(1) + " " + cursor4.getString(2) + "\nGender: " + cursor4.getString(3) + "\nNationality: " + cursor4.getString(6) + "\nGross Monthly Salary: " + cursor4.getString(7) + "\nCurrent Residence Country: " + cursor4.getString(10) + "\nCity: " + cursor4.getString(11) + "\nPhone Number: " + cursor4.getString(12) );


                        ArrayList<Property> pArrayList = new ArrayList<Property>();
                        Cursor cursor5 = DBHelper.getAllContractDataForTenant(tenantEmail);
                        Boolean empty = true;
                        Cursor cursor6;
                        while (cursor5.moveToNext()) {
                            empty = false;
                            cursor6 = DBHelper.getReadableDatabase().rawQuery("Select * from PROPERTY WHERE POSTALADDRESS LIKE '" + cursor5.getString(0) + "'", null);
                            while (cursor6.moveToNext())
                                pArrayList.add(new Property(cursor6.getString(0), cursor6.getString(1), cursor6.getInt(2), cursor6.getInt(3), cursor6.getInt(4), cursor6.getDouble(5), (cursor6.getString(6).compareToIgnoreCase("TRUE") == 0 ? true : false), cursor6.getString(7), cursor6.getString(8), cursor6.getString(9)));
                        }

                        if (empty)
                            QemptyTHistoryTextView.setVisibility(View.VISIBLE);
                        else {
                            QemptyTHistoryTextView.setVisibility(View.INVISIBLE);
                            THistoryGrid adapter = new THistoryGrid(getActivity(), pArrayList);
                            previousProperties.setAdapter(adapter);
                        }

                    }




                }
            });
        }
        approveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper DB = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                DB.deleteRequest(postal, tenantEmail);
                DB.insertContract(postal, tenantEmail);

                firstLayout.setVisibility(View.INVISIBLE);
                secondLayout.setVisibility(View.INVISIBLE);
                ApprovedLayout.setVisibility(View.VISIBLE);
                RejectedLayout.setVisibility(View.INVISIBLE);
            }
        });

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper DB = new DataBaseHelper(getActivity(), "EXP4", null, 1);
                DB.deleteRequest(postal, tenantEmail);


                firstLayout.setVisibility(View.INVISIBLE);
                secondLayout.setVisibility(View.INVISIBLE);
                ApprovedLayout.setVisibility(View.INVISIBLE);
                RejectedLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}