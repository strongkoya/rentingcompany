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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RAHistoryGrid extends BaseAdapter {
    private final ArrayList<Property> propertiesArrayList = new ArrayList<Property>();
    private Context mContext;

    public RAHistoryGrid(Context c, ArrayList<Property> propertiesArrayList) {
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
        //surface_area_textView.setText("Renting Period: " + "1 Year");
        DataBaseHelper DB = new DataBaseHelper(mContext, "EXP4", null, 1);
        Cursor cursor = DB.getReadableDatabase().rawQuery("Select FIRSTNAME, LASTNAME, STARTDATE from TENANT, CONTRACT WHERE CONTRACT.EMAIL = TENANT.EMAIL AND POSTALADDRESS LIKE '" + X.getPostalAddress() + "'", null);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (cursor.moveToNext()) {
            Date firstDate = null;
            try {
                firstDate = sdf.parse(cursor.getString(2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date secondDate = new Date(System.currentTimeMillis());
            long diff = secondDate.getTime() - firstDate.getTime();
            info_textView.setText("Tenant Name: " + cursor.getString(0) + " " + cursor.getString(1) + "\nRenting Period: " + (diff / 1000 / 60 / 60 / 24) + " Day/s");
        } else
            info_textView.setText("");
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
