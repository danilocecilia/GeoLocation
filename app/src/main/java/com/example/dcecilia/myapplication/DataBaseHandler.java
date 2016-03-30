package com.example.dcecilia.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dcecilia on 29/03/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "locationManager";

    // Contacts table name
    private static final String TABLE_LOCATION = "location";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ACURACY = "acuracy";
    private static final String KEY_DATE = "date";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LONG = "longitude";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ACURACY + " TEXT,"
                + KEY_DATE + " DATE,"
                + KEY_LAT + " TEXT,"
                + KEY_LONG + " TEXT"
                + ")";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addContact(Location contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACURACY, contact.get_acuracy());
        values.put(KEY_DATE, String.valueOf(contact.get_date()));
        values.put(KEY_LAT, contact.get_latitude());
        values.put(KEY_LONG, contact.get_longitude());

        // Inserting Row
        db.insert(TABLE_LOCATION, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Location getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCATION, new String[]{KEY_ID, KEY_ACURACY, KEY_DATE, KEY_LAT, KEY_LONG}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Location contact = new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        // return contact
        return contact;

    }

    // Getting All Contacts
    public List<Location> getAllContacts() {
        List<Location> contactList = new ArrayList<Location>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Location contact = new Location();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_acuracy(cursor.getString(1));
                contact.set_date(cursor.getString(2));
                contact.set_latitude(cursor.getString(3));
                contact.set_longitude(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public Cursor getAllContactsCursor() {
        List<Location> contactList = new ArrayList<Location>();
        // Select All Query
        String selectQuery = "SELECT id as _id, acuracy, date, latitude, longitude FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // return contact list
        return cursor;
    }



    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOCATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(Location contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LONG, contact.get_longitude());
        values.put(KEY_LAT, contact.get_latitude());
        values.put(KEY_DATE, contact.get_date());
        values.put(KEY_ACURACY, contact.get_acuracy());

        // updating row
        return db.update(TABLE_LOCATION, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }

    // Deleting single contact
    public void deleteContact(Location contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATION, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }
}