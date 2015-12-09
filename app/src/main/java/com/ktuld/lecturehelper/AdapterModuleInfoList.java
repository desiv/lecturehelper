package com.ktuld.lecturehelper;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Marius Savickas on 2015.07.19.
 */
public class AdapterModuleInfoList extends BaseAdapter
    {

    ArrayList<ModuleInfo> m_moduleInfoColletion = null;

    public AdapterModuleInfoList (ArrayList<ModuleInfo> moduleInfo)
        {
        m_moduleInfoColletion = moduleInfo;
        }

    @Override
    public int getCount ()
        {
        return m_moduleInfoColletion.size ();
        }

    @Override
    public Object getItem (int position)
        {
        return m_moduleInfoColletion.get (position);
        }

    @Override
    public long getItemId (int position)
        {
        return position;
        }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
        {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.layout_module_info_list_item, null);

        ModuleInfo info = m_moduleInfoColletion.get (position);
        TextView text = (TextView) view.findViewById (R.id.module_list_info_item_content);
        text.setText (info.content);
        text = (TextView) view.findViewById (R.id.module_list_info_item_attach);
        text.setText (info.attached);
        text = (TextView) view.findViewById (R.id.module_list_info_item_date);
        text.setText (info.date);
        text = (TextView) view.findViewById (R.id.module_list_info_item_username);
        text.setText (info.userName);
        text = (TextView) view.findViewById (R.id.module_list_info_item_lecturetype);
        text.setText (info.lectureType);

        return view;
        }

    }
