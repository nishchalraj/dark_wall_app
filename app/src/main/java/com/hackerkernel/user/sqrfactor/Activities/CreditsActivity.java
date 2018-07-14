package com.hackerkernel.user.sqrfactor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.Adapters.CreditsAdapter;
import com.hackerkernel.user.sqrfactor.Pojo.CreditsClass;
import com.hackerkernel.user.sqrfactor.R;

import java.util.ArrayList;

public class CreditsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView menu;
    private RecyclerView recyclerView;
    TextView bluePrint,portfolio,followers,following;
    ArrayList<CreditsClass> creditsClassArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new CreditsFragment()).commit();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView_credit);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(CreditsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //adding dummy data for testing
        CreditsClass creditsClass=new CreditsClass("Powerful illustrations of the world...","25","26","$20 reedmens");
        creditsClassArrayList.add(creditsClass);

        CreditsClass creditsClass1=new CreditsClass("Architecture is no less than an art...","25","26","$20 reedmens");
        creditsClassArrayList.add(creditsClass1);

        CreditsAdapter creditsAdapter=new CreditsAdapter(this,creditsClassArrayList);
        recyclerView.setAdapter(creditsAdapter);

        toolbar = (Toolbar) findViewById(R.id.credits_toolbar);
        toolbar.setTitle("CreditsActivity");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        menu = (ImageView)findViewById(R.id.morebtn);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(CreditsActivity.this,v);
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
                Intent i = new Intent(CreditsActivity.this,BlueprintActivity.class);
                startActivity(i);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();

            }
        });

        portfolio = (TextView)findViewById(R.id.portfolio);
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(CreditsActivity.this,PortfolioActivity.class);
                startActivity(j);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                        //.addToBackStack(null).commit();
            }
        });
        followers = (TextView)findViewById(R.id.followers);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(CreditsActivity.this, FollowersActivity.class);
                startActivity(k);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();

            }
        });
        following = (TextView)findViewById(R.id.following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(CreditsActivity.this, FollowingActivity.class);
                startActivity(l);
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PortfolioFragment())
                //.addToBackStack(null).commit();

            }
        });
        /*bluePrint = (TextView)findViewById(R.id.credit_blueprint);
        bluePrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreditsActivity.this,BluePrintFragment.class);
                startActivity(i);
            }
        });
        portfolio = (TextView)findViewById(R.id.credit_portfolio);
        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(CreditsActivity.this,PortfolioFragment.class);
                startActivity(j);
            }
        });
        followers = (TextView)findViewById(R.id.credit_followers);
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(CreditsActivity.this,FollowersFragment.class);
                startActivity(k);
            }
        });
        following = (TextView)findViewById(R.id.credit_following);
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(CreditsActivity.this,FollowingFragment.class);
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