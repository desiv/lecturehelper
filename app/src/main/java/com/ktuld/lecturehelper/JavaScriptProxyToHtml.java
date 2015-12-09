package com.ktuld.lecturehelper;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class JavaScriptProxyToHtml
    {
    private Context ctx;
    private String html;
    private PeRunnable m_execute;


    JavaScriptProxyToHtml (Context ctx, PeRunnable postExecute)
        {
        this.ctx = ctx;
        m_execute = postExecute;
        }

    @JavascriptInterface
    public void showHTML (String html)
        {
        this.html = html;
        m_execute.addParameter (html);
        m_execute.run ();
        m_execute.removeLastParameter ();
        }

    public boolean containsData ()
        {return m_execute != null;}


    }

