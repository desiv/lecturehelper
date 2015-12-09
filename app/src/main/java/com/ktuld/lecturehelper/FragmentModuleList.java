package com.ktuld.lecturehelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class FragmentModuleList extends Fragment
    {

    public static FragmentModuleList newInstance ()
        {
        FragmentModuleList fragment = new FragmentModuleList ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
        }

    public FragmentModuleList ()
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
        View view = inflater.inflate (R.layout.layout_module_list, container, false);

        ListView listview = (ListView) view.findViewById (R.id.event_list);
        AdapterModuleList listAdapter = new AdapterModuleList ();
        listview.setOnItemClickListener (listAdapter.getOnItemClickListener ());
        listview.setAdapter (listAdapter);

        return view;
        }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
        {
        super.onViewCreated (view, savedInstanceState);


        }
    }
