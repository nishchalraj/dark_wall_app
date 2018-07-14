package com.hackerkernel.user.sqrfactor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackerkernel.user.sqrfactor.Pojo.CreditsClass;
import com.hackerkernel.user.sqrfactor.R;

import java.util.ArrayList;

public class CreditsAdapter extends RecyclerView.Adapter<CreditsAdapter.MyViewHolder> {

    ArrayList<CreditsClass> creditsClassArrayList;
    Context context;
    public CreditsAdapter(Context context, ArrayList<CreditsClass> creditsClassArrayList ) {
        this.creditsClassArrayList = creditsClassArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.credits_adapter_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       CreditsClass creditsClass=creditsClassArrayList.get(position);
       holder.article.setText(creditsClass.getArticle());
       holder.totalViews.setText(creditsClass.getTotalViews());
       holder.firstWeekViews.setText(creditsClass.getfirstWeekViews());
       holder.credits.setText(creditsClass.getCredits());

    }

    @Override
    public int getItemCount() {
        return creditsClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView article,totalViews,firstWeekViews,credits;
        public MyViewHolder(View itemView) {
            super(itemView);
            article=(TextView) itemView.findViewById(R.id.articleName);
            totalViews=(TextView) itemView.findViewById(R.id.totalView);
            firstWeekViews=(TextView) itemView.findViewById(R.id.firstWeekViews);
            credits=(TextView) itemView.findViewById(R.id.credits);
        }
    }
}
