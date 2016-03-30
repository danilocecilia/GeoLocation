package com.example.dcecilia.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by dcecilia on 30/03/2016.
 */
public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_location, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvLongitude = (TextView) view.findViewById(R.id.tvLongitude);
        TextView tvLatitude = (TextView) view.findViewById(R.id.tvLatitude);
        TextView tvAcuracy = (TextView) view.findViewById(R.id.tvAcuracy);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);

        // Extract properties from cursor
        String latitude = cursor.getString(cursor.getColumnIndexOrThrow("latitude"));
        String longitude = cursor.getString(cursor.getColumnIndexOrThrow("longitude"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String acuracy = cursor.getString(cursor.getColumnIndexOrThrow("acuracy"));

        // Populate fields with extracted properties
        tvLongitude.setText(latitude);
        tvLatitude.setText(longitude);
        tvAcuracy.setText(date);
        tvDate.setText(acuracy);
    }
}