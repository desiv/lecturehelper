package com.ktuld.lecturehelper;

/**
 * Created by Marius Savickas on 2015.12.09.
 */
public class JavaScriptProxyToHtmlTest
    {
    }



public class JavaScriptProxyToHtmlTest
    {

    private JavaScriptProxyToHtml mTarget;

    @Before
    public void setUp() {
        mTarget = new JavaScriptProxyToHtmlTest(null, null);
    }

    @Test
    public void containsDataTest() {

        mTarget.sourceSets(action);
        verify(false).execute(mTarget.containsData ());
    }

}