package com.freedmore.myawesomeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "countries";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String CURRENCY = "currency";
    public static final String POPULATION = "population";


    // Database Information
    static final String DB_NAME = "COUNTRIES.DB";

    static final String[] COLUMNS = {_ID,NAME,CURRENCY,POPULATION};

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + _ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, "
            + CURRENCY + " TEXT NOT NULL,"
            + POPULATION + " FLOAT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

