package com.hackerkernel.user.sqrfactor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class ToolbarActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.options, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        /*MenuItem profile = menu.findItem(R.id.profile);
        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                MenuItem grp = menu.findItem(R.id.grp);
                grp.setVisible(true);
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);

    }

    int flag = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.profile:
                if (flag==0){

                    getMenuInflater().inflate(R.menu.drawer_options, item.getSubMenu());
                    flag = 1;

                    Menu submenu = item.getSubMenu();
                    submenu.findItem(R.id.avatar).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            if (item.getItemId()==R.id.avatar){

                                Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(i);

                            }
                            return true;

                        }
                    });

                }
            break;
        }
        return true;
    }
}
