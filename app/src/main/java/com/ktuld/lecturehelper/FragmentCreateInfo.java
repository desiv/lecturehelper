package com.ktuld.lecturehelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.goebl.david.Request;
import com.goebl.david.Response;
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
public class FragmentCreateInfo extends Fragment
    {

    String m_content;
    String m_attach;
    String m_lectureType;

    public static FragmentCreateInfo newInstance ()
        {
        FragmentCreateInfo fragment = new FragmentCreateInfo ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
        }

    public FragmentCreateInfo ()
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
        View view = inflater.inflate (R.layout.layout_module_create, container, false);
        return view;
        }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
        {
        super.onViewCreated (view, savedInstanceState);

        final EditText contentTextEdit = (EditText)view.findViewById(R.id.editText_content);
        final EditText attachTextEdit = (EditText)view.findViewById(R.id.editText_attach);
        final Spinner lectureTypeSpinner = (Spinner)view.findViewById(R.id.spinner_lecture_type);

        Button button= (Button) view.findViewById (R.id.module_info_create);
        button.setOnClickListener (new View.OnClickListener ()
        {
        @Override
        public void onClick (View v)
            {
            m_content = contentTextEdit.getText ().toString ();
            m_attach = attachTextEdit.getText ().toString ();
            m_lectureType = lectureTypeSpinner.getSelectedItem ().toString ();

            if (m_content == null && m_attach == null)
                return;

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
                        json.put ("user_id", "marsav2");
                        json.put ("user_name", "Marius Savickas");
                        json.put ("message_content", m_content);
                        json.put ("message_attach", m_attach);
                        json.put ("module_code", m_parameters.get (0));
                        json.put ("lecture_type", m_lectureType);
                        }
                    catch (JSONException e)
                        {
                        e.printStackTrace ();
                        }


                    Request request = Webb.create ()
                            .post (LectureHelper.gServerUrl)
                            .body (json.toString ());
                    Response result = request.ensureSuccess ().asString ();


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
                    LectureHelper.gSelectedAdapterModuleInfoList.notifyDataSetChanged();
                    LectureHelper.gActivity.onBackPressed ();
                    }
                });
                }
            };

            new Thread (updateEvents).start ();

            }
        });
        }
    }
