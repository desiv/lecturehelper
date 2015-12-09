package com.ktuld.lecturehelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Marius Savickas on 2015.10.01.
 */
public class FragmentLogin extends Fragment
    {

    public static FragmentLogin newInstance ()
        {
        FragmentLogin fragment = new FragmentLogin ();
        Bundle args = new Bundle ();
        fragment.setArguments (args);
        return fragment;
        }

    public FragmentLogin ()
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
        View view = inflater.inflate (R.layout.layout_login, container, false);

        final WebView wb = (WebView) view.findViewById (R.id.layout_webView_loging);
        wb.clearCache (true);
        wb.getSettings ().setJavaScriptEnabled (true);
        wb.getSettings ().setLoadWithOverviewMode (true);
        wb.getSettings ().setUseWideViewPort (true);
        wb.getSettings ().setBuiltInZoomControls (true);
        wb.getSettings().setJavaScriptEnabled (true);

        PeRunnable postJSGet = new PeRunnable (new ArrayList<> ())
        {
        @Override
        public void run ()
            {
            String str = (String) m_parameters.get (0);

            if (str != null)
                {
                if (!str.contains ("Neautentifikuotas"))
                    {
                    Document doc = Jsoup.parse (str);
                    Element semesterInfo = doc.select ("div.info-fixed-h1").first ();
                    Elements htmlModuleCode = semesterInfo.getElementsByTag ("b");

                    LHDatabase database = LectureHelper.gDatabase;
                    database.deleteEverything ();
                    for (Element htmlModuleName : htmlModuleCode)
                        {
                        String moduleCode = htmlModuleName.text ();

                        if (moduleCode.length () > 0)
                            database.createRecords ("wip", moduleCode);
                        }

                    LectureHelper.gModules = database.getAllElements ();
                    FragmentTransaction ft = LectureHelper.gFragmentManager.beginTransaction ();
                    ft.replace (R.id.main_container, FragmentModuleList.newInstance (), "MAIN_CONTAINER");
                    //ft.addToBackStack (null);
                    ft.commit ();
                    }
                }
            }
        };

        wb.addJavascriptInterface (new JavaScriptProxyToHtml (LectureHelper.gContext, postJSGet), "HtmlViewer");
        wb.setWebViewClient (new WebViewClient ()
        {
        @Override
        public void onPageFinished (WebView view, String url)
            {
            if (url.contains ("https://uais.cr.ktu.lt/ktuis/stud.busenos"))
                {

                wb.loadUrl ("javascript:window.HtmlViewer.showHTML" +
                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                }
            }
        });
        wb.loadUrl ("https://uais.cr.ktu.lt/ktuis/stud.busenos");

        return view;
        }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
        {
        super.onViewCreated (view, savedInstanceState);


        }
    }
