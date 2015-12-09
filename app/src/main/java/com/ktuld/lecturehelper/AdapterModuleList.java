package com.ktuld.lecturehelper;

import android.database.Cursor;
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
public class AdapterModuleList extends BaseAdapter
    {

    ArrayList<String> m_moduleColletion = null;

    public AdapterModuleList ()
        {
        m_moduleColletion = LectureHelper.gModules;
        }

    @Override
    public int getCount ()
        {
        return m_moduleColletion.size ();
        }

    @Override
    public Object getItem (int position)
        {
        return m_moduleColletion.get (position);
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
            view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.layout_module_list_item, null);

        String module = m_moduleColletion.get (position);
        TextView text = (TextView) view.findViewById (R.id.module_list_item_text);
        text.setText (module);

        return view;
        }

    public AdapterView.OnItemClickListener getOnItemClickListener ()
        {
        return new AdapterView.OnItemClickListener ()
            {
            @Override
            public void onItemClick (AdapterView<?> arg0, View view, int position, long id)
                {
                //PelotonActivity.sGlobalData.gEventSelected = m_eventColletion.get (position);
                LectureHelper.gSelectedModule = m_moduleColletion.get (position);
                FragmentTransaction ft = LectureHelper.gFragmentManager.beginTransaction ();
                ft.replace (R.id.main_container, FragmentModuleInfoPager.newInstance (), "MAIN_CONTAINER");
                ft.addToBackStack (null);
                ft.commit ();
                }
            };
        }
    }
