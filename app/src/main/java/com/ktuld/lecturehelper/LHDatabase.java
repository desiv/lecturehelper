package com.ktuld.lecturehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class LHDatabase
    {

    private LHDatabaseHelper dbHelper;

    private SQLiteDatabase database;

    public final static String EMP_TABLE = "modules"; // name of table

    public final static String EMP_ID = "_id"; // id value for employee
    public final static String EMP_MODULE_NAME = "module_name";  // name of employee
    public final static String EMP_MODULE = "name";  // name of employee

    /**
     * @param context
     */
    public LHDatabase (Context context)
        {
        dbHelper = new LHDatabaseHelper (context);
        database = dbHelper.getWritableDatabase ();
        }


    public void deleteEverything ()
        {
        database.execSQL ("delete from " + EMP_TABLE);
        database.execSQL ("vacuum");
        }

    public long createRecords (String moduleName, String module)
        {
        ContentValues values = new ContentValues ();
        values.put (EMP_MODULE_NAME, moduleName);
        values.put (EMP_MODULE, module);
        return database.insert (EMP_TABLE, null, values);
        }

    public Cursor selectRecords ()
        {
        String[] cols = new String[]{EMP_MODULE_NAME, EMP_MODULE};
        Cursor mCursor = database.query (true, EMP_TABLE, cols, null
                , null, null, null, null, null);
        if (mCursor != null)
            {
            mCursor.moveToFirst ();
            }
        return mCursor; // iterate to get each value.
        }

    public ArrayList<String> getAllElements ()
        {

        ArrayList<String> list = new ArrayList<String> ();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + EMP_TABLE;

        SQLiteDatabase db = dbHelper.getReadableDatabase ();
        try
            {

            Cursor cursor = db.rawQuery (selectQuery, null);
            try
                {

                // looping through all rows and adding to list
                if (cursor.moveToFirst ())
                    {
                    do
                        {
                        //only one column
                        String str1 = cursor.getColumnName (0);
                        String str2 = cursor.getColumnName (1);

                        String obj1 = cursor.getString (0);
                        String obj2 = cursor.getString (1);

                        //you could add additional columns here..

                        list.add (obj2);
                        } while (cursor.moveToNext ());
                    }

                } finally
                {
                try
                    {
                    cursor.close ();
                    }
                catch (Exception ignore)
                    {
                    }
                }

            } finally
            {
            try
                {
                db.close ();
                }
            catch (Exception ignore)
                {
                }
            }

        return list;
        }

    public boolean isEmpty ()
        {
        boolean empty = true;
        Cursor cur = database.rawQuery ("SELECT COUNT(*) FROM "+EMP_TABLE, null);
        if (cur != null && cur.moveToFirst ())
            {
            empty = (cur.getInt (0) == 0);
            }
        cur.close ();

        return empty;
        }
    }
