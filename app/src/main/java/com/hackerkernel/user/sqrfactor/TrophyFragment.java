package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrophyFragment extends Fragment {

    //Button news, red;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View)inflater.inflate(R.layout.fragment_trophy, container, false);

        getChildFragmentManager().beginTransaction().replace(R.id.fragment, new StatusFragment()).addToBackStack(null).commit();

        tabLayout = (TabLayout)view.findViewById(R.id.tabs2);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.status)
                .setText("Status"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.design)
                .setText("Design"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.article)
                .setText("Article"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()){

                    case 1:
                        Intent i2 = new Intent(getContext(), DesignActivity.class);
                        startActivity(i2);
                        break;

                    case 2:
                        Intent i3 = new Intent(getContext(), ArticleActivity.class);
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

        /*news = (Button)view.findViewById(R.id.feed);
        red = (Button)view.findViewById(R.id.red);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getChildFragmentManager().beginTransaction().replace(R.id.frag1, new NewsFeedFragment()).addToBackStack(null).commit();

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getChildFragmentManager().beginTransaction().replace(R.id.frag1, new RedFragment()).addToBackStack(null).commit();

            }
        });*/

        return view;

    }

}
