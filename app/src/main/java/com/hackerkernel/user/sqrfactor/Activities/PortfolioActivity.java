package com.hackerkernel.user.sqrfactor.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hackerkernel.user.sqrfactor.Adapters.PortfolioAdapter;
import com.hackerkernel.user.sqrfactor.Pojo.PortfolioClass;
import com.hackerkernel.user.sqrfactor.R;

import java.util.ArrayList;

public class PortfolioActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<PortfolioClass> portfolioArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView_portfolio);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        PortfolioAdapter portfolioAdapter = new PortfolioAdapter(portfolioArrayList,this);
//
        PortfolioClass portfolioClass = new PortfolioClass("url","Architectural wonders of the...","Amit","5","4");
        portfolioArrayList.add(portfolioClass);
//
        recyclerView.setAdapter(portfolioAdapter);

    }
}
