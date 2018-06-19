package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager pager;
    TabLayout tabLayout;
    ImageView imageView;
    DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.Open, R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav = (NavigationView)findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.news:
                        Intent i = new Intent(getApplicationContext(), RedActivity.class);
                        startActivity(i);
                        break;

                }

                drawer.closeDrawers();
                return true;

            }

        });

        pager = (ViewPager)findViewById(R.id.pager);
        //imageView = (ImageView) findViewById(R.id.options);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(pager);

        tabLayout.getTabAt(0).setIcon(R.drawable.trophy_filled);
        tabLayout.getTabAt(1).setIcon(R.drawable.msg);
        tabLayout.getTabAt(2).setIcon(R.drawable.notification);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /*imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(HomeScreen.this, imageView);
                pop.getMenuInflater().inflate(R.menu.options, pop.getMenu());
            }
        });*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){

                    case 0:
                        tab.setIcon(R.drawable.trophy_filled);
                        break;

                    case 1:
                        tab.setIcon(R.drawable.envelope_filled);
                        break;

                    case 2:
                        tab.setIcon(R.drawable.notification_filled);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()){

                    case 0:
                        tab.setIcon(R.drawable.trophy);
                        break;

                    case 1:
                        tab.setIcon(R.drawable.msg);
                        break;

                    case 2:
                        tab.setIcon(R.drawable.notification);
                        break;


                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private class SectionsPagerAdapter extends FragmentStatePagerAdapter{


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:
                    return new TrophyFragment();

                case 1:
                    return new TrophyFragment();

                case 2:
                    return new TrophyFragment();

            }

            return null;

        }

        @Override
        public int getCount() {
            return 3;
        }

        /*@Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){

                case 0:
                    return "Status";

                case 1:
                    return "Status";

                case 2:
                    return "Status";

                case 3:
                    return "Status";

            }

            return null;

        }*/
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawers();
        else
            super.onBackPressed();
    }
}
