package com.hackerkernel.user.sqrfactor.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackerkernel.user.sqrfactor.Adapters.MessageAdapter;
import com.hackerkernel.user.sqrfactor.R;

public class MessagesFragment extends Fragment {

    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;

    String names[] = {"Keanu Reeves", "Henry Cavill", "Henry Cavill"};
    String desc[] = {"Have you watched The Matrix?", "I belong to DC!", "I am Henry's doppleganger!"};
    Drawable dw1, dw2, dw3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_messages, container, false);

        dw1 = getContext().getResources().getDrawable(R.drawable.profilepic);
        dw2 = getContext().getResources().getDrawable(R.drawable.henry);
        dw3 = getContext().getResources().getDrawable(R.drawable.henry);

        Drawable drawables[] = {dw1, dw2, dw3};

        recycler = (RecyclerView)rootView.findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);

        adapter = new MessageAdapter(names, desc, drawables);
        recycler.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(recycler.getContext(), linearLayoutManager.getOrientation());
        recycler.addItemDecoration(decoration);

        return rootView;

    }

}
