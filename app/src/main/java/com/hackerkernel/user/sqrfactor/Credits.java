package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class Credits extends AppCompatActivity {
    Toolbar toolbar;
    ImageButton menu;
    private RecyclerView recyclerView;
    TextView bluePrint,portfolio,followers,following;
    ArrayList<CreditsClass> creditsClassArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView_credit);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(Credits.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //adding dummy data for testing
        CreditsClass creditsClass=new CreditsClass("ther sbdhsd need to ambadb","25","26","$20 reedmens");
        creditsClassArrayList.add(creditsClass);
        CreditsClass creditsClass1=new CreditsClass("ther sbdhsd need to ambadb","25","26","$20 reedmens");
        creditsClassArrayList.add(creditsClass1);

        CreditsAdapter creditsAdapter=new CreditsAdapter(this,creditsClassArrayList);
        recyclerView.setAdapter(creditsAdapter);

        toolbar = (Toolbar) findViewById(R.id.credits_toolbar);
        toolbar.setTitle("Credits");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        menu = (ImageButton)findViewById(R.id.more);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PopupMenu popupMenu = new PopupMenu(Credits.this,v);
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
        bluePrint = (TextView)findViewById(R.id.credit_blueprint);
        bluePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Credits.this,BluePrintFragment.class);
                startActivity(i);
            }
        });
        portfolio = (TextView)findViewById(R.id.credit_portfolio);
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Credits.this,Portfolio.class);
                startActivity(j);
            }
        });
        followers = (TextView)findViewById(R.id.credit_followers);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(Credits.this,FollowersFragment.class);
                startActivity(k);
            }
        });
        following = (TextView)findViewById(R.id.credit_following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(Credits.this,FollowingFragment.class);
                startActivity(l);
            }
        });
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