package com.hackerkernel.user.sqrfactor;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MessagesActivity extends ToolbarActivity {

    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;

    String names[] = {"Keanu Reeves", "Henry Cavill", "Henry Cavill"};
    String desc[] = {"Have you watched The Matrix?", "I belong to DC!", "I am Henry's doppleganger!"};
    Drawable dw1, dw2, dw3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dw1 = getApplicationContext().getResources().getDrawable(R.drawable.profilepic);
        dw2 = getApplicationContext().getResources().getDrawable(R.drawable.henry);
        dw3 = getApplicationContext().getResources().getDrawable(R.drawable.henry);

        Drawable drawables[] = {dw1, dw2, dw3};

        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);

        adapter = new MessageAdapter(names, desc, drawables);
        recycler.setAdapter(adapter);

        DividerItemDecoration decoration = new DividerItemDecoration(recycler.getContext(), linearLayoutManager.getOrientation());
        recycler.addItemDecoration(decoration);

    }

}
