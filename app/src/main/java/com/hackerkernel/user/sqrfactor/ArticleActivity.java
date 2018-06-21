package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ArticleActivity extends ToolbarActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }
}
