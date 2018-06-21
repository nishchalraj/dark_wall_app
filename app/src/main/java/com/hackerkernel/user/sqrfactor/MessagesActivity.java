package com.hackerkernel.user.sqrfactor;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

public class MessagesActivity extends ToolbarActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        tabLayout = (TabLayout)findViewById(R.id.tabs);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
