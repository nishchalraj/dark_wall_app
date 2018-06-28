package com.hackerkernel.user.sqrfactor;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

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

        recycler.addOnItemTouchListener(new TouchListener(this, recycler, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position==1){
                    Intent i = new Intent(getApplicationContext(), ChatroomActivity.class);
                    startActivity(i);
                }
            }
        }));

    }

    public static interface ClickListener{
        public void onClick(View view, int position);
    }

    class TouchListener implements RecyclerView.OnItemTouchListener{
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public TouchListener(Context context, RecyclerView recyclerView, ClickListener clickListener) {

            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            clickListener.onClick(child, rv.getChildAdapterPosition(child));

            return true;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

}
