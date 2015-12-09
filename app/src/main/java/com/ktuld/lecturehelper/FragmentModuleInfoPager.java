package com.ktuld.lecturehelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Marius Savickas on 2015.11.25.
 */
public class FragmentModuleInfoPager extends Fragment
    {

    private ViewPager m_pager = null;
    private PagerAdapter m_pagerAdapter = null;

    public static FragmentModuleInfoPager newInstance ()
        {
        FragmentModuleInfoPager fragment = new FragmentModuleInfoPager ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
        }

    public FragmentModuleInfoPager ()
        {
        // Required empty public constructor
        }

    @Override
    public void onCreate (Bundle savedInstanceState)
        {
        super.onCreate (savedInstanceState);


        }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
        {
        View view = inflater.inflate (R.layout.layout_module_info_pager, container, false);

        TextView text = (TextView) view.findViewById (R.id.module_info_module_code);
        text.setText (LectureHelper.gSelectedModule);

        m_pager = (ViewPager) view.findViewById (R.id.pager_events);
        m_pagerAdapter = new AdapterModuleInfoPager (LectureHelper.gFragmentManager);
        m_pager.setAdapter (m_pagerAdapter);

        final int pageMargin = (int) TypedValue.applyDimension (TypedValue.COMPLEX_UNIT_DIP, 4, getResources ()
                .getDisplayMetrics ());
        m_pager.setPageMargin (pageMargin);
        // Bind the tabs to the ViewPager
        TabLayout tabLayout = (TabLayout) view.findViewById (R.id.pager_tabs);
        tabLayout.setupWithViewPager (m_pager);

        return view;
        }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
        {
        super.onViewCreated (view, savedInstanceState);

        }

    @Override
    public void onStart ()
        {
        super.onStart ();

        }
    }
