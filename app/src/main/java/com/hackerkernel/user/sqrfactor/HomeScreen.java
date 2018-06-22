package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

public class HomeScreen extends ToolbarActivity {

    Toolbar toolbar;
    //ViewPager pager;
    TabLayout tabLayout;
    ImageView imageView;
    //DrawerLayout drawer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainfrag, new TrophyFragment()).commit();

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*drawer = (DrawerLayout)findViewById(R.id.drawer);
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
        pager.setAdapter(adapter);*/

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        //tabLayout.setupWithViewPager(pager);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.news_feed2));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.msg));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.notification));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_menu_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.profilepic));

        /*tabLayout.getTabAt(0).setIcon(R.drawable.trophy_filled);
        tabLayout.getTabAt(1).setIcon(R.drawable.msg);
        tabLayout.getTabAt(2).setIcon(R.drawable.notification);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){

                    case 0:

                        break;

                    case 1:
                        tab.setIcon(R.drawable.envelope_filled);
                        Intent i1 = new Intent(getApplicationContext(), MessagesActivity.class);
                        startActivity(i1);
                        break;

                    case 2:
                        tab.setIcon(R.drawable.notification_filled);
                        Intent i2 = new Intent(getApplicationContext(), NotificationsActivity.class);
                        startActivity(i2);
                        break;

                    case 3:
                        //Intent i3 = new Intent(getApplicationContext(), MessagesActivity.class);
                        //startActivity(i3);
                        break;

                    case 4:
                        new ModalSheet().show(getSupportFragmentManager(), "");
                        //Intent i4 = new Intent(getApplicationContext(), MessagesActivity.class);
                        //startActivity(i4);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()){

                    case 0:

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
    public void onBackPressed() {

        //LinearLayout ll = (LinearLayout)tabLayout.getChildAt(0);
        //ll.getChildAt(tabLayout.getSelectedTabPosition()).setSelected(false);
        //ll.getChildAt(0).setSelected(true);
        super.onBackPressed();
    }

    /*private class SectionsPagerAdapter extends FragmentStatePagerAdapter{


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:
                    return new TrophyFragment();

                case 1:
                    return new MessagesFragment();

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

        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawers();
        else
            super.onBackPressed();
    }*/
}
