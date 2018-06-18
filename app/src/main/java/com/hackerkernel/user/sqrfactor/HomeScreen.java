package com.hackerkernel.user.sqrfactor;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class HomeScreen extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager pager;
    TabLayout tabLayout;
    ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        pager = (ViewPager)findViewById(R.id.pager);
        imageView = (ImageView) findViewById(R.id.options);

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout = (TabLayout)findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(pager);

        tabLayout.getTabAt(0).setIcon(R.drawable.trophy);
        tabLayout.getTabAt(1).setIcon(R.drawable.megaphone);
        tabLayout.getTabAt(2).setIcon(R.drawable.msg);
        tabLayout.getTabAt(3).setIcon(R.drawable.notification);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(HomeScreen.this, imageView);
                pop.getMenuInflater().inflate(R.menu.options, pop.getMenu());
            }
        });

        /*tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){

                    case 0:
                        tab.setIcon(R.drawable.trophy_filled);

                    case 1:
                        tab.setIcon(R.drawable.megaphone_filled);

                    case 2:
                        tab.setIcon(R.drawable.envelope_filled);

                    case 3:
                        tab.setIcon(R.drawable.notification_filled);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()){

                    case 0:
                        tab.setIcon(R.drawable.trophy);

                    case 1:
                        tab.setIcon(R.drawable.megaphone);

                    case 2:
                        tab.setIcon(R.drawable.msg);

                    case 3:
                        tab.setIcon(R.drawable.notification);

                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

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

                case 3:
                    return new TrophyFragment();

            }

            return null;

        }

        @Override
        public int getCount() {
            return 4;
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

}
