package com.ktuld.lecturehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class LHDatabaseHelper extends SQLiteOpenHelper
    {

    private static final String DATABASE_NAME = "DBName";

    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table modules(module_name text not null, name text not null);";

    public LHDatabaseHelper (Context context)
        {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    // Method is called during creation of the database
    @Override
    public void onCreate (SQLiteDatabase database)
        {
        database.execSQL (DATABASE_CREATE);
        }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade (SQLiteDatabase database, int oldVersion, int newVersion)
        {
        //Log.w (LHDatabaseHelper.class.getName (),
        //        "Upgrading database from version " + oldVersion + " to "
         //               + newVersion + ", which will destroy all old data");
        database.execSQL ("DROP TABLE IF EXISTS modules");
        onCreate (database);
        }
    }
