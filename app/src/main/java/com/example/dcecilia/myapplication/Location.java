package com.example.dcecilia.myapplication;

import java.util.Date;

/**
 * Created by dcecilia on 29/03/2016.
 */
public class Location {
    int _id;
    String _latitude;
    String _longitude;
    String _date;
    String _acuracy;

    public Location(){}

    public Location(String latitude, String longitude, String date, String acuracy ){
        this._acuracy = acuracy;
        this._date = date;
        this._latitude = latitude;
        this._longitude = longitude;
    }

    public Location(int id, String latitude, String longitude, String date, String acuracy ){
        this._id = id;
        this._acuracy = acuracy;
        this._date = date;
        this._latitude = latitude;
        this._longitude = longitude;
    }

    public int get_id(){return this._id;}
    public void set_id(int id){this._id = id;}

    public String get_date() {return _date;}
    public void set_date(String date){this._date = date;}

    public String get_acuracy() {return _acuracy;}
    public void set_acuracy(String acuracy){ this._acuracy = acuracy;}

    public String get_latitude() {return _latitude;}
    public void set_latitude(String latitude){ this._latitude = latitude;}

    public String get_longitude() {
        return _longitude;
    }

    public void set_longitude(String longitude){this._longitude = longitude;}
}
