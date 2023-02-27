package com.example.rentingcompany.Grids;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rentingcompany.DataBase.DataBaseHelper;
import com.example.rentingcompany.Models.Property;
import com.example.rentingcompany.R;

import java.util.ArrayList;

public class RequestGrid extends BaseAdapter {
    private final ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
    private Context mContext;

    public RequestGrid(Context c, ArrayList<Property> propertiesArrayList) {
        mContext = c;
        this.propertiesArrayList.addAll(propertiesArrayList);
    }

    @Override
    public int getCount() {
        return propertiesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        if (convertView == null) {

        grid = new View(mContext);
        grid = inflater.inflate(R.layout.grid_single, null);
        TextView location_textView = (TextView) grid.findViewById(R.id.location_text);
        TextView surface_area_textView = (TextView) grid.findViewById(R.id.surfacearea_text);
        TextView info_textView = (TextView) grid.findViewById(R.id.info_text);
        TextView description_textView = (TextView) grid.findViewById(R.id.description_text);
        ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
        Property X = propertiesArrayList.get(position);
        location_textView.setText(X.getCity() + " - " + X.getPostalAddress());

        DataBaseHelper DB = new DataBaseHelper(mContext, "EXP4", null, 1);
        Cursor cursor = DB.getReadableDatabase().rawQuery("Select FIRSTNAME, LASTNAME from TENANT, REQUEST WHERE TENANT.EMAIL = REQUEST.EMAIL AND REQUEST.POSTALADDRESS  LIKE '" + X.getPostalAddress() + "'", null);
        if(cursor.moveToNext())
            surface_area_textView.setText("Tenant Name: " + cursor.getString(0) + " " + cursor.getString(1));
        else
            surface_area_textView.setText("");

        //info_textView.setText("Construction Year: " + String.valueOf(X.getConstructionYear()) + "\n# Of Bedrooms: " + String.valueOf(X.getNumOfBedrooms()) + "\nRental Price: " + String.valueOf(X.getRentalPrice()) + "$\n" + (X.isFurnished() ? "Furnished" : "Unfurnished") + "\nAvailability Date: " + String.valueOf(X.getAvailabilityDate()));
        //description_textView.setText(X.getDescryption());
        //imageView.setImageResource(R.drawable.property_picture);
        Glide.with(parent).load(X.getPhotoURL())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.property_picture)
                .into(imageView);
//        } else {
//            grid = (View) convertView;
//        }

        return grid;
    }
}
