package com.ktuld.lecturehelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Marius Savickas on 2015.11.25.
 */
public class AdapterModuleInfoPager extends FragmentStatePagerAdapter
    {
    private int m_numPages = 5;
    FragmentModuleInfoAll m_fragmentModuleInfoAll = FragmentModuleInfoAll.newInstance ();
    FragmentModuleInfoTheory m_fragmentModuleInfoTheory = FragmentModuleInfoTheory.newInstance ();
    FragmentModuleInfoSeminar m_fragmentModuleInfoSeminar = FragmentModuleInfoSeminar.newInstance ();
    FragmentModuleInfoLab m_fragmentModuleInfoLab = FragmentModuleInfoLab.newInstance ();
    FragmentModuleInfoGeneral m_fragmentModuleInfoGeneral = FragmentModuleInfoGeneral.newInstance ();

    public AdapterModuleInfoPager (FragmentManager fm)
        {
        super (fm);
        }

    @Override
    public Fragment getItem (int position)
        {
        switch (position)
            {
            case 0:
                return m_fragmentModuleInfoAll;
            case 1:
                return m_fragmentModuleInfoGeneral;
            case 2:
                return m_fragmentModuleInfoTheory;
            case 3:
                return m_fragmentModuleInfoSeminar;
            case 4:
                return m_fragmentModuleInfoLab;
            default:
                return null;
            }
        }

    @Override
    public int getCount ()
        {
        return m_numPages;
        }

    @Override
    public CharSequence getPageTitle (int position)
        {
        switch (position)
            {
            case 0:
                return "All";
            case 1:
                return "Gen";
            case 2:
                return "Theory";
            case 3:
                return "Sem";
            case 4:
                return "Lab";
            default:
                return null;
            }
        }

    }
