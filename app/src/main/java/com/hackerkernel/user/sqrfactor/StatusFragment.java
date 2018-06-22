package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StatusFragment extends Fragment {

    Button like ,comment, share, like2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_status, container, false);

        like = (Button)rootView.findViewById(R.id.like);
        comment = (Button)rootView.findViewById(R.id.comment);
        share = (Button)rootView.findViewById(R.id.share);

        like2 = (Button)rootView.findViewById(R.id.like2);

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), CommentsPage.class);
                startActivity(i);

            }
        });

        like.setOnClickListener(new View.OnClickListener() {

            int flag = 0;
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    like.setTextColor(getResources().getColor(R.color.sqr));
                    flag = 1;
                }
                else {
                    like.setTextColor(getResources().getColor(R.color.gray));
                    flag = 0;
                }
            }

        });

        share.setOnClickListener(new View.OnClickListener() {

            int flag = 0;
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    share.setTextColor(getResources().getColor(R.color.sqr));
                    flag = 1;
                }
                else {
                    share.setTextColor(getResources().getColor(R.color.gray));
                    flag = 0;
                }
            }
        });

        like2.setOnClickListener(new View.OnClickListener() {

            int flag = 0;
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    like2.setTextColor(getResources().getColor(R.color.sqr));
                    flag = 1;
                }
                else {
                    like2.setTextColor(getResources().getColor(R.color.gray));
                    flag = 0;
                }
            }

        });

        return rootView;

    }

}
