package com.hackerkernel.user.sqrfactor.Activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.hackerkernel.user.sqrfactor.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

public class BasicDetailsActivity extends AppCompatActivity {

    private Button addEmail;
    Spinner spin;
    Spinner countrySpinner;
    String spin_val=null;
    private RadioGroup radioGroup;
    private EditText dateOfBirth,otherOccupation;
    private LinearLayout linearLayout2;
    private TextInputLayout email;
    private boolean email2=false;
    private boolean email3=false;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    private int count=0;
    Toolbar toolbar;
    ArrayList<String> countries = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        toolbar = (Toolbar) findViewById(R.id.basic_toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        addEmail = (Button) findViewById(R.id.AddEmail);
        linearLayout2 =(LinearLayout) findViewById(R.id.linearLayout2);
        linearLayout2.setVisibility(View.GONE);
        addEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(BasicDetailsActivity.this);
                final View promptsView = li.inflate(R.layout.addemailprompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        BasicDetailsActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText nextEmail1 = (EditText) promptsView
                        .findViewById(R.id.nextEmailText1);
                final Button addPromptButton= (Button) promptsView
                        .findViewById(R.id.AddPromptButton);

                final LinearLayout linearLayoutPrompt1=(LinearLayout) promptsView.findViewById(R.id.linearLayoutprompt1);
                linearLayoutPrompt1.setVisibility(View.GONE);

                final LinearLayout linearLayoutPrompt2=(LinearLayout) promptsView.findViewById(R.id.linearLayoutprompt2);
                linearLayoutPrompt2.setVisibility(View.GONE);

                final Button removePromptButton1= (Button) promptsView
                        .findViewById(R.id.removeEmailButton1);

                final Button removePromptButton2= (Button) promptsView
                        .findViewById(R.id.removeEmailButton2);

                //final TextInputLayout nextEmail=(TextInputLayout) promptsView.findViewById(R.id.nextEmail);

                addPromptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        count++;
                        if(!email2 && count==1)
                        {
                            linearLayoutPrompt1.setVisibility(View.VISIBLE);
                            email2=true;
                        }
                        if(!email3 && count==2)
                        {
                            linearLayoutPrompt2.setVisibility(View.VISIBLE);
                            email3=true;
                        }

                    }
                });

                removePromptButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(email2)
                        {
                            linearLayoutPrompt1.setVisibility(View.GONE);
                            email2=false;
                            count--;

                        }
                    }
                });
                removePromptButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(email3)
                        {
                            linearLayoutPrompt2.setVisibility(View.GONE);
                            email3=false;
                            count--;
                        }
                    }
                });


                // set dialog message
                /*alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // here we will get the new email
                                        //
                                    }
                                })
                        .setNegativeButton("CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });*/

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });




        final String[] gender = { "Male", "Female" };
        spin = (Spinner) findViewById(R.id.gender);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1,

                                       int  position, long id) {
                spin_val = gender[position];

            }

            @Override

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(BasicDetailsActivity.this, android.R.layout.simple_list_item_1, gender);
        spin.setAdapter(spin_adapter);


        countrySpinner = (Spinner) findViewById(R.id.Country);
        //final String[] Countries = { "India", "Pakistan","England","Australia" };
        Locale[] locale = Locale.getAvailableLocales();

        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);


        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1,

                                       int  position, long id) {
                spin_val = countries.get(position);


            }

            @Override

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

        ArrayAdapter<String> spin_adapter1 = new ArrayAdapter<String>(BasicDetailsActivity.this, android.R.layout.simple_list_item_1, countries);
        countrySpinner.setAdapter(spin_adapter1);

        dateOfBirth=(EditText)findViewById(R.id.dateOfBirthText);

        final Calendar myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabe(myCalendar);
            }

        };

        dateOfBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                new DatePickerDialog(BasicDetailsActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        checkBox1= (CheckBox) findViewById(R.id.checkbox1);
        checkBox2= (CheckBox) findViewById(R.id.checkbox2);
        checkBox3= (CheckBox) findViewById(R.id.checkbox3);
        checkBox4= (CheckBox) findViewById(R.id.checkbox4);
        checkBox5= (CheckBox) findViewById(R.id.checkbox5);
        checkBox6= (CheckBox) findViewById(R.id.checkbox6);

        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                   linearLayout2.setVisibility(View.VISIBLE);
                }
                else
                {
                    linearLayout2.setVisibility(View.GONE);
                }
            }
        });
    }

    private void updateLabe(Calendar myCalendar) {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}