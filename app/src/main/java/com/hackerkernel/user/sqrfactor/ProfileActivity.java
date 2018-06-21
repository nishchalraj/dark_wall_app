package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class ProfileActivity extends ToolbarActivity {

    Toolbar toolbar;
    TabLayout tabLayout1;
    ImageView morebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        morebtn = (ImageView)findViewById(R.id.morebtn);
        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(getApplicationContext(), v);
                pop.getMenuInflater().inflate(R.menu.more_menu, pop.getMenu());
                pop.show();
            }
        });

        tabLayout1 = (TabLayout)findViewById(R.id.tabs2);

        tabLayout1.addTab(tabLayout1.newTab().setIcon(R.drawable.status)
                .setText("Status"));
        tabLayout1.addTab(tabLayout1.newTab().setIcon(R.drawable.design)
                .setText("Design"));
        tabLayout1.addTab(tabLayout1.newTab().setIcon(R.drawable.article)
                .setText("Article"));

        tabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){

                    case 1:
                        Intent i2 = new Intent(getApplicationContext(), DesignActivity.class);
                        startActivity(i2);
                        break;

                    case 2:
                        Intent i3 = new Intent(getApplicationContext(), ArticleActivity.class);
                        startActivity(i3);
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
