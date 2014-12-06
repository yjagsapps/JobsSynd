package com.android.yjagsapps.jobssynd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Jags on 12/4/2014.
 */
public class SOSearchActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsearch);
    }

    public void searchAllJobs(View view){
        Intent  intent = new Intent(this,SOCareersActivity.class);
        String message = "";
        EditText editTextJobSkill = (EditText) findViewById(R.id.TextViewJobSkill);
        try {

            message = URLEncoder.encode(editTextJobSkill.getText().toString(),"UTF-8");
        }
        catch (UnsupportedEncodingException e){
            Log.e("NOT OK","Some error");
        }

        intent.putExtra("jobSkill", message);
        startActivity(intent);

    }
}
