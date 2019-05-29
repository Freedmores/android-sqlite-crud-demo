package com.freedmore.myawesomeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String curr, double pop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.CURRENCY, curr);
        contentValues.put(DatabaseHelper.POPULATION, pop);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.CURRENCY ,DatabaseHelper.POPULATION};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String curr, double pop) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.CURRENCY, curr);
        contentValues.put(DatabaseHelper.POPULATION, pop);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


    // Get All Books
    public List<Country> getAllCountries()
    {
        ArrayList<Country> countries = new ArrayList<>();

        // 1. build the query
         String query = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
         // 2. get reference to writable
         SQLiteDatabase db= dbHelper.getWritableDatabase();
         Cursor cursor= db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
         Country country = null;
         if (cursor!= null && cursor.moveToFirst())
         {
             do {
                 country = new Country();
                 country.setId(Integer.parseInt(cursor.getString(0)));
                 country.setName(cursor.getString(1));
                 country.setCurrency(cursor.getString(2));
                 country.setPopulation(Double.parseDouble(cursor.getString(3)));

                 // Add book to books
                 countries.add(country);

             }while (cursor.moveToNext());

         }
         return countries;
    }


    public Country getCountry(int id)
    {
        Country country= new Country();
        // 1. get reference to readable
         SQLiteDatabase db= dbHelper.getReadableDatabase();
        // 2. build query
         Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, // a. table
                 DatabaseHelper.COLUMNS, // b. column names
                 " id = ?", // c. selections
                  new String[] { String.valueOf(id) }, // d. selections args
                  null, // e. group by
                  null, // f. having
                  null, // g. order by
                  null); // h. limit
        // 3. if we got results get the first one
         if (cursor != null && cursor.moveToFirst())
         {
             // 4. build book object

              country.setId(Integer.parseInt(cursor.getString(0)));
              country.setName(cursor.getString(1));
              country.setCurrency(cursor.getString(2));
              country.setPopulation(Double.parseDouble(cursor.getString(3)));

         }
         // return country
        return country;
    }// 5. return boo


}
