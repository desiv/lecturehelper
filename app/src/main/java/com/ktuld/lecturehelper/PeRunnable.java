package com.ktuld.lecturehelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Marius Savickas on 2015.07.22.
 */
public class PeRunnable implements Runnable
    {
    protected ArrayList<Object> m_parameters = null;

    public PeRunnable (ArrayList<Object> parameters)
        {
        m_parameters = parameters;
        }

    public void addParameter (Object parameter)
        {
        m_parameters.add (parameter);
        }

    public void removeLastParameter ()
        {
        m_parameters.remove (m_parameters.size ()-1);
        }

    @Override
    public void run ()
        {

        }
    }
