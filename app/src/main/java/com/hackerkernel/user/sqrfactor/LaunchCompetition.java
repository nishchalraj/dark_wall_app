package com.hackerkernel.user.sqrfactor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

public class LaunchCompetition extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    Button nextbtn;
    EditText startdate, closedate, closedate2, announcedate;
    RadioButton rb1, rb2;
    private int cyear, cmonth, cdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_competition);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nextbtn = (Button)findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Launch2.class);
                startActivity(i);
            }
        });

        startdate = (EditText)findViewById(R.id.startdate);
        closedate = (EditText)findViewById(R.id.closedate);
        closedate2 = (EditText)findViewById(R.id.closedate2);
        announcedate = (EditText)findViewById(R.id.announcedate);

        Calendar calendar = Calendar.getInstance();
        cyear = calendar.get(Calendar.YEAR);
        cmonth = calendar.get(Calendar.MONTH);
        cdate = calendar.get(Calendar.DAY_OF_MONTH);

        startdate.setOnClickListener(this);
        closedate.setOnClickListener(this);
        closedate2.setOnClickListener(this);
        announcedate.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(v==startdate)
                    startdate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                else if (v==closedate)
                    closedate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                else if(v==closedate2)
                    closedate2.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                else
                    announcedate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        };

        new DatePickerDialog(LaunchCompetition.this, listener, cyear, cmonth, cdate).show();
    }

}
