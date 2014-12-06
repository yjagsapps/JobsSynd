package com.android.yjagsapps.jobssynd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Jags on 12/6/2014.
 */
public class SoCareersDetailsActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the message from intent
        Intent intent = getIntent();
        String strjobURL = intent.getStringExtra("jobURL");

        // Set the Job details activity
        setContentView(R.layout.webview_jobdetails);

        WebView jobDetailsWebView = (WebView) findViewById(R.id.webviewjobdetails);
        jobDetailsWebView.loadUrl(strjobURL);
    }
}
