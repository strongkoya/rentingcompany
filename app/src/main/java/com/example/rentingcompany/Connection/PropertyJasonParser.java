package com.example.rentingcompany.Connection;

import com.example.rentingcompany.Models.Property;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PropertyJasonParser {
    public static List<Property> getObjectFromJason(String jason) {
        List<Property> Properties;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            Properties = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Property property = new Property();

                property.setPostalAddress(jsonObject.getString("postalAddress").toString());
                property.setCity(jsonObject.getString("city").toString());
                property.setSurfaceArea(jsonObject.getInt("surfaceArea"));
                property.setConstructionYear(jsonObject.getInt("constructionYear"));
                property.setNumOfBedrooms(jsonObject.getInt("numOfBedrooms"));
                property.setRentalPrice(jsonObject.getDouble("rentalPrice"));
                property.setFurnished(jsonObject.getBoolean("ISFURNISHED"));
                property.setPhotoURL(jsonObject.getString("photoURL"));
                property.setAvailabilityDate(jsonObject.getString("availabilityDate"));
                property.setDescryption(jsonObject.getString("descryption"));


                Properties.add(property);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return Properties;
    }
}
