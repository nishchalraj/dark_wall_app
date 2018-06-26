package com.hackerkernel.user.sqrfactor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

public class JobDetails extends AppCompatActivity {

    Toolbar toolbar;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        textview = (TextView)findViewById(R.id.textview);
        String s = getApplicationContext().getResources().getString(R.string.jobdetails);
        textview.setText(Html.fromHtml(s));

    }
}
