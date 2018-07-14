package com.hackerkernel.user.sqrfactor.Activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hackerkernel.user.sqrfactor.Fragments.CommentsFragment;
import com.hackerkernel.user.sqrfactor.R;

public class CommentsPageActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_page);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.back_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.commfrag, new CommentsFragment()).commit();

    }
}
