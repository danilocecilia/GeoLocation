package com.example.dcecilia.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class LocationListFragment extends Fragment {

    private ArrayAdapter<Location> mForecastAdapter;

    public LocationListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        DataBaseHandler db = new DataBaseHandler(rootView.getContext());

        ListView listView = (ListView) rootView.findViewById(R.id.listView);

        TodoCursorAdapter todoCursorAdapter = new TodoCursorAdapter(rootView.getContext(), db.getAllContactsCursor(),0);

        listView.setAdapter(todoCursorAdapter);

        return rootView;
    }


}
