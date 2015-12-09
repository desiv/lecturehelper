package com.ktuld.lecturehelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.goebl.david.Request;
import com.goebl.david.Webb;
import com.goebl.david.WebbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class FragmentModuleInfoSeminar extends Fragment
    {

    private ArrayList<ModuleInfo> m_moduleInfo = new ArrayList<> ();
    private AdapterModuleInfoList m_adapterModuleInfoList = null;

    public static FragmentModuleInfoSeminar newInstance ()
        {
        FragmentModuleInfoSeminar fragment = new FragmentModuleInfoSeminar ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
        }

    public FragmentModuleInfoSeminar ()
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
        View view = inflater.inflate (R.layout.layout_module_info, container, false);


        ListView listview = (ListView) view.findViewById (R.id.module_info_list);
        m_adapterModuleInfoList = new AdapterModuleInfoList (m_moduleInfo);
        listview.setAdapter (m_adapterModuleInfoList);

        return view;
        }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
        {
        super.onViewCreated (view, savedInstanceState);

        Button button= (Button) view.findViewById (R.id.module_info_add);
        button.setOnClickListener (new View.OnClickListener ()
        {
        @Override
        public void onClick (View v)
            {
            LectureHelper.gSelectedAdapterModuleInfoList = m_adapterModuleInfoList;
            FragmentTransaction ft = LectureHelper.gFragmentManager.beginTransaction ();
            ft.replace (R.id.main_container, FragmentCreateInfo.newInstance (), "MAIN_CONTAINER");
            ft.addToBackStack (null);
            ft.commit ();
            }
        });
        }

    @Override
    public void onStart ()
        {
        super.onStart ();

        String selectedModule = LectureHelper.gSelectedModule;

        ArrayList<Object> params = new ArrayList<> ();
        params.add (selectedModule);

        Runnable updateEvents = new PeRunnable (params)
        {
        @Override
        public void run ()
            {
            try
                {
                JSONObject json = new JSONObject ();
                try
                    {
                    json.put ("module_code", m_parameters.get (0)); //P1705B244
                    json.put ("lecture_type", "seminar");
                    }
                catch (JSONException e)
                    {
                    e.printStackTrace ();
                    }


                Request request = Webb.create ()
                        .post (LectureHelper.gServerUrl)
                        .body (json.toString ());
                JSONArray result = request.ensureSuccess ()
                        .asJsonArray ()
                        .getBody ();

                m_moduleInfo.clear ();
                for(int n = 0; n < result.length(); n++)
                    {
                    try
                        {
                        JSONObject object = result.getJSONObject(n);

                        ModuleInfo info = new ModuleInfo ();
                        info.attached = object.getString ("message_attach");
                        info.content = object.getString ("message_content");
                        info.date = object.getString ("message_date");
                        info.messageID = object.getInt ("message_id");
                        info.moduleCode = object.getString ("module_code");
                        info.userID = object.getString ("user_id");
                        info.userName = object.getString ("user_name");
                        info.lectureType = object.getString ("lecture_type");

                        //object.has ()

                        m_moduleInfo.add (info);
                        }
                    catch (JSONException e)
                        {
                        e.printStackTrace ();
                        }
                    }

                }
            catch (WebbException e)
                {
                Throwable cause = e.getCause ();
                if (cause instanceof SocketTimeoutException)
                    {
                    }
                e.printStackTrace ();
                }

            ((Activity) (LectureHelper.gContext)).runOnUiThread (new Runnable()
                {

                @Override
                public void run()
                    {
                    m_adapterModuleInfoList.notifyDataSetChanged();
                    }
                });
            }
        };

        new Thread (updateEvents).start ();

        }
    }
