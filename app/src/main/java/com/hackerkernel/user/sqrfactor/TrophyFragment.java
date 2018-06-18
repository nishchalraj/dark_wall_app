package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrophyFragment extends Fragment {

    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getChildFragmentManager().beginTransaction().replace(R.id.fragment, new StatusFragment()).addToBackStack(null).commit();

        View view = (View)inflater.inflate(R.layout.fragment_trophy, container, false);
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

                if (tab.getPosition()==0)
                    getChildFragmentManager().beginTransaction().replace(R.id.fragment, new StatusFragment()).addToBackStack(null).commit();

                if (tab.getPosition()==1)
                    getChildFragmentManager().beginTransaction().replace(R.id.fragment, new DesignFragment()).addToBackStack(null).commit();

                if (tab.getPosition()==2)
                    getChildFragmentManager().beginTransaction().replace(R.id.fragment, new DesignFragment()).addToBackStack(null).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }

}
