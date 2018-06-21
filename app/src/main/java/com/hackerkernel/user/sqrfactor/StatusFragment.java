package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StatusFragment extends Fragment {

    Button comment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_status, container, false);

        comment = (Button)rootView.findViewById(R.id.commentbtn);

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), CommentsPage.class);
                startActivity(i);

            }
        });

        return rootView;

    }
}
