package com.hackerkernel.user.sqrfactor.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hackerkernel.user.sqrfactor.Adapters.ChatAdapter;
import com.hackerkernel.user.sqrfactor.R;

public class ChatroomActivity extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        recycler = (RecyclerView)findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), linearLayoutManager.getOrientation());

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.addItemDecoration(decoration);

        adapter = new ChatAdapter();
        recycler.setAdapter(adapter);

    }

}
