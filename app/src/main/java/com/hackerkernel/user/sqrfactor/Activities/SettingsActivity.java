package com.hackerkernel.user.sqrfactor.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.R;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView menu;
    TextView text1,text2,text3,text4;
    TextView bluePrint,portfolio,followers,following;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new SettingsFragment()).commit();

        toolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        toolbar.setTitle("SettingsActivity");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        menu = (ImageView)findViewById(R.id.morebtn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(SettingsActivity.this,v);
                popupMenu.inflate(R.menu.profie_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        bluePrint = (TextView)findViewById(R.id.blueprint);
        bluePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this, BlueprintActivity.class);
                startActivity(i);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();

            }
        });

        portfolio = (TextView)findViewById(R.id.portfolio);
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(SettingsActivity.this, PortfolioActivity.class);
                startActivity(j);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();
            }
        });
        followers = (TextView)findViewById(R.id.followers);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(SettingsActivity.this, FollowersActivity.class);
                startActivity(k);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();

            }
        });
        following = (TextView)findViewById(R.id.following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(SettingsActivity.this, FollowingActivity.class);
                startActivity(l);

            }
        });

        text1 = (TextView)findViewById(R.id.basic_details);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this,BasicDetailsActivity.class);
                startActivity(i);
            }
        });
        text2 = (TextView)findViewById(R.id.edu_details);
        text2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent j = new Intent(SettingsActivity.this,EducationDetailsActivity.class);
                startActivity(j);
            }
        });
        text3 = (TextView)findViewById(R.id.prof_details);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(SettingsActivity.this,ProfessionalActivity.class);
                startActivity(k);
            }
        });
        text4 = (TextView)findViewById(R.id.other_details);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(SettingsActivity.this,OtherDetailsActivity.class);
                startActivity(l);
            }
        });

        /*bluePrint = (TextView)findViewById(R.id.setting_blueprint);
        bluePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SettingsActivity.this,BluePrintFragment.class);
                startActivity(i);
            }
        });
        portfolio = (TextView)findViewById(R.id.setting_portfolio);
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(SettingsActivity.this,PortfolioFragment.class);
                startActivity(j);
            }
        });
        followers = (TextView)findViewById(R.id.setting_followers);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(SettingsActivity.this,FollowersFragment.class);
                startActivity(k);
            }
        });
        following = (TextView)findViewById(R.id.setting_following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(SettingsActivity.this,FollowingFragment.class);
                startActivity(l);
            }
        });*/
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
