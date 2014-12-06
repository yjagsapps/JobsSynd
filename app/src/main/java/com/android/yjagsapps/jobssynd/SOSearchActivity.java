package com.android.yjagsapps.jobssynd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        EditText editTextJobSkill = (EditText) findViewById(R.id.TextViewJobSkill);
        String message = editTextJobSkill.getText().toString();
        intent.putExtra("jobSkill", message);
        startActivity(intent);

    }
}
