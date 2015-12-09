package com.ktuld.lecturehelper;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class LectureHelper extends AppCompatActivity
    {
    public static String gServerUrl = "http://studentsktudo.96.lt/";

    public static ArrayList<String> gModules = null;
    public static String gSelectedModule;
    //public static ArrayList<ModuleInfo> gModuleInfo = null;
    public static Context gContext = null;
    public static FragmentManager gFragmentManager = null;
    public static LHDatabase gDatabase = null;
    public static AppCompatActivity gActivity = null;

    public static AdapterModuleInfoList gSelectedAdapterModuleInfoList = null;

    @Override
    protected void onCreate (Bundle savedInstanceState)
        {

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lecture_helper);

        gContext = this;
        gDatabase = new LHDatabase (this);

        //gModuleInfo = new ArrayList<> ();
        gActivity = this;

        // Fragment setup
        gFragmentManager = getSupportFragmentManager ();

        FragmentTransaction fragmentTransaction = gFragmentManager.beginTransaction ();

        if (gDatabase.isEmpty ())
            fragmentTransaction.add (R.id.main_container, FragmentLogin.newInstance (), "MAIN_CONTAINER");
        else
            {
            gModules = gDatabase.getAllElements ();
            fragmentTransaction.add (R.id.main_container, FragmentModuleList.newInstance (), "MAIN_CONTAINER");
            }

        fragmentTransaction.commit ();
        }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
        {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ().inflate (R.menu.menu_lecture_helper, menu);
        return true;
        }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
        {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
            {
            return true;
            }

        return super.onOptionsItemSelected (item);
        }
    }
